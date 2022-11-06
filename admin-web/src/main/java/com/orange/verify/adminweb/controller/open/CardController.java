package com.orange.verify.adminweb.controller.open;

import com.orange.verify.adminweb.service.CardService;
import com.orange.verify.api.common.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Validated
@Controller(value = "CardControllerOpen")
@RequestMapping(value = "/open/card", produces = "application/json")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping(value = "/getCardTimeLimit")
    @ResponseBody
    public Response getCardTimeLimit(@Valid @NotBlank(message = "参数不合法") String username,
                                     @Valid @NotBlank(message = "参数不合法") String password,
                                     @Valid @NotBlank(message = "参数不合法") String softId) {
        return cardService.getCardTimeLimit(username, password, softId);
    }

}
