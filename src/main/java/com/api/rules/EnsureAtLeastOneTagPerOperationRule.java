package com.api.rules;

import com.api.factory.EnumRule;
import com.api.factory.SwaggerRuleFailure;
import io.swagger.models.HttpMethod;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Swagger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class EnsureAtLeastOneTagPerOperationRule extends SwaggerRule {

    public EnsureAtLeastOneTagPerOperationRule() {
        super(EnumRule.RULE0018);
    }

    @Override
    public List<SwaggerRuleFailure> execute(Swagger swagger) {

        ArrayList<SwaggerRuleFailure> failures = new ArrayList<>();

        for (Map.Entry<String, Path> pathEntry : swagger.getPaths().entrySet()) {

            for (Map.Entry<HttpMethod, Operation> operationEntry : pathEntry.getValue().getOperationMap().entrySet()) {

                if (operationEntry.getValue().getTags() == null || operationEntry.getValue().getTags().size() < 1) {
                    failures.add(new SwaggerRuleFailure(
                            getName(),
                            String.format(getMessage(), operationEntry.getKey().name() + " " + pathEntry.getKey()),
                            getSwaggerRuleType()
                    ));
                }
            }
        }

        return failures;
    }
}
