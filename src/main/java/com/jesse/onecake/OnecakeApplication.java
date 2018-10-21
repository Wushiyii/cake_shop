package com.jesse.onecake;

import com.jesse.onecake.service.generator.id.EnableIdGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableIdGenerator
@EnableSwagger2
public class OnecakeApplication{

    public static void main(String[] args) {

        SpringApplication.run(OnecakeApplication.class, args);

    }

}
