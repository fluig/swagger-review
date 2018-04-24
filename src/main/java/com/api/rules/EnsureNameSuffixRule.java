package com.api.rules;

import com.api.factory.SwaggerRuleFailure;
import com.api.factory.SwaggerRuleType;
import io.swagger.models.Swagger;

import java.util.List;

public class EnsureNameSuffixRule implements SwaggerRule {

    private static final String RULENAME = "RULE0006";
    private static final String MESSAGE = "A entidade de retorno %s não deve conter os sufixos: DTO, VO.";

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
        return "As entidades de retorno não podem conter os sufixos DTO ou VO.";
    }

    @Override
    public List<SwaggerRuleFailure> execute(Swagger swagger) {
        return null;
    }
}
