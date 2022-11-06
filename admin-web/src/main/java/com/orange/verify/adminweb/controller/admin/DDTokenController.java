package com.orange.verify.adminweb.controller.admin;

import com.orange.verify.adminweb.config.annotation.ApiAuth;
import com.orange.verify.adminweb.service.DDTokenService;
import com.orange.verify.api.common.constant.ApiAuthConstant;
import com.orange.verify.api.common.response.Response;
import com.orange.verify.api.entity.po.DDTokenPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/admin/ddToken", produces = "application/json")
public class DDTokenController {

    @Autowired
    private DDTokenService ddTokenService;

    @ApiAuth(type = ApiAuthConstant.ADMIN_PLATFORM)
    @PostMapping(value = "/getDetail")
    @ResponseBody
    public Response getDetail() {
        return ddTokenService.getDetail();
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN_PLATFORM)
    @PostMapping(value = "/update")
    @ResponseBody
    public Response update(DDTokenPO ddTokenPO) {
        return ddTokenService.update(ddTokenPO);
    }

}
