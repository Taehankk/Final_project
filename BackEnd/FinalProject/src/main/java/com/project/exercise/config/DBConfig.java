package com.project.exercise.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.project.exercise.dao")
public class DBConfig {
}