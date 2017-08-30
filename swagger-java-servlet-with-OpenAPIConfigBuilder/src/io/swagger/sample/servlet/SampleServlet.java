package io.swagger.sample.servlet;

import io.swagger.oas.annotations.*;
import io.swagger.oas.annotations.Operation;
import io.swagger.oas.annotations.responses.ApiResponse;
import io.swagger.oas.annotations.info.Contact;
import io.swagger.oas.annotations.info.Info;
import io.swagger.oas.annotations.info.License;
import io.swagger.oas.annotations.media.Content;
import io.swagger.oas.annotations.media.Schema;
import io.swagger.sample.model.SampleData;
import io.swagger.util.Json;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

@Info(
		description = "This is a sample server",
        version = "1.0.0",
        title = "Swagger Sample Servlet",
        termsOfService = "http://swagger.io/terms/",
        contact = @Contact(
        		name = "Sponge-Bob", 
        		email = "apiteam@swagger.io", 
        		url = "http://swagger.io"),
        license = @License(
        		name = "Apache 2.0", 
        		url = "http://www.apache.org/licenses/LICENSE-2.0.html")
        ) 
@Consumes({"application/json", "application/xml"})
@Produces({"application/json", "application/xml"})
//        schemes = {SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS},
//        tags = {@Tag(name = "users", description = "Operations about user")}
//)
@Schema(
		name = "/sample/users", 
		description = "gets some data from a servlet"
		)
public class SampleServlet extends HttpServlet {
	
	@GET
    @Operation(
  		  method = "GET",
  		  summary = "Get user",
  		  description ="Resource to get a user", 
  		  responses = {
  				  @ApiResponse(
  						  responseCode = "400",
  						  description = "Invalid input",
  						  content = @Content(
  								  mediaType = "none",
  								  schema = @Schema(implementation = io.swagger.sample.model.ApiResponse.class)
  								  )
  						  )
  		  		},
  		  parameters = {
  			         	@Parameter(
  			         			name = "id", 
  			            		description = "User ID", 
  			            		in = "query",
  			            		required = true, 
  			            		content = @Content(
  			            				schema = @Schema(format ="integer")
  			            				)
  			         			),
  			         	@Parameter(
  			         			name = "name", 
  			            		description = "User's name", 
  			            		in = "query",
  			            		required = true, 
  			            		content = @Content(
  			            				schema = @Schema(format ="string")
  			            				)
  			         			),
  			         	@Parameter(
  			         			name = "email", 
  			            		description = "User's email", 
  			            		in = "query",
  			            		required = true, 
  			            		content = @Content(
  			            				schema = @Schema(format ="string")
  			            				)
  			         			),
  			         	@Parameter(
  			         			name = "age", 
  			            		description = "User's age", 
  			            		in = "query",
  			            		required = true, 
  			            		content = @Content(
  			            				schema = @Schema(format ="integer")
  			            				)
  			         			),
  			         	@Parameter(
  			         			name = "dateOfBirth", 
  			            		description = "User's date of birth, in dd-MM-yyyy format", 
  			            		in = "query",
  			            		required = true, 
  			            		content = @Content(
  			            				schema = @Schema(format ="date") //java.util.Date
  			            				)
  			         			)
  		  }
    )
   
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result;
        try {
            final Integer id = Integer.parseInt(request.getParameter("id"));
            final String name = request.getParameter("name");
            final String email = request.getParameter("email");
            final Integer age = Integer.parseInt(request.getParameter("age"));
            final Date dateOfBirth = new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("dateOfBirth"));
            result = Json.pretty(new SampleData(id, name, email, age, dateOfBirth));
        } catch (Exception ex) {
            result = Json.pretty(new io.swagger.sample.model.ApiResponse(400, ex.getMessage()));
        }

        response.getOutputStream().write(result.getBytes(StandardCharsets.UTF_8));
    }
}