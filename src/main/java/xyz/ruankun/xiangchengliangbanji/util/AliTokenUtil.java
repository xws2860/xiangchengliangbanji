package xyz.ruankun.xiangchengliangbanji.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;


public class AliTokenUtil {
    private static Logger logger = LoggerFactory.getLogger(AliTokenUtil.class);
    // 您的地域ID
    private static final String REGIONID = "cn-shanghai";
    // 获取Token服务域名
    private static final String DOMAIN = "nls-meta.cn-shanghai.aliyuncs.com";
    // API 版本
    private static final String API_VERSION = "2019-02-28";
    // API名称
    private static final String REQUEST_ACTION = "CreateToken";
    // 响应参数
    private static final String KEY_TOKEN = "Token";
    private static final String KEY_ID = "Id";
    private static final String KEY_EXPIRETIME = "ExpireTime";

    private static volatile String TOKEN = "";
    private static volatile long EXPIRETIME = 0L;

    @Value("${tts.access-key-id}")
    private static String accessKeyId;

    @Value("${tts.access-key-secret}")
    private static String accessKeySecret;
    public static String getToken() {
        if (StringUtils.isNotBlank(TOKEN)) {
            if (EXPIRETIME - System.currentTimeMillis() / 1000 > 3600) {
                return TOKEN;
            }
        }
        try {

            // 创建DefaultAcsClient实例并初始化
            DefaultProfile profile = DefaultProfile.getProfile(REGIONID, accessKeyId, accessKeySecret);
            IAcsClient client = new DefaultAcsClient(profile);
            CommonRequest request = new CommonRequest();
            request.setDomain(DOMAIN);
            request.setVersion(API_VERSION);
            request.setAction(REQUEST_ACTION);
            request.setMethod(MethodType.POST);
            request.setProtocol(ProtocolType.HTTPS);
            CommonResponse response = client.getCommonResponse(request);
            logger.info(response.getData());
            if (response.getHttpStatus() == 200) {
                JSONObject result = JSON.parseObject(response.getData());
                TOKEN = result.getJSONObject(KEY_TOKEN).getString(KEY_ID);
                EXPIRETIME = result.getJSONObject(KEY_TOKEN).getLongValue(KEY_EXPIRETIME);
                logger.info("获取到的Token： " + TOKEN + "，有效期时间戳(单位：秒): " + EXPIRETIME);
            } else {
                logger.info("获取Token失败！");
            }
        } catch (Exception e) {
            logger.error("getToken error!", e);
        }

        return TOKEN;
    }

}
