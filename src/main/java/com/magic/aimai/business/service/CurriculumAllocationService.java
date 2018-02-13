package com.magic.aimai.business.service;

import com.magic.aimai.business.entity.*;
import com.magic.aimai.business.exception.InterfaceCommonException;
import com.magic.aimai.business.mapper.ICurriculumAllocationMapper;
import com.magic.aimai.business.mapper.IOrderMapper;
import com.magic.aimai.business.mapper.IUserMapper;
import com.magic.aimai.business.util.ExcelReader;
import com.magic.aimai.business.util.MD5Util;
import com.magic.aimai.business.util.StatusConstant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * 课程分配 Service
 * Created by Eric Xie on 2017/7/14 0014.
 */
@Service
public class CurriculumAllocationService {


    @Resource
    private ICurriculumAllocationMapper curriculumAllocationMapper;
    @Resource
    private IUserMapper userMapper;
    @Resource
    private IOrderMapper orderMapper;








    /**
     *  批量新增 分配 V2
     * @param allocations
     * @param orderIds
     * @throws Exception
     */
    public Set<User> addAllocation(List<CurriculumAllocation> allocations,Set<Integer> orderIds) throws Exception{
        Set<User> existUseList = new HashSet<User>(); // 这些用户已经拥有了改课程
        List<User> users = userMapper.queryUserByOrderIdV2(orderIds);
        if(null != users && users.size() > 0){
            // 查询并且哪些用户拥有该课程并且记录下来
            Iterator<CurriculumAllocation> iterator = allocations.iterator();
            while (iterator.hasNext()){
                CurriculumAllocation next = iterator.next();
                for (User user : users) {
                    if(user.getId().equals(next.getUserId())){
                        existUseList.add(user);
                        iterator.remove();
                        break;
                    }
                }
            }
        }
        if(allocations.size() > 0){
            curriculumAllocationMapper.batchAddAllocation(allocations);
        }
        return existUseList;
    }


    //********************************
    //*********以上V2接口**************//
    //********************************






