package com.orange.verify.adminweb.service;

import com.orange.verify.adminweb.common.util.BaiduIpUtil;
import com.orange.verify.adminweb.mapper.BaiduMapTokenMapper;
import com.orange.verify.api.common.response.Response;
import com.orange.verify.api.common.response.RspCode;
import com.orange.verify.api.entity.po.BaiduMapTokenPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaiduMapService {

    @Autowired
    private BaiduMapTokenMapper baiduMapTokenMapper;

    public Response getConfig() {
        BaiduMapTokenPO baiduMapTokenPO = baiduMapTokenMapper.selectSingle();
        return Response.build(RspCode.QUERY_SUCCESS, baiduMapTokenPO);
    }

    public Response save(BaiduMapTokenPO baiduMapTokenPO) {
        int update = baiduMapTokenMapper.updateById(baiduMapTokenPO);
        return update > 0 ? Response.success() : Response.error();
    }

    public String getIpInfo(String ip) throws Exception {

        if ("127.0.0.1".equals(ip)) {
            return "本地测试";
        }

        try {

            BaiduMapTokenPO baiduMapTokenPO = baiduMapTokenMapper.selectSingle();

            String ipInfo = BaiduIpUtil.start(baiduMapTokenPO.getAppkey())
                    .getAddressByIp(ip);

            return ipInfo;

        } catch (Exception e) {
            throw new Exception();
        }
    }

}
