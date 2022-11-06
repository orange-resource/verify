package com.orange.verify.adminweb.controller.admin;

import com.orange.verify.adminweb.config.annotation.ApiAuth;
import com.orange.verify.adminweb.service.SoftAccountService;
import com.orange.verify.api.common.constant.ApiAuthConstant;
import com.orange.verify.api.common.response.Response;
import com.orange.verify.api.common.response.RspCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@Controller(value = "SoftAccountControllerAdmin")
@RequestMapping(value = "/admin/account", produces = "application/json")
public class SoftAccountController {

    @Autowired
    private SoftAccountService softAccountService;

    @ApiAuth(type = ApiAuthConstant.ADMIN_PLATFORM)
    @PostMapping(value = "/page")
    @ResponseBody
    public Response page(String softId, String createIp,String username, Integer offset, Integer limit) {
        return softAccountService.page(softId, createIp, username, offset, limit);
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN_PLATFORM)
    @PostMapping(value = "/count")
    @ResponseBody
    public Response getCount() {
        return softAccountService.getCount();
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN_PLATFORM)
    @PostMapping(value = "/blacklist")
    @ResponseBody
    public Response blacklist(String id, Integer blacklist) {
        return softAccountService.blacklist(id, blacklist);
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN_PLATFORM)
    @PostMapping(value = "/delete")
    @ResponseBody
    public Response delete(String id) {
        return softAccountService.delete(id);
    }

}
