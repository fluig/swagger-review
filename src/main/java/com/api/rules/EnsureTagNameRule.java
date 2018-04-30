package com.api.rules;

import com.api.factory.EnumRule;
import com.api.factory.SwaggerRuleFailure;
import io.swagger.models.Swagger;

import java.util.List;

public final class EnsureTagNameRule extends SwaggerRule {

    public EnsureTagNameRule() {
        super(EnumRule.RULE0015);
    }

    @Override
    public List<SwaggerRuleFailure> execute(Swagger swagger) throws Exception {
        return null;
    }
}
