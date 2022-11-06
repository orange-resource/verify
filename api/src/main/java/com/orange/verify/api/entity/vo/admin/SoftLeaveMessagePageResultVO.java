package com.orange.verify.api.entity.vo.admin;

import com.orange.verify.api.entity.po.SoftLeaveMessagePO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SoftLeaveMessagePageResultVO extends SoftLeaveMessagePO {

    private String softName;

}
