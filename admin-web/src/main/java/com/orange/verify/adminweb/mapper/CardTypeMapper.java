package com.orange.verify.adminweb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orange.verify.api.entity.po.BaseTableName;
import com.orange.verify.api.entity.po.CardTypePO;
import com.orange.verify.api.entity.vo.admin.CardTypePageResultVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardTypeMapper extends BaseMapper<CardTypePO>, BaseTableName {

    @Select("<script>" +
            "select ct.*, " +
            "(select name from" + t_soft_space + "where id = ct.soft_id) as soft_name " +
            "from" + t_card_type_space + "ct " +
            "where 1=1 " +
            "<if test=\"softId != null and softId != ''\"> and ct.soft_id = #{softId} </if>" +
            "order by ct.create_at desc" +
            "</script>")
    List<CardTypePageResultVO> selectByPage(@Param("softId") String softId, Page page);

}
