package com.swagger.central.docs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author athish
 */
@EnableSwagger2
@SpringBootApplication
@EnableScheduling
public class DocumentationAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(DocumentationAppApplication.class, args);
    }
}
