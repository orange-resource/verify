package com.orange.verify.adminweb.common.convert;

import com.orange.verify.api.entity.po.SoftAccountPO;
import com.orange.verify.api.entity.po.SoftLeaveMessagePO;
import com.orange.verify.api.entity.po.SoftPO;
import com.orange.verify.api.entity.vo.open.AccountRegisterVO;
import com.orange.verify.api.entity.vo.open.SoftGetSoftDescVO;
import com.orange.verify.api.entity.vo.open.SoftLeaveMeesageSubmitVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ConvertObject {

    @Mapping(target = "createIp",source = "ip")
    SoftAccountPO fromVo(AccountRegisterVO accountRegisterVO);

    SoftLeaveMessagePO fromVo(SoftLeaveMeesageSubmitVO softLeaveMeesageSubmitVO);

    SoftGetSoftDescVO toVo(SoftPO softPO);

}
