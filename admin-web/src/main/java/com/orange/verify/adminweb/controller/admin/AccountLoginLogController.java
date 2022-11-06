package com.orange.verify.adminweb.controller.admin;

import com.orange.verify.adminweb.config.annotation.ApiAuth;
import com.orange.verify.adminweb.service.AccountLoginLogService;
import com.orange.verify.api.common.constant.ApiAuthConstant;
import com.orange.verify.api.common.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/admin/accountLoginLog", produces = "application/json")
public class AccountLoginLogController {

    @Autowired
    private AccountLoginLogService accountLoginLogService;

    @ApiAuth(type = ApiAuthConstant.ADMIN_PLATFORM)
    @PostMapping(value = "/getBeforeData")
    @ResponseBody
    public Response getBeforeData(String softId) {
        return accountLoginLogService.getBeforeData(softId);
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN_PLATFORM)
    @PostMapping(value = "/page")
    @ResponseBody
    public Response page(String softId, Integer offset, Integer limit) {
        return accountLoginLogService.page(softId, offset, limit);
    }

}
