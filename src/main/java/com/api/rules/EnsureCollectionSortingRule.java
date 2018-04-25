package com.api.rules;

import com.api.factory.SwaggerRuleFailure;
import com.api.factory.SwaggerRuleType;
import io.swagger.models.Swagger;

import java.util.List;

public final class EnsureCollectionSortingRule implements SwaggerRule {

    private static final String RULENAME = "RULE0012";
    private static final String MESSAGE = "O path %s retorna uma collection, mas não permite a operação de ordenação.";

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
        return "APIs que retornam collections devem permitir ordenação.";
    }

    @Override
    public List<SwaggerRuleFailure> execute(Swagger swagger) {
        return null;
    }
}
