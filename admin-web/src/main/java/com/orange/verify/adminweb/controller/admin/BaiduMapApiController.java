package com.orange.verify.adminweb.controller.admin;

import com.orange.verify.adminweb.config.annotation.ApiAuth;
import com.orange.verify.adminweb.service.BaiduMapService;
import com.orange.verify.api.common.constant.ApiAuthConstant;
import com.orange.verify.api.common.response.Response;
import com.orange.verify.api.entity.po.BaiduMapTokenPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 百度 地理 配置
 */
@Controller
@RequestMapping(value = "/admin/baiduMapToken", produces = "application/json")
public class BaiduMapApiController {

    @Autowired
    private BaiduMapService baiduMapService;

    @ApiAuth(type = ApiAuthConstant.ADMIN_PLATFORM)
    @PostMapping(value = "/getDetail")
    @ResponseBody
    public Response getDetail() {
        return baiduMapService.getDetail();
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN_PLATFORM)
    @PostMapping(value = "/update")
    @ResponseBody
    public Response update(BaiduMapTokenPO baiduMapTokenPO) {
        return baiduMapService.update(baiduMapTokenPO);
    }

}


