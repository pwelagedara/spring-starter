package online.pubudu.springstarter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * API Key Swagger configuration is here. This is activated by <b>apikey</b> Spring Profile.
 * <p>
 * Here Swagger Security Configurations are added to <b>/protected/*</b> endpoints golbally.
 * @author pubudu welagedara
 * @see <a href="http://pubudu.online">pubudu.online</a>
 */
@Configuration
@Profile("apikey")
@EnableSwagger2
public class ApiKeySwaggerConfig {

    /**
     * <p>
     *     <a href="https://springfox.github.io/springfox/docs/current/">Documentation</a>
     * </p>
     * @return Docket
     * @author pubudu welagedara
     * @see springfox.documentation.spring.web.plugins.Docket
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("online.pubudu.springstarter"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(newArrayList(apiKey()))
                .securityContexts(newArrayList(securityContext()))
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API Documentation")
                .description("Sample APIs")
                .license("MIT")
                .licenseUrl("https://opensource.org/licenses/MIT")
                .version("2.0")
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("ApiKey", "X-Api-Key", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/protected.*"))
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "Access Everything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return newArrayList(
                new SecurityReference("ApiKey", authorizationScopes));
    }

}
