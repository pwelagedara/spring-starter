package online.pubudu.springstarter.config;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.Multimap;
import online.pubudu.springstarter.dto.ErrorDto;
import online.pubudu.springstarter.security.jwt.login.LoginDto;
import online.pubudu.springstarter.security.jwt.login.TokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.ApiListingBuilder;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.service.ApiListing;
import springfox.documentation.service.Operation;
import springfox.documentation.spring.web.plugins.DocumentationPluginsManager;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;
import springfox.documentation.spring.web.scanners.ApiDescriptionReader;
import springfox.documentation.spring.web.scanners.ApiListingScanner;
import springfox.documentation.spring.web.scanners.ApiListingScanningContext;
import springfox.documentation.spring.web.scanners.ApiModelReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by pubudu on 01/02/19.
 */
// https://stackoverflow.com/questions/34386337/documenting-springs-login-logout-api-in-swagger
public class LoginOperation extends ApiListingScanner {

    @Autowired
    private TypeResolver typeResolver;

    public LoginOperation(ApiDescriptionReader apiDescriptionReader, ApiModelReader apiModelReader, DocumentationPluginsManager pluginsManager) {
        super(apiDescriptionReader, apiModelReader, pluginsManager);
    }

    @Override
    public Multimap<String, ApiListing> scan(ApiListingScanningContext context) {

        final Multimap<String, ApiListing> def = super.scan(context);

        final List<ApiDescription> apis = new LinkedList<>();

        final List<Operation> operations = new ArrayList<>();
        operations.add(new OperationBuilder(new CachingOperationNameGenerator())
                .method(HttpMethod.POST)
                .uniqueId("login")
                .parameters(Arrays.asList(new ParameterBuilder()
                        .name("body")
                        .required(true)
                        .description("The body of request")
                        .parameterType("body")
                        .type(typeResolver.resolve(LoginDto.class))
                        .modelRef(new ModelRef(LoginDto.class.getSimpleName()))
                        .build()))
                .summary("Login Endpoint")
                .notes("Login here to get the X-Authorization Token")
                .consumes(Stream.of("application/json").collect(Collectors.toSet()))
                .produces(Stream.of("*/*").collect(Collectors.toSet()))
                // https://riptutorial.com/swagger/example/25117/override-default-response-messages
                .responseMessages(Stream.of(
                        new ResponseMessageBuilder().code(200).message("OK").responseModel(new ModelRef(TokenDto.class.getSimpleName())).build(),
                        new ResponseMessageBuilder().code(400).message("Bad Request").responseModel(new ModelRef(ErrorDto.class.getSimpleName())).build(),
                        new ResponseMessageBuilder().code(401).message("Unauthorized").build(),
                        new ResponseMessageBuilder().code(403).message("Forbidden").build(),
                        new ResponseMessageBuilder().code(404).message("Not Found").build()
                ).collect(Collectors.toSet()))
                .build());

        apis.add(new ApiDescription("/auth/login", "Authentication documentation", operations, false));

        def.put("authentication", new ApiListingBuilder(context.getDocumentationContext().getApiDescriptionOrdering())
                .apis(apis)
                .description("Custom authentication")
                .build());

        return def;
    }
}
