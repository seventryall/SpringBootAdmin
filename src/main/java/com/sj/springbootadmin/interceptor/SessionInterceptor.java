package com.sj.springbootadmin.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;


//@Configuration
public class SessionInterceptor implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        /**
         * 自定义拦截器，添加拦截路径和排除拦截路径
         * addPathPatterns():添加需要拦截的路径
         * excludePathPatterns():添加不需要拦截的路径
         * 在括号中还可以使用集合的形式，如注释部分代码所示
         */
        //registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/user/queryAllUser").excludePathPatterns("/user/userLogin");

          //举例：利用集合的形式
       List list = new ArrayList();
        list.add("/login/**");
        list.add("/static/**");
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").
                excludePathPatterns(list);
    }
}
