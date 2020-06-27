package com.example.servingwebcontent;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/insertData").setViewName("insertData");
        registry.addViewController("/Stores").setViewName("store");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/registerError").setViewName("registerError");
        registry.addViewController("/css/register.css").setViewName("/css/register.css");
        registry.addViewController("/queuePeople1.jpeg").setViewName("/queuePeople1.jpeg");
        registry.addViewController("/registers").setViewName("registers");
        registry.addViewController("/css/loginStyle.css").setViewName("/css/loginStyle.css");
        registry.addViewController("/css/homeStyle.css").setViewName("/css/homeStyle.css");
        registry.addViewController("/css/insertDataStyle.css").setViewName("/css/insertDataStyle.css");
        registry.addViewController("/js/home.js").setViewName("/js/home.js");
        registry.addViewController("/js/homeList.js").setViewName("/js/homeList.js");
        registry.addViewController("/css/userInformationStyle.css").setViewName("/css/userInformationStyle.css");
        registry.addViewController("/userInformation").setViewName("userInformation");
        registry.addViewController("/storeLocation").setViewName("StoreByLocation");
        registry.addViewController("/css/storeLocation.css").setViewName("/css/storeLocation.css");
    }

}