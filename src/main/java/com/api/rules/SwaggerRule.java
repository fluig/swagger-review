package com.api.rules;

import io.swagger.models.Swagger;

public interface SwaggerRule {
    String getName();
    ValidationResult execute(Swagger swagger);
}
