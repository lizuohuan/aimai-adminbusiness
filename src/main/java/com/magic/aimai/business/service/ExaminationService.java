package com.magic.aimai.business.service;

import com.magic.aimai.business.entity.*;
import com.magic.aimai.business.enums.Common;
import com.magic.aimai.business.enums.PaperEnum;
import com.magic.aimai.business.exception.InterfaceCommonException;
import com.magic.aimai.business.mapper.*;
import com.magic.aimai.business.util.ExcelReader;
import com.magic.aimai.business.util.StatusConstant;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * 考题 Service
 * Created by Eric Xie on 2017/7/21 0021.
 */
@Service
public class ExaminationService {

    @Resource
    private IExaminationMapper examinationMapper;
    @Resource
    private IExaminationItemsMapper examinationItemsMapper;
    @Resource
    private IPaperMapper paperMapper;
    @Resource
    private ICityMapper cityMapper;
    @Resource
    private IExaminationPaperMapper examinationPaperMapper;

    /**
     * 通过ID 集合 查询试题 题解
     * @param ids
     * @return
     */
    public List<Examination> queryErrorExaminationByIds(List<Integer> ids){
        return examinationMapper.queryErrorExaminationByIds(ids);
    }

    /**
     * 获取错题集合
     * @param userId
     * @param orderId
     * @param courseWareId
     * @param pageNO
     * @param pageSize
     * @return
     */
    public List<Examination> queryErrorExamination(Integer userId,Integer orderId,Integer courseWareId,
                                                   Integer pageNO,Integer pageSize){
        return examinationMapper.queryErrorExamination(orderId,userId,courseWareId,(pageNO - 1) * pageSize,pageSize);
    }

    public Examination queryBaseExamination(Integer examinationId){
        return examinationMapper.queryBaseExamination(examinationId);
    }

    /**
     * 查询 考题选项
     * @param examinationId
     * @param pageArgs
     * @return
     */
    public PageList<ExaminationItems> queryExaminationItems(Integer examinationId,PageArgs pageArgs){
        PageList<ExaminationItems> pageList = new PageList<ExaminationItems>();
        Integer count = examinationItemsMapper.countExaminationItemsByExamination(examinationId);
        if(count > 0 ){
            pageList.setList(examinationItemsMapper.queryExaminationItemsByExamination(examinationId,pageArgs.getPageStart(),
                    pageArgs.getPageSize()));
        }
        pageList.setTotalSize(count);
        return pageList;
    }


    /**
     * 新增考题
     * @param examination 考题
     * @throws Exception
     */
    @Transactional
    public void addExamination(Examination examination) throws Exception{
        examinationMapper.addExamination(examination);
        if(2 == examination.getType()){

            List<ExaminationItems> list = new ArrayList<ExaminationItems>();

            // 如果是判断题 新增 两个选项
            ExaminationItems item1 = new ExaminationItems();
            item1.setExaminationId(examination.getId());
            item1.setItemTitle("--");
            item1.setIsCorrect(Common.YES.ordinal());
            item1.setSortNum(1);
            list.add(item1);
            ExaminationItems item2 = new ExaminationItems();
            item2.setExaminationId(examination.getId());
            item2.setItemTitle("--");
            item2.setIsCorrect(Common.NO.ordinal());
            item2.setSortNum(2);
            list.add(item2);

            examinationItemsMapper.batchAddExaminationItems(list);
        }
    }


