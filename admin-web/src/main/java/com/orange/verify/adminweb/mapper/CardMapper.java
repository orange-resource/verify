package com.orange.verify.adminweb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orange.verify.api.entity.po.BaseTableName;
import com.orange.verify.api.entity.po.CardPO;
import com.orange.verify.api.entity.vo.admin.CardPageParamVO;
import com.orange.verify.api.entity.vo.admin.CardPageResultVO;
import com.orange.verify.api.entity.vo.open.CardTimeLimitResultVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardMapper extends BaseMapper<CardPO>, BaseTableName {

    @Select("<script>" +
            "SELECT " +
            "c.*,ct.unit_type as card_type_unit, " +
            "ct.value as card_type_value,a.username as account_name, " +
            "s.name as soft_name " +
            "FROM" + t_card_space + "c " +
            "LEFT JOIN" + t_card_type_space + "ct " +
            "ON c.card_type_id = ct.id " +
            "LEFT JOIN" + t_soft_space + "s " +
            "ON ct.soft_id = s.id " +
            "LEFT JOIN t_account a " +
            "ON c.account_id = a.id " +
            "where 1=1 " +
            "<if test=\"cardVo.softId != null and cardVo.softId != ''\"> and ct.soft_id = #{cardVo.softId} </if>" +
            "<if test=\"cardVo.cardTypeUnit != null\"> and ct.unit_type = #{cardVo.cardTypeUnit} </if>" +
            "<if test=\"cardVo.closeStatus != null\"> and c.close_status = #{cardVo.closeStatus} </if>" +
            "<if test=\"cardVo.useStatus != null\"> and c.use_status = #{cardVo.useStatus} </if>" +
            "order by c.create_at desc" +
            "</script>")
    List<CardPageResultVO> page(@Param("cardVo") CardPageParamVO cardPageParamVO, Page page);

    @Select("SELECT c.start_date, c.end_date FROM" + t_soft_account_space + "a " +
            "LEFT JOIN" + t_card_space + "c " +
            "ON a.card_id = c.id " +
            "WHERE a.username = #{username} AND a.password = #{password} AND a.soft_id = #{softId}")
    CardTimeLimitResultVO selectCardTimeLimit(@Param("username") String username,
                                              @Param("password") String password,
                                              @Param("softId") String softId);

    @Select("select * from" + t_card_space + "where number = #{number}")
    CardPO selectByNumber(@Param("number") String number);

    @Select("select count(*) from" + t_card_space)
    int selectCount();

}