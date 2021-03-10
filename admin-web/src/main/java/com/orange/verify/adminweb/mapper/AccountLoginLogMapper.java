package com.orange.verify.adminweb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orange.verify.api.entity.po.AccountLoginLogPO;
import com.orange.verify.api.entity.po.BaseTableName;
import com.orange.verify.api.entity.vo.admin.AccountLoginLogPageResultVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountLoginLogMapper extends BaseMapper<AccountLoginLogPO>, BaseTableName {

    @Select("<script>" +
            "SELECT FROM_UNIXTIME(ROUND(create_date / 1000)) AS TIME FROM" + t_account_login_log_space +
            "WHERE " +
            "<![CDATA[ TO_DAYS(NOW()) - TO_DAYS(FROM_UNIXTIME(create_at/1000)) >= 0 " +
            "AND " +
            "TO_DAYS(NOW()) - TO_DAYS(FROM_UNIXTIME(create_at/1000)) <= 7 ]]>" +
            "<if test=\"softId != null and softId != ''\"> and soft_id = #{softId} </if>" +
            "ORDER BY create_at ASC" +
            "</script>")
    List<String> selectBeforeData(@Param("softId") String softId);

    @Delete("delete from" + t_account_login_log_space +
            "where TO_DAYS(NOW()) - TO_DAYS(FROM_UNIXTIME(create_at/1000)) >= 10 " +
            "limit 100")
    int deleteLog();

    @Select("<script>" +
            "select tall.*," +
            "(SELECT s.name FROM" + t_soft_space + "s WHERE s.id = tall.soft_id) as soft_name," +
            "(SELECT a.username FROM" + t_soft_account_space + "a WHERE a.id = tall.account_id) as account_name " +
            "from" + t_account_login_log_space + "tall " +
            "<where>" +
            "<if test=\"softId != null and softId != ''\">" +
            " and tall.soft_id = #{softId} " +
            "</if>" +
            "</where>" +
            "ORDER BY create_at desc" +
            "</script>")
    List<AccountLoginLogPageResultVO> page(@Param("softId") String softId, Page page);

}