    /**
     * 新增考题 选线
     * @param items 考题选项
     * @throws Exception
     */
    public void addExaminationItems(ExaminationItems items) throws Exception{
        Examination examination = examinationMapper.queryBaseExaminationById(items.getExaminationId());
        if(null == examination){
            throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST,"试题不存在");
        }
        if(examination.getType() == 0 && null != examination.getExaminationItemsList() && Common.YES.ordinal() == items.getIsCorrect()){
            // 如果是单选题
            for (ExaminationItems item : examination.getExaminationItemsList()) {
                if(item.getIsCorrect() == Common.YES.ordinal()){
                    throw new InterfaceCommonException(StatusConstant.Fail_CODE,"只能设置一个正确答案");
                }
            }
        }
        examinationItemsMapper.addExaminationItems(items);
    }

    /**
     * 更新考题
     * @param examination 考题
     * @param itemList 考题选项 以及 答案等信息
     * @throws Exception
     */
    @Transactional
    public void updateExamination(Examination examination, List<ExaminationItems> itemList) throws Exception{
        examinationMapper.updateExamination(examination);
        if(null != itemList){
            //先删除原有答案 重新添加
            examinationItemsMapper.deteleByExaminationId(examination.getId());
            for (ExaminationItems item : itemList) {
                item.setExaminationId(examination.getId());
            }
            examinationItemsMapper.batchAddExaminationItems(itemList);
        }
    }

    /**
     * 更新考题单项答案信息
     * @param examinationItems 答案
     * @throws Exception
     */
    @Transactional
    public void updateExaminationItems(ExaminationItems examinationItems) throws Exception{
        Examination examination = examinationMapper.queryBaseExaminationById(examinationItems.getExaminationId());
        if(null == examination){
            throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST,"试题不存在");
        }
        if(examination.getType() == 0 && null != examination.getExaminationItemsList() && Common.YES.ordinal() == examinationItems.getIsCorrect()){
            // 如果是单选题 或者判断题
            for (ExaminationItems item : examination.getExaminationItemsList()) {
                if(item.getIsCorrect() == Common.YES.ordinal() && !item.getId().equals(examinationItems.getId())){
                    throw new InterfaceCommonException(StatusConstant.Fail_CODE,"只能设置一个正确答案");
                }
            }

        }
        // 如果是判断题
        if (examination.getType() == 2) {

            //0:只能设置一个错误答案 1:只能设置一个正确答案 2:通过
            int flag = 0;

            for (ExaminationItems item : examination.getExaminationItemsList()) {


                if (examinationItems.getIsCorrect() == Common.NO.ordinal() && item.getIsCorrect().equals(examinationItems.getIsCorrect())) {
                    if (!item.getId().equals(examinationItems.getId())) {
                        throw new InterfaceCommonException(StatusConstant.Fail_CODE,"只能设置一个错误答案");
                    }
                }
                if (examinationItems.getIsCorrect() == Common.YES.ordinal() && item.getIsCorrect().equals(examinationItems.getIsCorrect())) {
                    if (!item.getId().equals(examinationItems.getId())) {
                        throw new InterfaceCommonException(StatusConstant.Fail_CODE,"只能设置一个正确答案");
                    }
                }
            }
        }
        examinationItemsMapper.updateExaminationItems(examinationItems);
    }


    /**
     * 答案详情
     * @param examinationItemsId 答案id
     * @throws Exception
     */
    public ExaminationItems examinationItemsInfo(Integer examinationItemsId) throws Exception{
       return examinationItemsMapper.info(examinationItemsId);
    }


    /**
     * 后台页面 分页获取考题 包含了 考题的选项以及答案信息
     * @param pageArgs 分页属性
     * @param title 考题名
     * @param type 试题类型 0:单选题 1:多选题  2:判断题
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    public PageList<Examination> listForAdmin(PageArgs pageArgs , String title , Integer type , Date startTime ,Date endTime) {
        PageList<Examination> pageList = new PageList<Examination>();
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("title",title);
        map.put("type",type);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        int count = examinationMapper.listForAdminCount(map);
        if (count > 0) {
            map.put("pageArgs",pageArgs);
            pageList.setList(examinationMapper.listForAdmin(map));
        }
        pageList.setTotalSize(count);
        return pageList;
    }



    /**
     * 后台页面 分页获取考题 包含了 考题的选项以及答案信息
     * @param pageArgs 分页属性
     * @return
     */
    public PageList<Examination> listForAdmin(PageArgs pageArgs , Map<String,Object> params) throws Exception {
        PageList<Examination> pageList = new PageList<Examination>();

        if(null != params.get("paperId")){
            Integer paperId = (Integer)params.get("paperId");
            Paper paper = paperMapper.queryBasePaperById(paperId);
            if(null == paper){
                throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST,"试卷不存在");
            }
            params.put("targetId",paper.getTargetId());
            params.put("paperType",paper.getType());
            if(PaperEnum.Exercises.ordinal() != paper.getType()){
                // 如果不是 练习试卷 查询该课程下所有的 习题
                params.put("curriculumIds",paper.getTargetId());
            }else{
                params.put("curriculumIds",null);
            }
        }
        int count = examinationMapper.listForAdminCount(params);
        if (count > 0) {
            params.put("pageArgs",pageArgs);
            pageList.setList(examinationMapper.listForAdmin(params));
        }
        pageList.setTotalSize(count);
        return pageList;
    }


    /**
     * 考题详情
     * @param id 考题id
     * @return
     */
    public Examination info(Integer id) {
        Examination examination = examinationMapper.info(id);
        //封装省市区
        if (null != examination.getCityId()) {
            City city = cityMapper.getThreeId(examination.getCityId());
            JSONArray jsonArray = new JSONArray();
            jsonArray.add(city.getProvinceId());
            jsonArray.add(city.getTownId());
            jsonArray.add(city.getId());
            examination.setCityJsonAry(jsonArray);
        }
        return examination;
    }

    /**
     * 删除考题
     * @param id 考题id
     * @return
     */
    @Transactional
    public void delete(Integer id) {
        if (examinationPaperMapper.countExaminationPaperByExaminationId(id) > 0){
            throw new InterfaceCommonException(StatusConstant.OBJECT_EXIST,"试题已被绑定，不能删除");
        }
        examinationItemsMapper.deteleByExaminationId(id);
        examinationMapper.delete(id);
    }

    /**
     * 更新考题
     * @param examination 考题
     * @throws Exception
     */
    @Transactional
    public void update(Examination examination) throws Exception{
        examinationMapper.updateExamination(examination);
    }


    @Transactional
    public void addExcel(String urls) throws IOException {
        int HttpResult; // 服务器返回的状态
        URL url = new URL(urls); // 创建URL
        URLConnection urlConn = url.openConnection(); // 试图连接并取得返回状态码
        urlConn.connect();
        HttpURLConnection httpConn = (HttpURLConnection) urlConn;
        HttpResult = httpConn.getResponseCode();
        if (HttpResult != HttpURLConnection.HTTP_OK) // 不等于HTTP_OK说明连接不成功
            System.out.print("无法连接到");
        else {
            Map<Integer, List<String>> map = ExcelReader.readExcelContent(urlConn.getInputStream());

            List<Examination> list = new ArrayList<Examination>();
            for (Integer key : map.keySet()) {
                List<String> list1 = map.get(key);
                if (list1.size() < 4) {
                    throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL,"考题【"+list1.get(0) + "】请填写完整数据");
                }
                Examination e = new Examination();
                if (null == list1.get(1)) {
                    throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL,"考题【"+list1.get(0) + "】请填写考题类型");
                }
                if (null == list1.get(4)) {
                    throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL,"考题【"+list1.get(0) + "】请填写考题答案");
                }

                String title = list1.get(0);
                Integer type = Integer.parseInt(list1.get(1).split("\\.")[0]);
                String examinationKey = list1.get(2);
                String emphasis = list1.get(3);

                //题目
                e.setTitle(title);
                //类型
                e.setType(type);
                //题解
                e.setExaminationKey(examinationKey);
                //考点
                e.setEmphasis(emphasis);
                //答案
                String[] examinationItems = list1.get(4).split("\\|");

                if (type > 2 || type < 0) {
                    throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL,"考题【"+list1.get(0) + "】考题类型不正确");
                }
                if (type == 2 && examinationItems.length != 2) {
                    throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL,"考题【"+list1.get(0) + "】为判断题，有且只能为一个正确答案一个错误答案");
                }

                //正确答案数量
                int correctNum = 0;
                //错误答案的数量
                int errorNum = 0;
                List<ExaminationItems> eList = new ArrayList<ExaminationItems>();
                for (int i = 0 ; i < examinationItems.length ; i ++) {
                    String[] exItems = examinationItems[i]/*.replaceAll("，",",")*/.split(",");
                    if (exItems.length < 3) {
                        throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL,"考题【"+list1.get(0) + ":答案["+exItems[i]+"]】请填写完整数据");
                    }
                    ExaminationItems ex = new ExaminationItems();
                    //答案选项
                    String itemTitle = exItems[0];
                    if (null == itemTitle || itemTitle.equals("")) {
                        throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL,"考题【"+list1.get(0)+"】请填写答案");
                    }
                    //该选项是否是正确的 0:不是   1:是 该正确答案
                    if (null == exItems[1]) {
                        throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL,"考题【"+list1.get(0) + ":答案["+exItems[i]+"]】请填写0或1");
                    }
                    //该选项是否是正确的 0:不是   1:是 该正确答案
                    Integer isCorrect = Integer.parseInt(exItems[1]);
                    if (isCorrect < 0 || isCorrect > 1) {
                        throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL,"考题【"+list1.get(0) + ":答案["+exItems[i]+"]】请填写0或1");
                    }
                    //排序 升序排列
                    Integer sortNum = null;
                    if (null != exItems[2]) {
                        sortNum = Integer.parseInt(exItems[2]);
                    }
                    //记录正确答案
                    if (isCorrect == 1) {
                        correctNum++;
                    } else {
                        errorNum ++;
                    }

                    ex.setItemTitle(itemTitle);
                    ex.setIsCorrect(isCorrect);
                    ex.setSortNum(sortNum);
                    eList.add(ex);
                }

                if (type == 0 && correctNum > 1) {
                    throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL,"考题【"+list1.get(0) + "】为单选题，只允许有一个正确答案");
                }
                if (type == 2 && errorNum != 1 && correctNum != 1) {
                    throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL,"考题【"+list1.get(0) + "】为判断题，有且只能为一个正确答案一个错误答案");
                }
                e.setExaminationItemsList(eList);
                list.add(e);
            }
            examinationMapper.addList(list);

            List<ExaminationItems> exItems = new ArrayList<ExaminationItems>();

            for (Examination examination : list) {
                //为答案设置考题id
                for (ExaminationItems items : examination.getExaminationItemsList()) {
                    items.setExaminationId(examination.getId());
                    exItems.add(items);
                }
            }
            //批量新增考题答案
            examinationItemsMapper.batchAddExaminationItems(exItems);
            

            System.out.println(map);
        }
    }

}
