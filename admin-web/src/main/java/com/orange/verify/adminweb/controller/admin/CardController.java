package com.orange.verify.adminweb.controller.admin;

import com.orange.verify.adminweb.config.annotation.ApiAuth;
import com.orange.verify.adminweb.service.CardService;
import com.orange.verify.api.common.constant.ApiAuthConstant;
import com.orange.verify.api.common.response.Response;
import com.orange.verify.api.entity.vo.admin.CardPageParamVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller(value = "CardControllerAdmin")
@RequestMapping(value = "/admin/card", produces = "application/json")
public class CardController {

    @Autowired
    private CardService cardService;

    @ApiAuth(type = ApiAuthConstant.ADMIN_PLATFORM)
    @PostMapping(value = "/page")
    @ResponseBody
    public Response page(CardPageParamVO cardPageParamVO) {
        return cardService.page(cardPageParamVO);
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN_PLATFORM)
    @PostMapping(value = "/count")
    @ResponseBody
    public Response getCount() {
        return cardService.getCount();
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN_PLATFORM)
    @PostMapping(value = "/create")
    @ResponseBody
    public Response create(String cardTypeId, Integer count) {
        return cardService.batchCreate(cardTypeId, count);
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN_PLATFORM)
    @PostMapping(value = "/close")
    @ResponseBody
    public Response close(String cardId, Integer closeStatus) {
        return cardService.close(cardId, closeStatus);
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN_PLATFORM)
    @PostMapping(value = "/sell")
    @ResponseBody
    public Response sell(String cardId, Integer sellStatus) {
        return cardService.sell(cardId, sellStatus);
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN_PLATFORM)
    @PostMapping(value = "/batchSell")
    @ResponseBody
    public Response batchSell(@RequestBody List<String> cardIdList) {
        return cardService.batchSell(cardIdList);
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN_PLATFORM)
    @PostMapping(value = "/batchDelete")
    @ResponseBody
    public Response batchDelete(@RequestBody List<String> cardIdList) {
        return cardService.batchDelete(cardIdList);
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN_PLATFORM)
    @PostMapping(value = "/delete")
    @ResponseBody
    public Response delete(String cardId) {
        return cardService.delete(cardId);
    }

}
