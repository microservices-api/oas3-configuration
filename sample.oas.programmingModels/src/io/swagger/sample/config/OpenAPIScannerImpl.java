package io.swagger.sample.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import io.swagger.oas.integration.OpenAPIConfiguration;
import io.swagger.oas.integration.OpenAPIScanner;
import io.swagger.sample.jaxrs.TasksResource;

public class OpenAPIScannerImpl implements OpenAPIScanner {

    @Override
    public Set<Class<?>> getClasses() {
        return new HashSet<Class<?>>(Arrays.asList(TasksResource.class));
    }

    @Override
    public Map<String, Object> getResources() {
        return null;
    }

    @Override
    public void setConfiguration(OpenAPIConfiguration openAPIConfiguration) {
        // No-op
    }

}
