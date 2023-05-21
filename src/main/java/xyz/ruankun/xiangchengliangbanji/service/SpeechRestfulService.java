package xyz.ruankun.xiangchengliangbanji.service;


import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import xyz.ruankun.xiangchengliangbanji.util.AliTokenUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SpeechRestfulService {
    private static Logger logger = LoggerFactory.getLogger(SpeechRestfulService.class);
    private String accessToken;

    @Value("${aliyun.voice.appkey}")
    private static String appkey;


    private static SpeechRestfulService getInstance() {

        String token = AliTokenUtil.getToken();
        return new SpeechRestfulService(appkey, token);
    }

    private SpeechRestfulService(String appkey, String token) {
        this.appkey = appkey;
        this.accessToken = token;
    }
    /**
     * HTTPS GET 请求
     */
    private byte[] processGETRequet(String text, String format, int sampleRate) {
        /**
         * 设置HTTPS GET请求
         * 1.使用HTTPS协议
         * 2.语音识别服务域名：nls-gateway.cn-shanghai.aliyuncs.com
         * 3.语音识别接口请求路径：/stream/v1/tts
         * 4.设置必须请求参数：appkey、token、text、format、sample_rate
         * 5.设置可选请求参数：voice、volume、speech_rate、pitch_rate
         */
        String url = "https://nls-gateway.cn-shanghai.aliyuncs.com/stream/v1/tts";
        url = url + "?appkey=" + appkey;
        url = url + "&token=" + accessToken;
        url = url + "&text=" + text;
        url = url + "&format=" + format;
        url = url + "&sample_rate=" + String.valueOf(sampleRate);
        // voice 发音人，可选，默认是xiaoyun
        // url = url + "&voice=" + "xiaoyun";
        // volume 音量，范围是0~100，可选，默认50
        // url = url + "&volume=" + String.valueOf(50);
        // speech_rate 语速，范围是-500~500，可选，默认是0
        url = url + "&speech_rate=" + String.valueOf(100);
        // pitch_rate 语调，范围是-500~500，可选，默认是0
        // url = url + "&pitch_rate=" + String.valueOf(0);
//        System.out.println("URL: " + url);
        /**
         * 发送HTTPS GET请求，处理服务端的响应
         */
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        byte[] bytes=null;
        try {
            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();
            String contentType = response.header("Content-Type");
            if ("audio/mpeg".equals(contentType)) {
                bytes =response.body().bytes();
//                File f = new File(audioSaveFile);
//                FileOutputStream fout = new FileOutputStream(f);
//                fout.write(response.body().bytes());
//                fout.close();
//                System.out.println(f.getAbsolutePath());
                logger.info("The GET SpeechRestful succeed!");
            }
            else {
                // ContentType 为 null 或者为 "application/json"
                String errorMessage = response.body().string();
                logger.info("The GET SpeechRestful failed: " + errorMessage);
            }
            response.close();
        } catch (Exception e) {
            logger.error("processGETRequet",e);
        }
        return bytes;
    }
    /**
     * HTTPS POST 请求
     */
    private byte[] processPOSTRequest(String text, String audioSaveFile, String format, int sampleRate) {
        /**
         * 设置HTTPS POST请求
         * 1.使用HTTPS协议
         * 2.语音合成服务域名：nls-gateway.cn-shanghai.aliyuncs.com
         * 3.语音合成接口请求路径：/stream/v1/tts
         * 4.设置必须请求参数：appkey、token、text、format、sample_rate
         * 5.设置可选请求参数：voice、volume、speech_rate、pitch_rate
         */
        String url = "https://nls-gateway.cn-shanghai.aliyuncs.com/stream/v1/tts";
        JSONObject taskObject = new JSONObject();
        taskObject.put("appkey", appkey);
        taskObject.put("token", accessToken);
        taskObject.put("text", text);
        taskObject.put("format", format);
        taskObject.put("sample_rate", sampleRate);
        // voice 发音人，可选，默认是xiaoyun
        // taskObject.put("voice", "xiaoyun");
        // volume 音量，范围是0~100，可选，默认50
        // taskObject.put("volume", 50);
        // speech_rate 语速，范围是-500~500，可选，默认是0
        // taskObject.put("speech_rate", 0);
        // pitch_rate 语调，范围是-500~500，可选，默认是0
        // taskObject.put("pitch_rate", 0);
        String bodyContent = taskObject.toJSONString();
//        System.out.println("POST Body Content: " + bodyContent);
        RequestBody reqBody = RequestBody.create(MediaType.parse("application/json"), bodyContent);
        Request request = new Request.Builder()
                .url(url)
                .header("Content-Type", "application/json")
                .post(reqBody)
                .build();

        byte[] bytes=null;
        try {
            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();
            String contentType = response.header("Content-Type");
            if ("audio/mpeg".equals(contentType)) {
                bytes = response.body().bytes();
                logger.info("The POST SpeechRestful succeed!");
            }
            else {
                // ContentType 为 null 或者为 "application/json"
                String errorMessage = response.body().string();
                logger.info("The POST SpeechRestful failed: " + errorMessage);
            }
            response.close();
        } catch (Exception e) {
            logger.error("processPOSTRequest",e);
        }
        return bytes;
    }

    public static byte[] text2voice(String text) {
        if (StringUtils.isBlank(text)) {
            return null;
        }
        SpeechRestfulService demo = SpeechRestfulService.getInstance();

        String textUrlEncode = text;
        try {
            textUrlEncode = URLEncoder.encode(textUrlEncode, "UTF-8")
                    .replace("+", "%20")
                    .replace("*", "%2A")
                    .replace("%7E", "~");
        } catch (UnsupportedEncodingException e) {
            logger.error("encode",e);
        }
//        String audioSaveFile = "syAudio.wav";
        String format = "wav";
        int sampleRate = 16000;
        return demo.processGETRequet(textUrlEncode, format, sampleRate);
    }

}

