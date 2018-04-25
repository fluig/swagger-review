package com.api.rules;

import com.api.factory.SwaggerRuleFailure;
import com.api.factory.SwaggerRuleType;
import io.swagger.models.Swagger;

import java.util.List;

public final class EnsureCollectionFilteringRule implements SwaggerRule {

    private static final String RULENAME = "RULE0013";
    private static final String MESSAGE = "O path %s retorna uma collection, mas não permite a operação de filtro.";

    @Override
    public String getName() {
        return RULENAME;
    }

    @Override
    public SwaggerRuleType getType() {
        return SwaggerRuleType.WARNING;
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