    /**
     *  批量新增 分配
     * @param allocations
     * @param orderId
     * @throws Exception
     */
    public Set<User> addAllocation(List<CurriculumAllocation> allocations,Integer orderId) throws Exception{
        Order order = orderMapper.info(orderId);
        if (null == order) {
            throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST, "未知订单");
        }
        if (order.getPayStatus() == 0) {
            throw new InterfaceCommonException(StatusConstant.ORDER_STATUS_ABNORMITY, "订单未支付，不能进行分配");
        }
        Set<User> existUseList = new HashSet<User>(); // 这些用户已经拥有了改课程
        List<User> users = userMapper.queryUserByOrderId(orderId);
        if(null != users && users.size() > 0){
            // 查询并且哪些用户拥有该课程并且记录下来
            Iterator<CurriculumAllocation> iterator = allocations.iterator();
            while (iterator.hasNext()){
                CurriculumAllocation next = iterator.next();
                for (User user : users) {
                    if(user.getId().equals(next.getUserId())){
                        existUseList.add(user);
                        iterator.remove();
                        break;
                    }
                }
            }
        }
        int waitNum = 0;
        for (CurriculumAllocation allocation : allocations) {
            allocation.setOrderId(orderId);
            waitNum += allocation.getNumber();
        }
        // 可以分配的数量
        Integer count = curriculumAllocationMapper.querySurplusAllocation(orderId);
        if(waitNum > count){
            throw new InterfaceCommonException(StatusConstant.Fail_CODE,"剩余可分配数量不足");
        }
        if(allocations.size() > 0){
            curriculumAllocationMapper.batchAddAllocation(allocations);
        }
        return existUseList;
    }


    /**
     *  批量新增 分配
     *  查询是否被分配过。如果有用户被分配过，则不能分配成功
     * @param allocations
     * @throws Exception
     */
    public void addAllocation(List<CurriculumAllocation> allocations) throws Exception{

        curriculumAllocationMapper.batchAddAllocation(allocations);
    }

    /**
     * 分配课程
     * @param allocation
     * @throws Exception
     */
    public void addAllocation(CurriculumAllocation allocation) throws Exception{
        // 可以分配的数量
        Integer count = curriculumAllocationMapper.querySurplusAllocation(allocation.getOrderId());
        if(allocation.getNumber() > count){
            throw new InterfaceCommonException(StatusConstant.Fail_CODE,"剩余可分配数量不足");
        }
        curriculumAllocationMapper.addCurriculumAllocation(allocation);
    }

    /**
     * 后台页面 分页获取评论
     * @param pageArgs 分页属性
     * @param orderId 订单id
     * @return
     */
    public PageList<CurriculumAllocation> listForAdmin(PageArgs pageArgs , String phone ,Integer orderId) {
        PageList<CurriculumAllocation> pageList = new PageList<CurriculumAllocation>();
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("phone",phone);
        map.put("orderId",orderId);
        int count = curriculumAllocationMapper.listForAdminCount(map);
        if (count > 0) {
            map.put("pageArgs",pageArgs);
            pageList.setList(curriculumAllocationMapper.listForAdmin(map));
        }
        pageList.setTotalSize(count);
        return pageList;
    }

    /**
     * 导入  分配用户进行分配
     * @param resourcesUrl
     * @param orderId
     * @return
     * @throws Exception
     */
    public Map<String,Object> importExcelCurriculumAllocation(String resourcesUrl,Integer orderId) throws Exception {

        Order order = orderMapper.info(orderId);
        if (null == order) {
            throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST, "未知订单");
        }
        if (order.getPayStatus() == 0) {
            throw new InterfaceCommonException(StatusConstant.ORDER_STATUS_ABNORMITY, "订单未支付，不能进行分配");
        }

        int HttpResult; // 服务器返回的状态
        URL url = new URL(resourcesUrl); // 创建URL
        URLConnection urlConn = url.openConnection();
        urlConn.connect();
        HttpURLConnection httpConn = (HttpURLConnection) urlConn;
        HttpResult = httpConn.getResponseCode();
        if (HttpResult != HttpURLConnection.HTTP_OK)
            throw new InterfaceCommonException(StatusConstant.Fail_CODE, "数据读取失败");
        else {
            Map<Integer, List<String>> map = ExcelReader.readExcelContent(urlConn.getInputStream());
            List<User> importUsers = new ArrayList<User>(); // 读取出来的用户列表
            List<String> phones = new ArrayList<String>();
            // 解析数据
            for (Integer cellNum : map.keySet()) {
                List<String> values = map.get(cellNum);
                if (values.size() < 1) {
                    throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST, "数据不完整");
                }
                if (null == values.get(0)) {
                    throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST, "手机号必填");
                }
                User temp = new User();
                temp.setPhone(values.get(0));
                //普通用户
                importUsers.add(temp);
                phones.add(values.get(0));
            }

            if (phones.size() == 0 || importUsers.size() == 0) {
                throw new InterfaceCommonException(StatusConstant.Fail_CODE, "表格数据为空");
            }

            List<User> failUsers = new ArrayList<User>(); // 添加失败的用户
            int successNum = 0;
            int failNum = 0;
            List<CurriculumAllocation> list = new ArrayList<CurriculumAllocation>();
            //可以添加的用户
            List<User> users = userMapper.findUserByPhone(phones,orderId);
            if (null != users && users.size() > 0) {
                // 可以分配的数量
                Integer count = curriculumAllocationMapper.querySurplusAllocation(orderId);
                if(users.size() > count){
                    throw new InterfaceCommonException(StatusConstant.Fail_CODE,"剩余可分配数量不足，请减少");
                }

                for (User importUser : importUsers) {
                    if(!users.contains(importUser)){
                        // 如果存在
                        failNum++;
                        failUsers.add(importUser);
                        continue;
                    }
                    successNum++;
                }
                for (User user : users) {
                    CurriculumAllocation curriculumAllocation = new CurriculumAllocation();
                    curriculumAllocation.setUserId(user.getId());
                    curriculumAllocation.setNumber(1);
                    curriculumAllocation.setOrderId(orderId);
                    curriculumAllocation.setCreateTime(new Date());
                    list.add(curriculumAllocation);
                }
            } else {
                failNum = importUsers.size();
                failUsers.addAll(importUsers);
            }
            //批量添加
            if (list.size() > 0) {
                curriculumAllocationMapper.batchAddAllocation(list);
            }
            Map<String,Object> result = new HashMap<String, Object>();
            result.put("successNum",successNum);
            result.put("failNum",failNum);
            result.put("failUsers",failUsers);
            return result;
        }
    }

    /**
     * 更新分配的课程 用户是否可以学习
     * @param studyStatus 学习状态  1：可以学习   0：停止  1：暂停
     * @param userId
     * @param orderId
     */
    public void updateIsCanStudy(Integer studyStatus , Integer userId, Integer orderId) {
        curriculumAllocationMapper.updateIsCanStudy(studyStatus, userId, orderId);
    }
    /**
     * 更新分配的课程 用户是否可以学习2
     * @param studyStatus 学习状态  1：可以学习   0：停止  1：暂停
     * @param id
     */
    public void updateIsCanStudy2(Integer studyStatus , Integer id) {
        curriculumAllocationMapper.updateIsCanStudy2(studyStatus, id);
    }


}
