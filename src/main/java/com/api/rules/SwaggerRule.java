package com.api.rules;

import io.swagger.models.Swagger;

import java.util.List;

public interface SwaggerRule {
    String getName();
    String getDescription();
    SwaggerRuleType getType();

    List<SwaggerRuleFailure> execute(Swagger swagger);
}
