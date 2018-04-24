package com.api.rules;

import com.api.factory.SwaggerRuleFailure;
import com.api.factory.SwaggerRuleType;
import io.swagger.models.Swagger;

import java.util.List;

public interface SwaggerRule {

    String getName();
    String getDescription();
    SwaggerRuleType getType();

    List<SwaggerRuleFailure> execute(Swagger swagger);
}
