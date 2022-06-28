package com.yk.controller;

import com.yk.pojo.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("yk")
public class HelloController {

    @GetMapping("/helloSwagger")
    public String HelloSwagger() {
        return "Hello Swagger";
    }
    @GetMapping("/getUser")
    public User getUser() {
        return new User();
    }
    @ApiOperation("狂神的接口")
    @PostMapping("/kuang")
    @ResponseBody
    public String kuang(@ApiParam("这个名字会被返回")String username){
        return username;
    }

}
