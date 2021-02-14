package com.orange.verify.adminweb.common.convert;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ConvertObject {

    @Mapping(target = "createIp",source = "ip")
    Account fromVo(AccountRegisterVo accountRegisterVo);

    SoftLeaveMessage fromVo(SoftLeaveMeesageSubmitVo softLeaveMeesageSubmitVo);

    SoftGetSoftDescVo toVo(Soft soft);

}
