package com.yk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2 // 开启swagger2配置
public class SwaggerConfig {
    @Bean //配置docket以配置Swagger具体参数
    public Docket docket(Environment environment) {
        // 设置要显示swagger的环境
        Profiles of = Profiles.of("dev", "test");
        // 判断当前是否处于该环境
        // 通过 enable() 接收此参数判断是否要显示
        boolean flag = environment.acceptsProfiles(of);
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("hello")
                .apiInfo(apiInfo())
                .enable(flag)//配置是否启用Swagger，如果是false，在浏览器将无法访问
                .select()//通过.select()方法，去配置扫描接口,RequestHandlerSelectors配置如何扫描接口
                .apis(RequestHandlerSelectors.basePackage("com.yk.controller"))
                .paths(PathSelectors.ant("/yk/**"))  // 配置如何通过path过滤，即这里只扫描请求以/yk开头的接口
                .build();
//        any() // 扫描所有，项目中的所有接口都会被扫描到
//        none() // 不扫描接口
//// 通过方法上的注解扫描，如withMethodAnnotation(GetMapping.class)只扫描get请求
//        withMethodAnnotation(final Class<? extends Annotation> annotation)
//// 通过类上的注解扫描，如.withClassAnnotation(Controller.class)只扫描有controller注解的类中的接口
//        withClassAnnotation(final Class<? extends Annotation> annotation)
//        basePackage(final String basePackage) // 根据包路径扫描接口
//        paths(PathSelectors.ant("/kuang/**"))  //过滤什么路径：过滤/kuang下的所有路径

    }
    //配置文档信息
    private ApiInfo apiInfo() {
        Contact contact = new Contact("联系人名字","http://xxx.xxx.com/练习人访问链接","联系人邮箱");
        return new ApiInfo(
                "Swagger学习", //标题
                "学习如何演示配置Swagger", //描述
                "v1.0", //版本
                "http://terms.service.url/组织链接", // 组织链接
                contact, // 联系人信息
                "Apach 2.0 许可", // 许可
                "许可链接", // 许可连接
                new ArrayList<>()// 扩展
        );
    }

    @Bean
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("group1");
    }
    @Bean
    public Docket docket2(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("group2");
    }
    @Bean
    public Docket docket3(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("group3");
    }

}
