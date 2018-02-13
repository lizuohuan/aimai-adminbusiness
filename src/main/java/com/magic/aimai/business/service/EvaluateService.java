package com.magic.aimai.business.service;

import com.alibaba.fastjson.JSONArray;
import com.magic.aimai.business.entity.Evaluate;
import com.magic.aimai.business.entity.PageArgs;
import com.magic.aimai.business.entity.PageList;
import com.magic.aimai.business.entity.User;
import com.magic.aimai.business.enums.Common;
import com.magic.aimai.business.exception.InterfaceCommonException;
import com.magic.aimai.business.mapper.IEvaluateMapper;
import com.magic.aimai.business.mapper.IUserMapper;
import com.magic.aimai.business.util.CommonUtil;
import com.magic.aimai.business.util.LoginHelper;
import com.magic.aimai.business.util.StatusConstant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 评价 Service
 * Created by Eric Xie on 2017/7/17 0017.
 */
@Service
public class EvaluateService {

    @Resource
    private IEvaluateMapper evaluateMapper;
    @Resource
    private IUserMapper userMapper;

    /**
     * 评论审核通过
     * @param isValid 1 审核通过  0:不通过
     */
    @Transactional
    public void auditEvaluate(Integer evaluateId,Integer isValid) throws Exception{
        Evaluate evaluate = evaluateMapper.queryEvaluateById(evaluateId);
        if(null == evaluate){
            throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST,"评论不存在");
        }
        User user = userMapper.queryBaseInfoById(evaluate.getUserId());
        if(null == user){
            throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST,"用户不存在");
        }
        if(Common.YES.ordinal() == isValid){
            // 如果审核通过
            // 更新 10积分
            User temp = new User();
            temp.setId(user.getId());
            temp.setAccumulate(user.getAccumulate() + 10);
            userMapper.updateUser(temp);
            // 更新缓存
            if(!CommonUtil.isEmpty(user.getToken())){
                User currentUser = LoginHelper.getCurrentUser(user.getToken());
                if (null != currentUser) {
                    currentUser.setAccumulate(temp.getAccumulate());
                    LoginHelper.replaceToken(currentUser.getToken(),currentUser);
                }
            }
            evaluate.setIsValid(isValid);
            evaluateMapper.updateEvaluate(evaluate);
        }
    }


    public List<Evaluate> queryEvaluate(Integer userId,Integer pageNO,Integer pageSize,Integer curriculumId){
        return evaluateMapper.queryEvaluate(userId,(pageNO - 1) * pageSize ,pageSize,curriculumId);
    }


    /**
     * 新增评价
     * @param evaluate
     */
    public void addEvaluate(Evaluate evaluate){

        evaluateMapper.addEvaluate(evaluate);
    }

    /**
     * 更新不为空的字段 通过ID
     * @param evaluate
     */
    public void updateEvaluate(Evaluate evaluate){
        evaluateMapper.updateEvaluate(evaluate);
    }

    /**
     * 后台页面 分页获取评论
     * @param pageArgs 分页属性
     * @param userName 评论用户
     * @param curriculumName 课程名
     * @param provinceId 省
     * @param cityId 市
     * @param districtId 区
     * @param isValid 是否有效  0:无效  1:有效
     * @param startTime  创建开始时间
     * @param endTime 创建结束时间
     * @return
     */
    public PageList<Evaluate> listForAdmin(PageArgs pageArgs , String userName , String curriculumName,
                                           Integer provinceId , Integer cityId , Integer districtId ,
                                           Integer isValid ,Date startTime , Date endTime) {
        PageList<Evaluate> pageList = new PageList<Evaluate>();
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("userName",userName);
        map.put("curriculumName",curriculumName);
        map.put("provinceId",provinceId);
        map.put("cityId",cityId);
        map.put("districtId",districtId);
        map.put("isValid",isValid);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        int count = evaluateMapper.listForAdminCount(map);
        if (count > 0) {
            map.put("pageArgs",pageArgs);
            pageList.setList(evaluateMapper.listForAdmin(map));
        }
        pageList.setTotalSize(count);
        return pageList;
    }

    /**
     * 删除评论
     * @param id
     */
    @Transactional
    public void delete(Integer id) {
        evaluateMapper.delete(id);
    }

    /**
     * 批量删除评论
     * @param ids
     */
    @Transactional
    public void deleteList(List<Integer> ids) {
        evaluateMapper.deleteList(ids);
    }



    /**
     * 评论审核通过
     * @param idsAndIsValid
     */
    @Transactional
    public void updateList(String idsAndIsValid) throws Exception{
        List<Evaluate> list = JSONArray.parseArray(idsAndIsValid,Evaluate.class);
        List<User> userList = new ArrayList<User>();
        for (Evaluate evaluate : list) {
            Evaluate e = evaluateMapper.queryEvaluateById(evaluate.getId());
            if(null == e){
                throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST,"评论不存在");
            }
            User user = userMapper.queryBaseInfoById(evaluate.getUserId());
            if(null == user){
                throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST,"用户不存在");
            }

            if(Common.YES.ordinal() == evaluate.getIsValid()){
                // 如果审核通过
                // 更新 10积分
                User temp = new User();
                temp.setId(user.getId());
                temp.setAccumulate(user.getAccumulate() + 10);
                userList.add(temp);
                // 更新缓存
                if(!CommonUtil.isEmpty(user.getToken())){
                    User currentUser = LoginHelper.getCurrentUser(user.getToken());
                    currentUser.setAccumulate(temp.getAccumulate());
                    LoginHelper.replaceToken(currentUser.getToken(),currentUser);
                }
                evaluate.setIsValid(evaluate.getIsValid());
            }
        }
        userMapper.batchUpdateUser(userList);
        evaluateMapper.updateList(list);
    }
}
