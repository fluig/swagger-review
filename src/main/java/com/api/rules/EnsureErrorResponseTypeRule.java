package com.api.rules;

import com.api.factory.EnumRule;
import com.api.factory.SwaggerRuleFailure;
import io.swagger.models.Swagger;

import java.util.List;

public final class EnsureErrorResponseTypeRule extends SwaggerRule {

    public EnsureErrorResponseTypeRule() {
        super(EnumRule.RULE0020);
    }

    @Override
    public List<SwaggerRuleFailure> execute(Swagger swagger) {
        return null;
    }
}
