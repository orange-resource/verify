package com.orange.verify.api.entity.vo.admin;

import com.orange.verify.api.entity.po.SoftPO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SoftPageResultVO extends SoftPO {

    private Integer accountTotal;

    private String versionNum;

    private Integer leaveMessageNum;

}
