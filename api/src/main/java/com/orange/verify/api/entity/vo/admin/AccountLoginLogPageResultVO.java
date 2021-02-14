package com.orange.verify.api.entity.vo.admin;

import com.orange.verify.api.entity.po.AccountLoginLogPO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountLoginLogPageResultVO extends AccountLoginLogPO {

    private String softName;

    private String accountName;

}
