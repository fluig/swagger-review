package com.api.rules;

import com.api.factory.SwaggerRuleFailure;
import com.api.factory.SwaggerRuleType;
import io.swagger.models.Swagger;

import java.util.List;

public final class EnsureValidJsonEntity implements SwaggerRule {

    private static final String RULENAME = "RULE0007";
    private static final String MESSAGE = "O path %s retorna um valor primitivo ao invés de uma JSON válido.";

    @Override
    public String getName() {
        return RULENAME;
    }

    @Override
    public SwaggerRuleType getType() {
        return SwaggerRuleType.ERROR;
    }

    @Override
    public String getDescription() {
        return "As entidades de retorno das APIs deve ser um JSON válido e não um valor primitivo.";
    }

    @Override
    public List<SwaggerRuleFailure> execute(Swagger swagger) {
        return null;
    }
}
