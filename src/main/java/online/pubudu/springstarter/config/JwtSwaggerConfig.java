package online.pubudu.springstarter.config;

import com.fasterxml.classmate.TypeResolver;
import online.pubudu.springstarter.security.jwt.login.LoginDto;
import online.pubudu.springstarter.security.jwt.login.TokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
import springfox.documentation.spring.web.plugins.DocumentationPluginsManager;
import springfox.documentation.spring.web.scanners.ApiDescriptionReader;
import springfox.documentation.spring.web.scanners.ApiListingScanner;
import springfox.documentation.spring.web.scanners.ApiModelReader;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * JWT Swagger configuration is here. This is activated by <b>jwt</b> Spring Profile.
 * <p>
 * <b>/auth/login</b> endpoint is used to authenticate the user initially. This endpoint is added to Swagger Documentation using {@link online.pubudu.springstarter.config.LoginOperation}.
 * @author pubudu welagedara
 * @see <a href="http://pubudu.online">pubudu.online</a>
 */
@Configuration
@Profile("jwt")
@EnableSwagger2
public class JwtSwaggerConfig {

    @Autowired
    private TypeResolver typeResolver;

    /**
     * <a href="https://springfox.github.io/springfox/docs/current/">Documentation</a>
     * @return Docket
     * @author pubudu welagedara
     * @see <a href="http://pubudu.online">pubudu.online</a>
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
                .additionalModels(typeResolver.resolve(LoginDto.class), typeResolver.resolve(TokenDto.class))
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
        return new ApiKey("Authorization", "X-Authorization", "header");
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
                new SecurityReference("Authorization", authorizationScopes));
    }

    /**
     * <a href="https://stackoverflow.com/questions/34386337/documenting-springs-login-logout-api-in-swagger">Tutorial on Exposing Login and Logout Endpoints</a>
     * @param apiDescriptionReader
     * @param apiModelReader
     * @param pluginsManager
     * @return LoginOperation
     * @author pubudu welagedara
     * @see <a href="http://pubudu.online">pubudu.online</a>
     */
    @Primary
    @Bean
    public ApiListingScanner addExtraOperations(ApiDescriptionReader apiDescriptionReader, ApiModelReader apiModelReader, DocumentationPluginsManager pluginsManager)
    {
        return new LoginOperation(apiDescriptionReader, apiModelReader, pluginsManager);
    }

}
