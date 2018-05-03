package com.api.rules;

import com.api.factory.EnumRule;
import com.api.factory.SwaggerRuleFailure;
import io.swagger.models.Swagger;

import java.util.List;

public final class EnsureCollectionPathPluralName extends SwaggerRule {

    public EnsureCollectionPathPluralName() {
        super(EnumRule.RULE0017);
    }

    @Override
    public List<SwaggerRuleFailure> execute(Swagger swagger) {
        return null;
    }
}
