package xyz.ruankun.xiangchengliangbanji.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import xyz.ruankun.xiangchengliangbanji.entity.User;
import xyz.ruankun.xiangchengliangbanji.service.UserService;
import xyz.ruankun.xiangchengliangbanji.util.JwtUtil;
import xyz.ruankun.xiangchengliangbanji.util.LoginForm;
import xyz.ruankun.xiangchengliangbanji.util.response.ErrorResponse;
import xyz.ruankun.xiangchengliangbanji.util.response.SuccessResponse;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
@Api("用户相关接口")
@CrossOrigin
public class UserController {

    private final UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ApiOperation("注册")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            userService.registerUser(user);
            return ResponseEntity.ok(new SuccessResponse());
        } catch (DuplicateKeyException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(400,e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginForm, HttpSession session) {
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();
        // 验证码进行验证, 因为有session所以比较好操作
        String captcha = (String) session.getAttribute("captcha"); //session中的
        if (!loginForm.getCaptcha().equals(captcha)) {
            return ResponseEntity.badRequest().body(new ErrorResponse("验证码错误"));
        }
        User user;
        try {
            user = userService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse("用户名不存在"));
        }
        if (!userService.passwordMatches(password, user.getPassword())) {
            return ResponseEntity.badRequest().body(new ErrorResponse("密码不正确"));
        }
        return ResponseEntity.ok(new SuccessResponse(200,"登录成功", jwtUtil.generateToken(user)));
    }
}
