package com.api.rules;

import com.api.factory.EnumRule;
import com.api.factory.SwaggerRuleFailure;
import io.swagger.models.Swagger;

import java.util.List;

public final class EnsureCamelCaseQueryParams extends SwaggerRule {

    public EnsureCamelCaseQueryParams() {
        super(EnumRule.RULE0016);
    }

    @Override
    public List<SwaggerRuleFailure> execute(Swagger swagger) {
        return null;
    }
}
