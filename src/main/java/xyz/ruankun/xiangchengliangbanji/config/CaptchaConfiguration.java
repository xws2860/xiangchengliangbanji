package xyz.ruankun.xiangchengliangbanji.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * 可能是因为 Spring 容器无法正确实例化该对象defaultKaptcha。此时，您可以尝试手动创建一个 DefaultKaptcha 对象，并将其添加到 Spring 容器中
 */
@Configuration
public class CaptchaConfiguration {
    @Value("${kaptcha.border}")
    private String border;

    @Value("${kaptcha.textproducer.font.color}")
    private String fontColor;

    @Value("${kaptcha.textproducer.char.space}")
    private String charSpace;

    @Value("${kaptcha.textproducer.char.length}")
    private String charLength;
    @Bean
    public DefaultKaptcha defaultKaptcha() {
        Properties properties = new Properties();
        // 设置 kaptcha 属性
        properties.setProperty("kaptcha.border", border);
        properties.setProperty("kaptcha.textproducer.font.color", fontColor);
        properties.setProperty("kaptcha.textproducer.char.space", charSpace);
        properties.setProperty("kaptcha.textproducer.char.length", charLength);

        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);

        return defaultKaptcha;
    }
}