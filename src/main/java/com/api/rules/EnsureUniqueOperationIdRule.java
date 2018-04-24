package com.api.rules;

import com.api.factory.SwaggerRuleFailure;
import com.api.factory.SwaggerRuleType;
import io.swagger.models.Swagger;

import java.util.List;

public final class EnsureUniqueOperationIdRule implements SwaggerRule {

    @Override
    public String getName() {
        return null;
    }

    @Override
    public SwaggerRuleType getType() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public List<SwaggerRuleFailure> execute(Swagger swagger) {
        return null;
    }
}
