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

public final class EnsureOperationReturnFormatRule extends SwaggerRule {

    public EnsureOperationReturnFormatRule() {
        super(EnumRule.RULE0019);
    }

    @Override
    public List<SwaggerRuleFailure> execute(Swagger swagger) throws Exception {

        ArrayList<SwaggerRuleFailure> failures = new ArrayList<>();

        for (Map.Entry<String, Path> pathEntry : swagger.getPaths().entrySet()) {

            for (Map.Entry<HttpMethod, Operation> operationEntry : pathEntry.getValue().getOperationMap().entrySet()) {

                if (operationEntry.getValue().getProduces() == null || operationEntry.getValue().getProduces().size() < 1) {
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
