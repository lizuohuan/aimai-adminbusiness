package com.magic.aimai.business.util;

import com.magic.aimai.business.entity.User;
import com.magic.aimai.business.entity.WebStatistics;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *  数据导出excel工具
 * Created by Eric Xie on 2017/3/15 0015.
 */

public class ExcelUtil {

    private static Logger logger = Logger.getLogger(ExcelUtil.class);


    /**
     * 企业用户 导出 员工列表
     * @param resp
     * @param excelName
     * @param users
     */
    public static void exportExcelUser(HttpServletResponse resp, String excelName,List<User> users){

        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(); //创建excel文件
        HSSFSheet sheet = hssfWorkbook.createSheet(); // 创建工作簿
        // 设置样式
        HSSFCellStyle cellStyle = hssfWorkbook.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        HSSFRow oneRow = sheet.createRow(0);
        oneRow.createCell(0).setCellValue("员工名");
        oneRow.createCell(1).setCellValue("电话");
        oneRow.createCell(2).setCellValue("部门");
        oneRow.createCell(3).setCellValue("职位");
        oneRow.createCell(4).setCellValue("身份证");
        oneRow.createCell(5).setCellValue("行业");
        oneRow.createCell(6).setCellValue("最新学习的课程");
        oneRow.createCell(7).setCellValue("课程类型");
        oneRow.createCell(8).setCellValue("培训阶段");
        oneRow.createCell(9).setCellValue("已学习完课时数量");
        oneRow.createCell(10).setCellValue("学习进度");
        oneRow.createCell(11).setCellValue("考核状态");
        oneRow.createCell(12).setCellValue("通过时间");
        for (int i = 0; i < users.size(); i++) {
            HSSFRow row = sheet.createRow(i+1);
            row.createCell(0).setCellValue(null == users.get(i).getShowName() ? "暂无" : users.get(i).getShowName());
            row.createCell(1).setCellValue(null == users.get(i).getPhone() ? "暂无" : users.get(i).getPhone());
            row.createCell(2).setCellValue(null == users.get(i).getDepartmentName() ? "暂无" : users.get(i).getDepartmentName());
            row.createCell(3).setCellValue(null == users.get(i).getJobTitle() ? "暂无" : users.get(i).getJobTitle());
            row.createCell(4).setCellValue(null == users.get(i).getPid() ? "暂无" : users.get(i).getPid());
            row.createCell(5).setCellValue(null == users.get(i).getTradeName() ? "暂无" : users.get(i).getTradeName());
            row.createCell(6).setCellValue(null == users.get(i).getCurriculumName() ? "暂无" : users.get(i).getCurriculumName());
            row.createCell(7).setCellValue(null == users.get(i).getCurriculumTypeName() ? "暂无" : users.get(i).getCurriculumTypeName());
            row.createCell(8).setCellValue(null == users.get(i).getStageName() ? "暂无" : users.get(i).getStageName());
            row.createCell(9).setCellValue(null == users.get(i).getFinishCourseWareNum() ? "暂无" : users.get(i).getFinishCourseWareNum().toString());
            row.createCell(10).setCellValue(null == users.get(i).getTotalCourseWareNum() || null == users.get(i).getFinishCourseWareNum() ? "暂无" : users.get(i).getFinishCourseWareNum().toString() + "/" + users.get(i).getTotalCourseWareNum().toString());
            row.createCell(11).setCellValue(null == users.get(i).getIsPass() ? "暂无" : users.get(i).getIsPass() == 0 ? "未通过" : "通过");
            row.createCell(12).setCellValue(null == users.get(i).getPassTime() ? "暂无" : Timestamp.DateTimeStamp(users.get(i).getPassTime(),"yyyy-MM-dd HH:mm:ss"));
        }
        OutputStream out = null;
        try {
            out = resp.getOutputStream();
            resp.reset();
            resp.setHeader("Content-disposition", "attachment; filename="+excelName+".xls");
            resp.setContentType("application/msexcel");
            hssfWorkbook.write(out);
            out.close();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }

    }


    /**
     * 政府导出 excel表
     * @param resp
     * @param excelName
     * @param companyList
     */
    public static void drawExcel(HttpServletResponse resp, String excelName,
                                 List<WebStatistics> companyList){
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(); //创建excel文件
        HSSFSheet sheet = hssfWorkbook.createSheet(); // 创建工作簿
        // 设置样式
        HSSFCellStyle cellStyle = hssfWorkbook.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        HSSFRow oneRow = sheet.createRow(0);
        oneRow.createCell(0).setCellValue("企业");
        oneRow.createCell(1).setCellValue("安全人数");
        oneRow.createCell(2).setCellValue("参培人数");
        oneRow.createCell(3).setCellValue("全员培训认证通过人员");
        oneRow.createCell(4).setCellValue("三项培训认证通过人员");
        oneRow.createCell(5).setCellValue("所在地");
        oneRow.createCell(6).setCellValue("行业");
        oneRow.createCell(7).setCellValue("年度");

        int companyNum = 0;
        int safeNum = 0;
        int allSafeNum = 0;
        int threeSafeNum = 0;
        int joinNum = 0;
        for (WebStatistics company : companyList) {
            companyNum++;
            safeNum += company.getSafeNum();
            joinNum += company.getJoinNum();
            allSafeNum += company.getAllSafeNum();
            threeSafeNum += company.getThreeSafeNum();
        }
        WebStatistics temp = new WebStatistics();
        temp.setAllSafeNum(allSafeNum);
        temp.setShowName(companyNum+"");
        temp.setSafeNum(safeNum);
        temp.setJoinNum(joinNum);
        temp.setThreeSafeNum(threeSafeNum);
        companyList.add(0,temp);
        // 生成表格
        for (int i = 0; i < companyList.size(); i++) {
            HSSFRow row = sheet.createRow(i+1);
            HSSFCell one = row.createCell(0);
            one.setCellValue(companyList.get(i).getShowName());
            HSSFCell two = row.createCell(1);
            two.setCellValue(companyList.get(i).getSafeNum());
            HSSFCell three = row.createCell(2);
            three.setCellValue(companyList.get(i).getJoinNum());
            HSSFCell four = row.createCell(3);
            four.setCellValue(companyList.get(i).getAllSafeNum());
            HSSFCell five = row.createCell(4);
            five.setCellValue(companyList.get(i).getThreeSafeNum());
            if (i > 0) {
                HSSFCell six = row.createCell(5);
                six.setCellValue(companyList.get(i).getCityName());
                HSSFCell seven = row.createCell(6);
                seven.setCellValue(companyList.get(i).getTradeName());
                HSSFCell eight = row.createCell(7);
                eight.setCellValue(Timestamp.DateTimeStamp(companyList.get(i).getYear(),"yyyy")+"年");
            }
        }
        OutputStream out = null;
        try {
            out = resp.getOutputStream();
            resp.reset();
            resp.setHeader("Content-disposition", "attachment; filename="+excelName+".xls");
            resp.setContentType("application/msexcel");
            hssfWorkbook.write(out);
            out.close();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }

    }
}
