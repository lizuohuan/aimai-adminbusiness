package com.magic.aimai.business.mapper;

import com.magic.aimai.business.entity.User;
import com.magic.aimai.business.entity.WebStatistics;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户 Mapper
 * Created by Eric Xie on 2017/7/12 0012.
 */
public interface IUserMapper {

    /**
     * 通过城市获取 城市下的游离用户
     * @param cIds
     * @return
     */
    List<User> queryCompanyByCity(@Param("cIds") Set<Integer> cIds,@Param("limit") Integer limit,
                                  @Param("limitSize") Integer limitSize);

    User queryUserByEmail(@Param("email") String email,@Param("userId") Integer userId);

    /**
     * 获取在该公司下某课程未分配的员工列表
     * @param curriculumId
     * @param companyId
     * @return
     */
    List<User> queryUserByCurriculum(@Param("curriculumId") Integer curriculumId,@Param("companyId") Integer companyId);

    List<User> queryUserByCurriculum2(@Param("curriculumId") Integer curriculumId,@Param("companyId") Integer companyId);

    List<User> queryUserByOrderIdV2(@Param("orderIds") Set<Integer> orderIds);

    //********************************
    //*********以上V2接口**************//
    //********************************

    /**
     * 通过订单ID  查询整个系统里 有哪些用户已经购买或者拥有过该订单的课程
     * @param orderId
     * @return
     */
    List<User> queryUserByOrderId(@Param("orderId") Integer orderId);

    /**
     * 通过状态获取用户基础字段列表
     * @param isValid
     * @return
     */
    List<User> queryUserByIsValid(@Param("isValid") Integer isValid,@Param("roleId") Integer roleId,@Param("phoneNumber") String phoneNumber,
                                  @Param("limit") Integer limit,@Param("limitSize") Integer limitSize);
    /**
     * 通过状态获取用户基础字段列表
     * @param isValid
     * @return
     */
    int countUserByIsValid(@Param("isValid") Integer isValid,@Param("roleId") Integer roleId,@Param("phoneNumber") String phoneNumber);

    /**
     * 查询所有用户的Id
     * @return
     */
    List<User> queryAllUserId();


    /**
     * 查询 已考核的人
     * @param companyId
     * @param orderId
     * @param limit
     * @param limitSize
     * @return
     */
    List<User> queryExamine(@Param("companyId") Integer companyId,@Param("orderId") Integer orderId,
                            @Param("limit") Integer limit,@Param("limitSize") Integer limitSize);

    /**
     * 查看已观看的人数 通过公司ID 和 订单ID
     * @param companyId
     * @param limit
     * @param limitSize
     * @return
     */
    List<User> queryWatchVideo(@Param("companyId") Integer companyId,@Param("orderId") Integer orderId,
                               @Param("limit") Integer limit,@Param("limitSize") Integer limitSize);

    /**
     * 通过用户手机号 批量更新用户部分字段
     * @param users
     * @return
     */
    Integer batchUpdate(@Param("users") List<User> users);

    /**
     * 通过手机号集合  批量查询用户
     * @param phones
     * @return
     */
    List<User> batchQueryUserByPhone(@Param("phones") List<String> phones);

    /**
     * 通过手机号集合 订单id 批量查询未分配此课程的用户
     * @param phones
     * @return
     */
    List<User> findUserByPhone(@Param("phones") List<String> phones ,@Param("orderId") Integer orderId);

    /**
     *  web 端 导表
     * @param year
     * @param stageId
     * @param cityIds
     * @param searchParams
     * @return
     */
    List<WebStatistics> exportExcel(@Param("year") Date year, @Param("stageId") Integer stageId, @Param("cityIds") List<Integer> cityIds,
                                    @Param("searchParams") String searchParams);

    /**
     * 获取观看人员
     * @param map
     * @return
     */
    List<User> queryWatchUsers(Map<String,Object> map);

    /**
     * 获取安全人员
     * @param map
     * @return
     */
    List<User> querySafeUsers(Map<String,Object> map);

    /**
     * web端 政府个人中心 统计接口
     * @param year
     * @param stageId
     * @param cityIds
     * @return
     */
    WebStatistics webStatistics(@Param("year") Date year, @Param("stageId") Integer stageId, @Param("cityIds") List<Integer> cityIds,
                                @Param("searchParams") String searchParams);

    /**
     * 查看个人详情 包括 课程等详情
     * @param userId
     * @return
     */
    User queryUserDetail(@Param("userId") Integer userId);

    /**
     * 政府 查看公司详情
     * @param companyId
     * @return
     */
    User queryCompanyDetail(@Param("companyId") Integer companyId);

    /**
     * 政府学习中心 获取 公司列表
     * @param cityIds
     * @param companyName
     * @return
     */
    List<User> queryUserForGovernment(@Param("cityIds") List<Integer> cityIds,@Param("companyName") String companyName,
                                      @Param("limit") Integer limit,@Param("limitSize") Integer limitSize);

    /**
     * 政府学习中心 获取 公司列表
     * @param cityIds
     * @param companyName
     * @return
     */
    Integer countUserForGovernment(@Param("cityIds") List<Integer> cityIds,@Param("companyName") String companyName);

    /**
     * 通过公司ID 动态查询 公司下的用户
     * @param companyId 公司ID
     * @param showName 名字
     * @param pid 身份证
     * @param phone 电话
     * @param IsAllocation  是否查询分配课程的用户  0：否  1：是
     * @return
     */
    List<User> queryUserByCompanyId(@Param("companyId") Integer companyId,@Param("searchParams") String searchParams,
                                    @Param("IsAllocation") Integer IsAllocation,
                                  @Param("orderId") Integer orderId,@Param("limit") Integer limit,@Param("limitSize") Integer limitSize,
                                    @Param("curriculumId") Integer curriculumId);

