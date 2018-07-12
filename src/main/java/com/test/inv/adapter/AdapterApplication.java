package com.test.inv.adapter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan({"com.test.inv"})
@EnableSwagger2
@EnableDiscoveryClient
public class AdapterApplication {

	
	@Value("${env.server}")
	public String env = "";	

	
	public static void main(String[] args) {
		SpringApplication.run(AdapterApplication.class, args);
	}
	
	@Bean
	public Docket createQueryRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.groupName("Query API")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.test.inv.adapter.ctl.query"))
				.paths(PathSelectors.any())
				.build();
	}
	
	@Bean
	public Docket createregisterRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.groupName("Register API")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.test.inv.adapter.ctl.register"))
				.paths(PathSelectors.any())
				.build();
	}
	
    @SuppressWarnings("deprecation")
	private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("["+env+"] swagger invoice RESTful APIs")
                .description("swagger invoice RESTful APIs")
                .termsOfServiceUrl("http://www.test.com/")
                .contact("richardkang_tw@msn.com")
                .version("1.0")
                .build();
    }
}
