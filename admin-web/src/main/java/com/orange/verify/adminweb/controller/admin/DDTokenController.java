package com.orange.verify.adminweb.controller.admin;

import com.orange.verify.adminweb.config.annotation.ApiAuth;
import com.orange.verify.adminweb.service.CardTypeService;
import com.orange.verify.adminweb.service.DDTokenService;
import com.orange.verify.api.common.constant.ApiAuthConstant;
import com.orange.verify.api.common.response.Response;
import com.orange.verify.api.entity.po.CardTypePO;
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
    @PostMapping(value = "/getList")
    @ResponseBody
    public Response getList() {
        return ddTokenService.getList();
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN_PLATFORM)
    @PostMapping(value = "/getDetail")
    @ResponseBody
    public Response getDetail(String id) {
        return ddTokenService.getDetail(id);
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN_PLATFORM)
    @PostMapping(value = "/create")
    @ResponseBody
    public Response create(DDTokenPO ddTokenPO) {
        return ddTokenService.create(ddTokenPO);
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN_PLATFORM)
    @PostMapping(value = "/update")
    @ResponseBody
    public Response update(DDTokenPO ddTokenPO) {
        return ddTokenService.update(ddTokenPO);
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN_PLATFORM)
    @PostMapping(value = "/delete")
    @ResponseBody
    public Response delete(String id) {
        return ddTokenService.delete(id);
    }

}
