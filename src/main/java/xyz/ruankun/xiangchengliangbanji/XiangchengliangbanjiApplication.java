package xyz.ruankun.xiangchengliangbanji;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableOpenApi
@SpringBootApplication
public class XiangchengliangbanjiApplication {
    public static void main(String[] args) {
        SpringApplication.run(XiangchengliangbanjiApplication.class, args);
        System.out.println("swagger-api: http://localhost:8081/swagger-ui/index.html");
    }

}
