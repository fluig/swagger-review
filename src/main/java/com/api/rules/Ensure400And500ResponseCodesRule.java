package com.api.rules;

import com.api.factory.SwaggerRuleFailure;
import com.api.factory.SwaggerRuleType;
import io.swagger.models.Swagger;

import java.util.List;

public final class Ensure400And500ResponsesRule implements SwaggerRule {

    private static final String RULENAME = "RULE0005";
    private static final String MESSAGE = "O path {path} não mapea os códigos de retorno 400 ou 500.";

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
        return "Todas as APIs devem mapear os códigos de resposta 400 e 500.";
    }

    @Override
    public List<SwaggerRuleFailure> execute(Swagger swagger) {
        return null;
    }
}
