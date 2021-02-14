package com.orange.verify.api.entity.vo.admin;

import com.orange.verify.api.entity.po.AccountRegisterLogPO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRegisterLogPageResultVO extends AccountRegisterLogPO {

    private String softName;

    private String accountName;

}
