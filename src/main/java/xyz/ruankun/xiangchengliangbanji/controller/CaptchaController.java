package xyz.ruankun.xiangchengliangbanji.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

@RestController
public class CaptchaController {
    private final DefaultKaptcha captchaProducer;

    @Autowired
    public CaptchaController(DefaultKaptcha captchaProducer) {
        this.captchaProducer = captchaProducer;
    }

    @GetMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 设置响应头信息
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        // 生成验证码字符串和图片
        String captchaText = captchaProducer.createText();
        BufferedImage captchaImage = captchaProducer.createImage(captchaText);

        // 将验证码字符串保存到 session 中
        HttpSession session = request.getSession();
        session.setAttribute("captcha", captchaText);

        // 输出图片流到响应体中
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(captchaImage, "jpeg", outputStream);
        IOUtils.closeQuietly(outputStream);
    }
}