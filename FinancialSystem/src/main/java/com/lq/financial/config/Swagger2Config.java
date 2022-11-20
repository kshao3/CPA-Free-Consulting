package com.lq.financial.config;

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
 * @Author
 * @Date2021/1/6 11:44
 * @Version V1.0
 * 前后台开发文档配置
 **/
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lq.financial.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 创建API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址 http://项目实际地址/swagger-ui.html
     * http://localhost:8085/logistics/doc.html  # BootStrap版本
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("财务咨询网站--API接口文档")
                .version("V1.0")
                .build();
    }

}
