/*
* IBM Confidential
*
* OCO Source Materials
*
* WLP Copyright IBM Corp. 2017
*
* The source code for this program is not published or otherwise divested
* of its trade secrets, irrespective of what has been deposited with the
* U.S. Copyright Office.
*/
package io.swagger.sample.servlet.configBuilderImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.List;

import io.swagger.oas.models.Components;
import io.swagger.oas.models.parameters.Parameter;
import io.swagger.oas.models.OpenAPI;
import io.swagger.oas.models.Operation;
import io.swagger.oas.models.PathItem;
import io.swagger.oas.models.Paths;
import io.swagger.oas.models.info.Info;
import io.swagger.oas.models.media.Content;
import io.swagger.oas.models.media.MediaType;
import io.swagger.oas.models.media.Schema;
import io.swagger.oas.models.responses.ApiResponse;
import io.swagger.oas.models.responses.ApiResponses;
import io.swagger.oas.web.OpenAPIConfig;
import io.swagger.oas.web.OpenAPIConfigBuilder;

public final class ServletAPIs implements OpenAPIConfigBuilder {

    @Override
	public OpenAPIConfig build(Map<String, Object> arg0) {
				OpenAPI oai = new OpenAPI();
		
		Info info = new Info();
		info.setTitle("Swagger Sample Servlet");
		info.setVersion("1.0.0");
		oai.setInfo(info);
		
		Components components = new Components();
		Map<String, Schema> schemas = new HashMap<String, Schema>();
		List<String> required = new ArrayList<String>();
		required.add("name");
		required.add("id");
		required.add("email");
		required.add("age");
		
		Map<String, Schema> properties = new HashMap<String, Schema>();
		properties.put("name",  new Schema()
				.type("string"));
		properties.put("id", new Schema()
				.type("integer")
				.format("int32"));
		properties.put("email", new Schema()
				.type("string"));
		properties.put("age", new Schema<>()
				.type("integer")
				.format("int32"));
		
		schemas.put("User", new Schema<Object>()
				.type("object")
				.required(required)
				.properties(properties));
		
		components.setSchemas(schemas);
		Paths paths = new Paths();
		PathItem pathItem = new PathItem();
		
		Operation operation = new Operation();
		operation.setDescription("Resource to get a user");
		oai.setComponents(components);

		ApiResponses responses = new ApiResponses();
		
		ApiResponse item = new ApiResponse();
		item.setDescription("Successful");
		
		ApiResponse item2 = new ApiResponse();
		item2.setDescription("Error: Not found.");
		
		Content content = new Content();
		MediaType mediaType = new MediaType();
		
		Schema<?> schema = new Schema<Object>();
		schema.set$ref("#/components/schemas/User");
		
		mediaType.setSchema(schema);
		content.addMediaType("application/json", mediaType);
		
		item.setContent(content);
		
		responses.addApiResponse("200", item);
		
		responses.addApiResponse("404", item2);
		
		operation.setResponses(responses);
		
		List<Parameter> parameters = new ArrayList<Parameter>();
		parameters.add(new Parameter()
				.name("name")
				.required(true)
				.in("query")
				.description("Name of the user")
				.schema(new Schema()
						.type("string")));
		parameters.add(new Parameter()
				.name("id")
				.required(true)
				.in("query")
				.description("Id of the user")
				.schema(new Schema()
						.type("integer")
						.format("int32")));
		parameters.add(new Parameter()
				.name("email")
				.required(true)
				.in("query")
				.description("Email of the user")
				.schema(new Schema()
						.type("string")));
		parameters.add(new Parameter()
				.name("age")
				.required(true)
				.in("query")
				.description("Age of the user")
				.schema(new Schema()
						.type("integer")
						.format("int32")));
		operation.setParameters(parameters);
		
		pathItem.get(operation);
		paths.addPathItem("/sample/users", pathItem);
		oai.setPaths(paths);
		
		OpenAPIConfig openAPIConfig = new OpenAPIConfig () {

			@Override
			public String getFilterClass() {
				return null;
			}

			@Override
			public Set<String> getIgnoredClasses() {	
				return null;
			}

			@Override
			public OpenAPI getOpenAPI() {
				return oai;
			}

			@Override
			public Map<String, Object> getOptions() {
				return null;
			}

			@Override
			public String getReaderClass() {
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
				return null;
			}

			@Override
			public boolean isScanDisabled() {
				return true;
			}
		};
		return openAPIConfig;	
	}
}
