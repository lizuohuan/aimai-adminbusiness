package com.magic.aimai.business.service;

import com.magic.aimai.business.entity.Banner;
import com.magic.aimai.business.entity.PageArgs;
import com.magic.aimai.business.entity.PageList;
import com.magic.aimai.business.exception.InterfaceCommonException;
import com.magic.aimai.business.mapper.IBannerMapper;
import com.magic.aimai.business.util.StatusConstant;
import com.magic.aimai.business.util.Timestamp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Banner Service
 * Created by Eric Xie on 2017/7/24 0024.
 */
@Service
public class BannerService {

    @Resource
    private IBannerMapper bannerMapper;


    public void addBanner(Banner banner) throws Exception{


        if (null == banner.getIsBanner()) {
            throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL,"广告/banner类型不能为空");
        }
        if (banner.getIsBanner().equals(0)) {
            if (null == banner.getLocation()) {
                throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL,"广告显示位置不能为空");
            }
            Banner b = bannerMapper.isHaveAd(banner.getLocation());
            if (null != b) {
                throw new InterfaceCommonException(StatusConstant.Fail_CODE,"此广告位已存在，请直接修改此广告位的内容");
            }
        }

        if(null == banner.getValidity()){
            throw new InterfaceCommonException(StatusConstant.Fail_CODE,"有效期时间不能为空");
        }
        banner.setValidity(Timestamp.setDateHH(banner.getValidity()));
        bannerMapper.addBanner(banner);
    }

    public void update(Banner banner) throws Exception{

        if (null == banner.getIsBanner()) {
            throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL,"广告/banner类型不能为空");
        }
        if (banner.getIsBanner().equals(0)) {
            if (null == banner.getLocation()) {
                throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL,"广告显示位置不能为空");
            }
            Banner b = bannerMapper.isHaveAd(banner.getLocation());
            if (null != b && !b.getId().equals(banner.getId())) {
                throw new InterfaceCommonException(StatusConstant.Fail_CODE,"此广告位已存在，请直接修改此广告位的内容");
            }
        }
        if(null == banner.getValidity()){
            throw new InterfaceCommonException(StatusConstant.Fail_CODE,"有效期时间不能为空");
        }
        banner.setValidity(Timestamp.setDateHH(banner.getValidity()));
        bannerMapper.updateBanner(banner);
    }


    public Banner queryBannerById(Integer bannerId){
        return bannerMapper.queryBannerById(bannerId);
    }


    public List<Banner> queryBannerList(Integer isBanner){
        return bannerMapper.queryBannerList(new Date(),isBanner);
    }

    /**
     * 后台页面 分页获取banner
     * @param pageArgs 分页属性
     * @param title 标题
     * @param editor 责任编辑人
     * @param isLink 是否是外链
     * @param startTime  创建开始时间
     * @param endTime 创建结束时间
     * @param validityStartTime 到期开始时间
     * @param validityEndTime 到期结束时间
     * @param isBanner PC广告: 0  banner: 1
     * @return
     */
    public PageList<Banner> listForAdmin(PageArgs pageArgs , String title ,  String editor , Integer isLink ,
                                         Date startTime , Date endTime ,Date validityStartTime , Date validityEndTime ,Integer isBanner) {
        PageList<Banner> pageList = new PageList<Banner>();
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("title",title);
        map.put("editor",editor);
        map.put("isLink",isLink);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("validityStartTime",validityStartTime);
        map.put("validityEndTime",validityEndTime);
        map.put("isBanner",isBanner);
        int count = bannerMapper.listForAdminCount(map);
        if (count > 0) {
            map.put("pageArgs",pageArgs);
            pageList.setList(bannerMapper.listForAdmin(map));
        }
        pageList.setTotalSize(count);
        return pageList;
    }


    public void delBanner(Integer id) {
        bannerMapper.delBanner(id);
    }
}
