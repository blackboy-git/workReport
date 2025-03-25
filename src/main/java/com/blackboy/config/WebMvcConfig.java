package com.blackboy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 允许跨域访问的路径  '/**'表示应用的所有方法
        registry.addMapping("/**")

                //允许跨域请求可携带的header，'*'表所有header头。CORS请求时，XMLHttpRequest对象的getResponseHeader()方法只能拿到6个基本字段：Cache-Control、Content-Language、Content-Type、Expires、Last-Modified、Pragma。如果想拿到其他字段，就必须在Access-Control-Expose-Headers里面指定
                .allowedOriginPatterns("*")

                // 允许跨域请求的方法  '*'表示所有
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")

                // 预检间隔时间1小时，单位为秒。指定本次预检请求的有效期，在有效期间，不用发出另一条预检请求
                .maxAge(3600)
                // 是否允许发送cookie true-允许 false-不允许 默认false。对服务器有特殊要求的请求，比如请求方法是PUT或DELETE，或者Content-Type字段的类型是application/json，这个值只能设为true
                .allowCredentials(true);
    }
}
