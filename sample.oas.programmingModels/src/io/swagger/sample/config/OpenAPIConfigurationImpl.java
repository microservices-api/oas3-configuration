package io.swagger.sample.config;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import io.swagger.oas.integration.OpenAPIConfiguration;
import io.swagger.oas.models.Components;
import io.swagger.oas.models.OpenAPI;
import io.swagger.oas.models.Operation;
import io.swagger.oas.models.PathItem;
import io.swagger.oas.models.info.Info;
import io.swagger.oas.models.media.Content;
import io.swagger.oas.models.media.MediaType;
import io.swagger.oas.models.media.Schema;
import io.swagger.oas.models.parameters.Parameter;
import io.swagger.oas.models.responses.ApiResponse;
import io.swagger.oas.models.responses.ApiResponses;

public class OpenAPIConfigurationImpl implements OpenAPIConfiguration {

    @Override
    public OpenAPI getOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Open API Specification Sample").version("1.0.0"))
                .components(new Components().addSchemas("User",
                        new Schema<Object>().type("object").addProperties("name", new Schema().type("string"))
                                .addProperties("id", new Schema().type("integer").format("int32"))
                                .addProperties("email", new Schema().type("string"))
                                .addProperties("age", new Schema<>().type("integer").format("int32"))))
                .path("/sample/users",
                        new PathItem().get(new Operation().description("Resource to get a user")
                                .responses(
                                        new ApiResponses()
                                                .addApiResponse("200",
                                                        new ApiResponse().description("Successful")
                                                                .content(new Content().addMediaType("application/json",
                                                                        new MediaType().schema(new Schema<Object>()
                                                                                .$ref("#/components/schemas/User")))))
                                                .addApiResponse("404",
                                                        new ApiResponse().description("Error: Not found.")))
                                .addParametersItem(new Parameter().name("name").required(true).in("query")
                                        .description("Name of the user").schema(new Schema().type("string")))
                                .addParametersItem(new Parameter().name("id").required(true).in("query")
                                        .description("Id of the user").schema(new Schema().type("integer").format("int32")))
                                .addParametersItem(new Parameter().name("email").required(true).in("query")
                                        .description("Email of the user").schema(new Schema().type("string")))
                                .addParametersItem(new Parameter().name("age").required(true).in("query")
                                        .description("Age of the user").schema(new Schema().type("integer").format("int32")))));
    }

    @Override
    public String getReaderClass() {
        // This method could be used for providing a custom OpenAPIReader implementation 
        // as an alternative to specifying the fully qualifies class name in 
        // META-INF/services/io.swagger.oas.integration.OpenAPIReader.
        // return "io.swagger.sample.config.OpenAPIReaderImpl";
        return null;
    }

    @Override
    public Set<String> getResourceClasses() {
        return null;
    }

    @Override
    public Set<String> getResourcePackages() {
        return null;
    }

    @Override
    public String getScannerClass() {
        // This method could be used for providing a custom OpenAPIScanner implementation 
        // as an alternative to specifying the fully qualifies class name in 
        // META-INF/services/io.swagger.oas.integration.OpenAPIScanner.
        // return "io.swagger.sample.config.OpenAPIScannerImpl";
        return null;
    }

    @Override
    public Collection<String> getIgnoredRoutes() {
        return null;
    }

    @Override
    public Map<String, Object> getUserDefinedOptions() {
        return null;
    }

    @Override
    public Boolean isReadAllResources() {
        return null;
    }

    @Override
    public Boolean isScanningDisabled() {
        return Boolean.FALSE;
    }

}
