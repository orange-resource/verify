package com.orange.verify.adminweb.controller.admin;

import com.orange.verify.adminweb.config.annotation.ApiAuth;
import com.orange.verify.adminweb.service.SoftLeaveMessageService;
import com.orange.verify.api.common.constant.ApiAuthConstant;
import com.orange.verify.api.common.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 软件留言
 */
@Controller(value = "SoftLeaveMessageControllerAdmin")
@RequestMapping(value = "/admin/softLeaveMessage")
public class SoftLeaveMessageController {

    @Autowired
    private SoftLeaveMessageService softLeaveMessageService;

    @ApiAuth(type = ApiAuthConstant.ADMIN_PLATFORM)
    @PostMapping(value = "/page")
    @ResponseBody
    public Response page(String softId, Integer offset, Integer limit) {
        return softLeaveMessageService.page(softId, offset, limit);
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN_PLATFORM)
    @PostMapping(value = "/delete")
    @ResponseBody
    public Response delete(String id) {
        return softLeaveMessageService.delete(id);
    }

}
