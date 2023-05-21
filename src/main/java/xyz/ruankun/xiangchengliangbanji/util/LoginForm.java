package xyz.ruankun.xiangchengliangbanji.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * LoginForm包含了用户名、密码和验证码三个参数，其中用户名和密码是必填项，验证码是可选项。
 * 这个LoginForm使用了Lombok注解简化了代码，
 * 同时使用了JSR 380 Bean Validation注解对参数进行验证
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm {
    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    private String captcha;
}
