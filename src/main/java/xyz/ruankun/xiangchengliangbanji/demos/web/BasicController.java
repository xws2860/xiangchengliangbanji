/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package xyz.ruankun.xiangchengliangbanji.demos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.ruankun.xiangchengliangbanji.entity.User;
import xyz.ruankun.xiangchengliangbanji.entity.YesNoEnum;
import xyz.ruankun.xiangchengliangbanji.service.UserService;

import java.util.Date;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Controller
public class BasicController {

    @Autowired
    UserService userService;


    // http://127.0.0.1:8080/hello?name=lisi
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam(name = "name", defaultValue = "unknown user") String name) {
        return "Hello " + name;
    }

    // http://127.0.0.1:8080/user
    @RequestMapping("/user")
    @ResponseBody
    public xyz.ruankun.xiangchengliangbanji.demos.web.User user() {
        xyz.ruankun.xiangchengliangbanji.demos.web.User user = new xyz.ruankun.xiangchengliangbanji.demos.web.User();
        user.setName("theonefx");
        user.setAge(666);
        return user;
    }

    // http://127.0.0.1:8080/save_user?name=newName&age=11
    @RequestMapping("/save_user")
    @ResponseBody
    public String saveUser(xyz.ruankun.xiangchengliangbanji.demos.web.User u) {
        return "user will save: name=" + u.getName() + ", age=" + u.getAge();
    }

    // http://127.0.0.1:8080/html
    @RequestMapping("/html")
    public String html(){
        return "index.html";
    }

    @ModelAttribute
    public void parseUser(@RequestParam(name = "name", defaultValue = "unknown user") String name
            , @RequestParam(name = "age", defaultValue = "12") Integer age, xyz.ruankun.xiangchengliangbanji.demos.web.User user) {
        user.setName("zhangsan");
        user.setAge(18);
    }

    @RequestMapping("/save_users")
    @ResponseBody
    public String saveUsers(User u) {
        User user = new User(1,"asdsa","sdfsd","dsfdsf",5,4,YesNoEnum.否, new Date(),new Date());
        System.out.println(user);
        userService.save(user);
        return "{牛子疼}";
    }
}
