package com.magic.aimai.business.service;

import com.magic.aimai.business.entity.*;
import com.magic.aimai.business.exception.InterfaceCommonException;
import com.magic.aimai.business.mapper.ICurriculumMapper;
import com.magic.aimai.business.mapper.IOrderMapper;
import com.magic.aimai.business.mapper.IUserMapper;
import com.magic.aimai.business.util.LoginHelper;
import com.magic.aimai.business.util.StatusConstant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Eric Xie on 2017/7/14 0014.
 */
@Service
public class OrderService {

    @Resource
    private IOrderMapper orderMapper;
    @Resource
    private IUserMapper userMapper;
    @Resource
    private ICurriculumMapper curriculumMapper;




    /**
     * 获取公司用户 有效的订单集合
     * 1、已经支付了的订单
     * 2、订单的数量大于0
     * @return
     */
    public List<Order> queryOrderByCompany(Integer userId,Integer curriculumId){
        return orderMapper.queryOrderByCompany(userId,curriculumId);
    }


    //*********************************
    //**********以上V2.0 JPA************//
    //*********************************//

    public List<Order> queryOrderByUser(Integer userId,Integer curriculumId){
        return orderMapper.queryOrderByUser(userId,curriculumId);
    }

    /**
     * 支付成功后 的业务处理
     * @param order 订单
     * @param payMethod 支付方式
     * @param outTradeNO 第三方支付订单号
     */
    @Transactional
    public void paySuccess(Order order,Integer payMethod,String outTradeNO){
        Order temp = new Order();
        temp.setId(order.getId());
        temp.setPayMethod(payMethod);
        temp.setPayStatus(StatusConstant.YES_PAY);
        temp.setOutTradeNO(outTradeNO);
        orderMapper.updateOrder(temp);
        // 支付成功后 兑换积分 按 1:1比例兑换
        User user = userMapper.queryBaseInfoById(order.getUserId());
        user.setAccumulate(user.getAccumulate() + (int)Math.floor(order.getPrice()));
        User loginUser = LoginHelper.getCurrentUser(user.getToken());
        if(null != loginUser){
            loginUser.setAccumulate(user.getAccumulate());
            LoginHelper.replaceToken(loginUser.getToken(),loginUser);
        }
        User tempUser = new User();
        tempUser.setId(user.getId());
        tempUser.setAccumulate(user.getAccumulate());
        userMapper.updateUser(tempUser);
    }


    /**
     * 查询订单基础字段
     * @param orderId 订单ID
     * @return
     */
    public Order queryBaseOrder(Integer orderId){
        return orderMapper.queryBaseOrder(orderId);
    }

    /**
     * 动态查询订单列表
     * @param userId 用户
     * @param payStatus 支付状态
     * @param pageNO 页码
     * @param pageSize 页码
     * @return
     */
    public List<Order> queryOrderByItems(Integer userId,Integer payStatus,Integer pageNO,Integer pageSize){
        return orderMapper.queryOrder(userId,payStatus,(pageNO - 1) * pageSize,pageSize);
    }


    /**
     * 动态查询订单列表
     * @param userId 用户
     * @param payStatus 支付状态
     * @param pageNO 页码
     * @param pageSize 页码
     * @return
     */
    public Page<Order> queryOrderByItemsOfWeb(Integer userId,Integer payStatus,Integer pageNO,Integer pageSize){
        List<Order> count = orderMapper.queryOrder(userId, payStatus, null, null);
        List<Order> orders = new ArrayList<Order>();
        if(null != count && count.size() > 0){
             orders = orderMapper.queryOrder(userId, payStatus, (pageNO - 1) * pageSize, pageSize);
        }
        return new Page<Order>(orders,null == count ? 0 : count.size(),0);
    }


    /**
     * 更新订单
     * @param order
     */
    public void updateOrder(Order order){
        orderMapper.updateOrder(order);
    }


    /**
     * 创建订单
     * @param order
     */
    @Transactional
    public Integer addOrder(Order order) throws Exception{
        if (null == order.getCurriculumId()) {
            throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL,"字段不能为空");
        }
        Curriculum curriculum = curriculumMapper.queryBaseInfo(order.getCurriculumId());
        if (null == curriculum) {
            throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST,"未知课程");
        }
        // 判断当前用户是否拥有过该课程，不管是否支付
        
        order.setCityId(curriculum.getCityId());
        orderMapper.addOrder(order);
        return order.getId();
    }


    /**
     * 后台页面 分页获取订单
     * @param pageArgs 分页工具
     * @param provinceId 省id
     * @param cityId 市id
     * @param districtId 区县id
     * @param userName 用户名
     * @param orderNumber 订单号
     * @param payStatus 支付状态
     * @param payMethod 支付方式
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    public PageList<Order> listForAdmin(PageArgs pageArgs,Integer provinceId ,Integer cityId ,Integer districtId ,
                                             String  userName ,String  orderNumber , Integer payStatus ,Integer payMethod ,
                                             Date startTime, Date endTime,Integer isPlatformCreate) {
        PageList<Order> pageList = new PageList<Order>();
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("provinceId",provinceId);
        map.put("cityId",cityId);
        map.put("districtId",districtId);
        map.put("userName",userName);
        map.put("orderNumber",orderNumber);
        map.put("payStatus",payStatus);
        map.put("payMethod",payMethod);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("isPlatformCreate",isPlatformCreate);
        int count = orderMapper.listForAdminCount(map);
        if (count > 0) {
            map.put("pageArgs",pageArgs);
            List<Order> list = orderMapper.listForAdmin(map);
            pageList.setList(list);
        }
        pageList.setTotalSize(count);
        return pageList;
    }

    @Transactional
    public void save(Order order) {
        if (null == order.getCurriculumId()) {
            throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL,"字段不能为空");
        }
        Curriculum curriculum = curriculumMapper.queryBaseInfo(order.getCurriculumId());
        if (null == curriculum) {
            throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST,"未知课程");
        }
        order.setCityId(curriculum.getCityId());
        order.setPayMethod(3);
        order.setPayStatus(StatusConstant.YES_PAY);
        orderMapper.addOrder(order);

        // 支付成功后 兑换积分 按 1:1比例兑换
        User user = userMapper.queryBaseInfoById(order.getUserId());
        user.setAccumulate(user.getAccumulate() + (int)Math.floor(order.getPrice()));
        User loginUser = LoginHelper.getCurrentUser(user.getToken());
        if(null != loginUser){
            loginUser.setAccumulate(user.getAccumulate());
            LoginHelper.replaceToken(loginUser.getToken(),loginUser);
        }
        User tempUser = new User();
        tempUser.setId(user.getId());
        tempUser.setAccumulate(user.getAccumulate());
        userMapper.updateUser(tempUser);
    }

    /**
     * 订单详情
     * @param orderId 订单ID
     * @return
     */
    public Order info(Integer orderId){
        return orderMapper.info(orderId);
    }
    /**
     * 更新购买的课程 用户是否可以学习
     * @param studyStatus 学习状态  1：可以学习   0：停止  1：暂停
     * @param id
     */
    public void updateIsCanStudy(Integer studyStatus , Integer id ) {
        orderMapper.updateIsCanStudy(studyStatus, id);
    }
}
