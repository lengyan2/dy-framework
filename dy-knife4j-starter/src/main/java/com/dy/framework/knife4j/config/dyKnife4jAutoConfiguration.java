package com.dy.framework.knife4j.config;

import com.dy.framework.core.props.DyProperties;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.validation.beanvalidation.BeanValidationPostProcessor;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * knife4j 自动配置类
 * @author daiyuanjing
 */
@EnableKnife4j
@EnableOpenApi
@RequiredArgsConstructor
@AutoConfiguration
@Import(BeanValidationPostProcessor.class)
public class dyKnife4jAutoConfiguration {

    @Value(value = "${sa-token.token-name:Authorization}")
    private String headerTokenName;

    private final DyProperties dyProperties;

    @Bean
    @Order(1)
    public Docket groupRestApi(){
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(this.apiInfo())
                .select()
                // 抓取所有api
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .securityContexts(Collections.singletonList(this.securityContext()))
                .securitySchemes(Collections.singletonList(this.apiKeyOfToken()));
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title(dyProperties.getKnife4j().getTitle())
                .description(dyProperties.getKnife4j().getDescription())
                .termsOfServiceUrl("http://www.dy.com/")
                .contact(ApiInfo.DEFAULT_CONTACT)
                .version(dyProperties.getKnife4j().getVersion())
                .build();
    }

    private SecurityContext securityContext(){
        List<SecurityReference> references = new ArrayList<>();

        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;

        references.add(new SecurityReference(headerTokenName, authorizationScopes));
        return SecurityContext.builder().securityReferences(references).build();
    }

    private ApiKey apiKeyOfToken(){
        return new ApiKey(headerTokenName, headerTokenName, "header");
    }
}
