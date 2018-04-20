package com.api.rules;

import io.swagger.models.Swagger;

import java.util.List;

public interface SwaggerRule {
    String getName();
    SwaggerRuleType getType();

    List<SwaggerRuleFailure> execute(Swagger swagger);
}
