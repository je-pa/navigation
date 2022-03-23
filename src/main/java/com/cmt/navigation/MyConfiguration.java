package com.cmt.navigation;

import com.example.navigationservice.navigation.core.service.NavigationServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.example.navigationservice.navigation.core.repository")
public class MyConfiguration {

}
