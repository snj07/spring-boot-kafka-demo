package com.snj;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;

@Configuration
@EnableSwagger2
@ComponentScan(basePackages = "com.authentication.api")
public class SwaggerConfig {

    @Value("${config.oauth2.clientID}")
    private String CLIENT_ID;

    @Value("${config.oauth2.clientSecret}")
    private String CLIENT_SECRET;

    @Value("${config.oauth2.accessTokenUri}")
    private String AUTH_SERVER;



    /**
     * @return Docket
     */
    @Bean
    public Docket productApi() {


        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .securityContexts(Collections.singletonList(securityContext()))
                .securitySchemes(Arrays.asList(securitySchema(), apiKey(), apiCookieKey()))
                .apiInfo(apiInfo());


    }
//    @Bean
//    SecurityConfiguration security() {
//
//        return new SecurityConfiguration(
//                "client",
//                "secret",
//                "",
//                "",
//                "{{X-XSRF-COOKIE}}",
//                ApiKeyVehicle.HEADER,
//                "X-XSRF-TOKEN",
//                "" /*scope separator*/);
//    }
    @Bean
    SecurityConfiguration security() {
        return new SecurityConfiguration(
                "client",
                "secret",
                null, // realm Needed for authenticate button to work
                null, // appName Needed for authenticate button to work
                "BEARER ",// apiKeyValue
                ApiKeyVehicle.HEADER,
                "AUTHORIZATION", //apiKeyName
                null);
    }

    @Bean
    public SecurityScheme apiKey() {
        return new ApiKey(HttpHeaders.AUTHORIZATION, "Authorization", "header");
    }

    @Bean
    public SecurityScheme apiCookieKey() {
        return new ApiKey(HttpHeaders.COOKIE, "apiKey", "cookie");
    }

    private OAuth securitySchema() {

        List<AuthorizationScope> authorizationScopeList = newArrayList();
        authorizationScopeList.add(new AuthorizationScope("read", "read all"));
        authorizationScopeList.add(new AuthorizationScope("write", "access all"));

        List<GrantType> grantTypes = newArrayList();
        GrantType passwordCredentialsGrant = new ResourceOwnerPasswordCredentialsGrant(AUTH_SERVER);
        grantTypes.add(passwordCredentialsGrant);

        return new OAuth("oauth2", authorizationScopeList, grantTypes);
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth())
                .build();
    }


    private List<SecurityReference> defaultAuth() {

        final AuthorizationScope[] authorizationScopes = new AuthorizationScope[3];
        authorizationScopes[0] = new AuthorizationScope("read", "read all");
        authorizationScopes[1] = new AuthorizationScope("trust", "trust all");
        authorizationScopes[2] = new AuthorizationScope("write", "write all");

        return Collections.singletonList(new SecurityReference("oauth2", authorizationScopes));
    }

//    @Bean
//    public SecurityConfiguration security() {
//        return new SecurityConfiguration
//                ("client", "secret", "", "", "{{X-XSRF-COOKIE}}", ApiKeyVehicle.HEADER, HttpHeaders.AUTHORIZATION, "");
//    }



    /**
     * @return ApiInf
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Authentication API").description("")
                .termsOfServiceUrl("https://www.example.com/api")
                .contact(new Contact("Developers", "https://projects.spring.io/spring-boot/", ""))
                .license("Open Source")
                .licenseUrl("\"https://www.apache.org/licenses/LICENSE-2.0")
                .version("1.0.0")
                .build();
    }

}
