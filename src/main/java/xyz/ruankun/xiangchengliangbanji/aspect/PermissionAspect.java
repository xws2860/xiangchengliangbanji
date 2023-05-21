package xyz.ruankun.xiangchengliangbanji.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import xyz.ruankun.xiangchengliangbanji.annotation.RequirePermission;
import xyz.ruankun.xiangchengliangbanji.entity.User;
import xyz.ruankun.xiangchengliangbanji.exception.UnauthorizedException;
import xyz.ruankun.xiangchengliangbanji.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class PermissionAspect {

    @Autowired
    private UserService userService;

    @Pointcut("@annotation(xyz.ruankun.xiangchengliangbanji.annotation.RequirePermission)")
    public void permissionCheck() {
    }

    @Around("permissionCheck()")
    public Object doPermissionCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization");

        if (!StringUtils.hasLength(token)) {
            throw new UnauthorizedException("请求头中没有Authorization");
        }
        User user = userService.getUserByToken(token);
        if (user == null) {
            throw new UnauthorizedException("未授权");
        }
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        RequirePermission requirePermission = signature.getMethod().getAnnotation(RequirePermission.class);
        String permission = requirePermission.value();
        //在这里通过permission的value判断用户是否具有某种权限
        System.out.println("这里判断用户是否具有权限" +permission);
        return joinPoint.proceed();
    }
}