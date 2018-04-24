package com.api.rules;

import com.api.factory.SwaggerRuleFailure;
import com.api.factory.SwaggerRuleType;
import io.swagger.models.Swagger;

import java.util.List;

public final class EnsurePathParam404ResponseRule implements SwaggerRule {

    private static final String RULENAME = "RULE0003";
    private static final String MESSAGE = "O path da api %s possui um placeholder que identifica uma entidade e portanto deve mapear o código de erro 404.";

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
        return "Paths que identificam uma entidade pelo id/code devem retornar 404.";
    }

    @Override
    public List<SwaggerRuleFailure> execute(Swagger swagger) {
        return null;
    }
}
