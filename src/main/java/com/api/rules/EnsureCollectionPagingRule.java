package com.api.rules;

import com.api.SwaggerUtils;
import com.api.factory.EnumRule;
import com.api.factory.SwaggerRuleFailure;
import io.swagger.models.HttpMethod;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Swagger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class EnsureCollectionPagingRule extends SwaggerRule {

    public EnsureCollectionPagingRule(EnumRule enumRule) {
        super(EnumRule.RULE0014);
    }

    @Override
    public List<SwaggerRuleFailure> execute(Swagger swagger) {
        return null;
    }
}
