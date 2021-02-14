package com.orange.verify.api.entity.vo;

import com.orange.verify.api.bean.SoftLeaveMessage;
import lombok.Data;

@Data
public class SoftLeaveMessageVo extends SoftLeaveMessage {

    private String softId;

    private String softName;

}
