package com.orange.verify.adminweb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.orange.verify.api.entity.po.BaseTableName;
import com.orange.verify.api.entity.po.SoftVersionPO;
import com.orange.verify.api.entity.vo.open.SoftVersionResultVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface SoftVersionMapper extends BaseMapper<SoftVersionPO>, BaseTableName {

    @Select("SELECT sv.* " +
            "FROM" + t_soft_version_space + "sv " +
            "where sv.soft_id = #{softId}")
    SoftVersionPO selectBySoftId(@Param("softId") String softId);

    @Select("select * from" + t_soft_version_space + "where soft_id = #{softId}")
    SoftVersionResultVO selectVOBySoftId(@Param("softId") String softId);

}