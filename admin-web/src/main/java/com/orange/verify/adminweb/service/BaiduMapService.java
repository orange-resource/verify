package com.orange.verify.adminweb.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orange.verify.adminweb.common.util.BaiduIpUtil;
import com.orange.verify.adminweb.mapper.BaiduMapTokenMapper;
import com.orange.verify.api.common.response.Response;
import com.orange.verify.api.common.response.ResultBuilder;
import com.orange.verify.api.common.response.RspCode;
import com.orange.verify.api.entity.po.BaiduMapTokenPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BaiduMapService extends ServiceImpl<BaiduMapTokenMapper, BaiduMapTokenPO> {

    @Autowired
    private BaiduMapTokenMapper baiduMapTokenMapper;

    public Response getDetail() {
        BaiduMapTokenPO baiduMapTokenPO = baiduMapTokenMapper.selectOne();
        Map<String, Object> build = ResultBuilder.create().setDetail(baiduMapTokenPO).build();
        return Response.build(RspCode.QUERY_SUCCESS, build);
    }

    public Response update(BaiduMapTokenPO baiduMapTokenPO) {
        boolean saveOrUpdate = super.saveOrUpdate(baiduMapTokenPO);
        return saveOrUpdate ? Response.success() : Response.error();
    }

    public String getIpInfo(String ip) throws Exception {

        if ("127.0.0.1".equals(ip)) {
            return "本地测试";
        }

        try {

            BaiduMapTokenPO baiduMapTokenPO = baiduMapTokenMapper.selectOne();

            String ipInfo = BaiduIpUtil.init(baiduMapTokenPO.getAppkey())
                    .getAddressByIp(ip);

            return ipInfo;

        } catch (Exception e) {
            throw new Exception();
        }
    }

}
