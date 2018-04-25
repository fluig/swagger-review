package com.api.rules;

import com.api.factory.EnumRule;
import com.api.factory.SwaggerRuleFailure;
import com.api.factory.SwaggerRuleType;
import io.swagger.models.HttpMethod;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Swagger;
import io.swagger.models.parameters.Parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class EnsurePathParam404ResponseRule extends SwaggerRule {

    public EnsurePathParam404ResponseRule() {
        super(EnumRule.RULE0004);
    }

    @Override
    public List<SwaggerRuleFailure> execute(Swagger swagger) {

        final String expectedParameterLocation = "path";
        final String expectedResponseCode = "404";

        ArrayList<SwaggerRuleFailure> failures = new ArrayList<>();

        for (Map.Entry<String, Path> pathEntry : swagger.getPaths().entrySet()) {

            for (Map.Entry<HttpMethod, Operation> operationEntry : pathEntry.getValue().getOperationMap().entrySet()) {

                for (Parameter parameter : operationEntry.getValue().getParameters()) {

                    if (parameter.getIn().equals(expectedParameterLocation) &&
                            !operationEntry.getValue().getResponses().containsKey(expectedResponseCode)) {

                        SwaggerRuleFailure failure = new SwaggerRuleFailure(
                                getName(),
                                String.format(getMessage(), operationEntry.getKey().name() + " " + pathEntry.getKey()),
                                getSwaggerRuleType()
                        );

                        failures.add(failure);

                        break;
                    }
                }
            }
        }

        return failures;
    }
}
