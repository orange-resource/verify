package com.orange.verify.adminweb.controller.admin;

import cn.hutool.core.util.StrUtil;
import com.orange.verify.adminweb.config.annotation.ApiAuth;
import com.orange.verify.adminweb.service.SoftVersionService;
import com.orange.verify.api.common.constant.ApiAuthConstant;
import com.orange.verify.api.common.response.Response;
import com.orange.verify.api.entity.po.SoftVersionPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller(value = "SoftVersionControllerAdmin")
@RequestMapping(value = "/admin/softVersion")
public class SoftVersionController {

    @Autowired
    private SoftVersionService softVersionService;

    @ApiAuth(type = ApiAuthConstant.ADMIN_PLATFORM)
    @PostMapping(value = "/getDetail")
    @ResponseBody
    public Response getDetail(String softId) {
        return softVersionService.getDetail(softId);
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN_PLATFORM)
    @PostMapping(value = "/create")
    @ResponseBody
    public Response create(SoftVersionPO softVersionPO) {
        return softVersionService.create(softVersionPO);
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN_PLATFORM)
    @PostMapping(value = "/update")
    @ResponseBody
    public Response update(SoftVersionPO softVersionPO) {
        return softVersionService.update(softVersionPO);
    }

}