    /**
     * 获取公司列表
     * @return
     */
    List<User> queryCompanyList();

    /**
     * 获取 游离状态下的用户
     * @param phone
     * @param pid
     * @param limit
     * @param limitSize
     * @return
     */
    List<User> queryNoCompanyUser(@Param("phone") String phone,@Param("pid") String pid,
                                  @Param("limit") Integer limit,@Param("limitSize") Integer limitSize,
                                  @Param("cityId") Integer cityId,@Param("levelType") Integer levelType);


    /**
     * 后台获取 用户列表
     * @param map
     * @return
     */
    List<User> queryUserList(Map<String,Object> map);

    /**
     * 后台获取用户 总数
     * @param map
     * @return
     */
    Integer countUserList(Map<String,Object> map);


    /**
     * 新增用户
     * @param user
     * @return
     */
    Integer addUser(User user);

    /**
     *  批量新增用户
     * @param users
     * @return
     */
    Integer batchAddUser(@Param("users") List<User> users);


    /**
     * 通过ID 更新用户不为null的字段
     * @param user
     * @return
     */
    Integer updateUser(@Param("user") User user);

    /**
     * 批量更新用户 通过ID
     * @param users
     * @return
     */
    Integer batchUpdateUser(@Param("users") List<User> users);

    /**
     * 通过ID 查询用户 基础字段信息
     * @param id
     * @return
     */
    User queryBaseInfoById(@Param("id") Integer id);

    /**
     *  通过电话号码 查询用户
     * @param phone
     * @return
     */
    User queryUserByPhone(@Param("phone") String phone);

    /**
     * 通过电话号码 查询用户基础信息
     * @param phone
     * @return
     */
    User queryBaseUserByPhone(@Param("phone") String phone);

    /**
     *  当参数不为空时 根据父级 或者 公司用户  查询 公司下的用户集合
     *  当参数为空时 查询所有 游离状态的用户
     * @param parentId
     * @return
     */
    List<User> queryUserByParent(@Param("parentId") Integer parentId,@Param("userName") String userName,
                                 @Param("userIds") List<Integer> userIds);

    /**
     * 通过角色ID 查询用户集合
     * @param roleId
     * @return
     */
    List<User> queryUserByRoleId(@Param("roleId") Integer roleId);

    /**
     * 通过 公司 查询公司用户
     * @param companyName 公司名称
     * @return
     */
    User queryUserByCompany(@Param("companyName") String companyName);

    /**
     * 通过各种条件 查询用户集合
     * @param map
     * @return
     */
    List<User> queryUserByItems(Map<String,Object> map);

    /**
     * 通过各种条件 统计 用户数量 暂用于分页
     * @param map
     * @return
     */
    Integer countUserByItems(Map<String,Object> map);

    /**
     * 用过ID 清空 设备类型 以及 设备注册ID
     * @param id
     * @return
     */
    Integer clearDeviceInfo(@Param("id") Integer id);


    /**
     * 后台页面 分页 通过各种条件 查询用户集合
     * @param map map
     * @return
     */
    List<User> listForAdmin(Map<String , Object> map);

    /**
     * 后台页面 统计 用户数量
     * @param map map
     * @return
     */
    int listForAdminCount(Map<String , Object> map);

    /**
     * 后台页面 分页 通过各种条件 查询用户集合(档案管理)
     * @param map map
     * @return
     */
    List<User> listForAdminRecord(Map<String , Object> map);
 /**
     * 后台页面 分页 通过各种条件 查询用户集合(档案管理)
     * @param map map
     * @return
     */
    List<User> listForWebRecord(Map<String , Object> map);

    /**
     * 后台页面 统计 用户数量(档案管理)
     * @param map map
     * @return
     */
    int listForAdminRecordCount(Map<String , Object> map);


    /**
     * 通过ID 查询用户 详情 包含公司名  地区
     * @param id
     * @return
     */
    User info(@Param("id") Integer id);


    /**
     * 分页 通过手机号集合 订单id 批量查询未分配此课程的用户(未分配课程的用户)
     * @param map
     * @return
     */
    List<User> findUserByPhone2(Map<String , Object> map);

    /**
     * 后台页面 统计 通过手机号集合 订单id 批量查询未分配此课程的用户(未分配课程的用户)
     * @param map map
     * @return
     */
    int findUserByPhoneCount(Map<String , Object> map);


    /**
     * 根据 个人：身份证 政府：机构代码 企业：营业执照编码 查询用户
     * @param pid
     * @return
     */
    User getByPid(@Param("pid") String pid ,@Param("roleId") Integer roleId );

    /**
     * 根据 个人：身份证 政府：机构代码 企业：营业执照编码 查询用户
     * @param pids
     * @return
     */
    List<User> getByPids(@Param("pids") List<String> pids ,@Param("roleId") Integer roleId );

    /**
     * 根据角色删除用户
     * @param roleId
     */
    void deleteByRoleId(@Param("roleId") Integer roleId);


    /**
     * 与公司脱离关系 与上级用户解除关联
     * @param id
     */
    void removeParentId(@Param("id") Integer id);

    /**
     * 导出用户 及 课程(档案管理)
     * @param map map
     * @return
     */
    List<User> excelUser(Map<String , Object> map);

}
