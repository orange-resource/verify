package com.orange.verify.adminweb.controller.admin;

import com.orange.verify.adminweb.config.annotation.ApiAuth;
import com.orange.verify.adminweb.service.AdminUserService;
import com.orange.verify.api.common.constant.ApiAuthConstant;
import com.orange.verify.api.common.response.Response;
import com.orange.verify.api.entity.po.AdminUserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/admin/user", produces = "application/json")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    @PostMapping(value = "/login")
    @ResponseBody
    public Response login(AdminUserPO adminUserPO) {
        return adminUserService.login(adminUserPO);
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN_PLATFORM)
    @PostMapping(value = "/logout")
    @ResponseBody
    public Response logout(@RequestHeader(ApiAuthConstant.HEADER_AUTHORIZATION) String token) {
        return adminUserService.logout(token);
    }

}
