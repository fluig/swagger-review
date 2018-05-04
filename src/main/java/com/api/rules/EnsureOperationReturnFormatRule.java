package com.api.rules;

import com.api.factory.EnumRule;
import com.api.factory.SwaggerRuleFailure;
import io.swagger.models.Swagger;

import java.util.List;

public final class EnsureOperationReturnFormatRule extends SwaggerRule {

    public EnsureOperationReturnFormatRule() {
        super(EnumRule.RULE0019);
    }

    @Override
    public List<SwaggerRuleFailure> execute(Swagger swagger) throws Exception {
        return null;
    }
}
