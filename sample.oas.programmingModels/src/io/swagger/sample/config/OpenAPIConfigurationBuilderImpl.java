package io.swagger.sample.config;

import java.util.Map;

import io.swagger.oas.integration.OpenAPIConfiguration;
import io.swagger.oas.integration.OpenAPIConfigurationBuilder;

public class OpenAPIConfigurationBuilderImpl implements OpenAPIConfigurationBuilder {

    @Override
    public OpenAPIConfiguration build(Map<String, Object> arg0) {
        return new OpenAPIConfigurationImpl();
    }

}
