package com.orange.verify.adminweb.controller.admin;

import com.orange.verify.adminweb.config.annotation.ApiAuth;
import com.orange.verify.adminweb.service.CardTypeService;
import com.orange.verify.api.common.constant.ApiAuthConstant;
import com.orange.verify.api.common.response.Response;
import com.orange.verify.api.entity.po.CardTypePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/admin/cardType", produces = "application/json")
public class CardTypeController {

    @Autowired
    private CardTypeService cardTypeService;

    @ApiAuth(type = ApiAuthConstant.ADMIN_PLATFORM)
    @PostMapping(value = "/page")
    @ResponseBody
    public Response page(String softId, Integer offset, Integer limit) {
        return cardTypeService.page(softId, offset, limit);
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN_PLATFORM)
    @PostMapping(value = "/getDetail")
    @ResponseBody
    public Response getDetail(String cardTypeId) {
        return cardTypeService.getDetail(cardTypeId);
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN_PLATFORM)
    @PostMapping(value = "/create")
    @ResponseBody
    public Response create(CardTypePO cardTypePO) {
        return cardTypeService.create(cardTypePO);
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN_PLATFORM)
    @PostMapping(value = "/update")
    @ResponseBody
    public Response update(CardTypePO cardTypePO) {
        return cardTypeService.update(cardTypePO);
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN_PLATFORM)
    @PostMapping(value = "/delete")
    @ResponseBody
    public Response delete(String cardTypeId) {
        return cardTypeService.delete(cardTypeId);
    }

}
