package com.zjc.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @Description:
 * @author: hanzhen.lu
 * @date: Create in 11:42 2019/3/21
 */

@Configuration
@EnableSwagger2
public class Swagger2 {

	@Bean
	public Docket createCommonApi() {
		return initDocket("Controller",
				"com.zjc.demo.controller");
	}


	/**
	 * @param groupName   组名
	 * @param basePackage 扫描路径
	 * @return
	 */
	private Docket initDocket(String groupName, String basePackage) {
		return new Docket(DocumentationType.SWAGGER_2)
				//.groupName(groupName)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage(basePackage))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Springboot2.x api接口")
				.description("Springboot2.x API文档")
				.version("1.0")
				.build();
	}


}
