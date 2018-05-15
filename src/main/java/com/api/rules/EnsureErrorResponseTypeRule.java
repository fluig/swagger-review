package com.api.rules;

import com.api.factory.EnumRule;
import com.api.factory.SwaggerRuleFailure;
import io.swagger.models.HttpMethod;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Response;
import io.swagger.models.Swagger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class EnsureErrorResponseTypeRule extends SwaggerRule {

    public EnsureErrorResponseTypeRule() {
        super(EnumRule.RULE0020);
    }

    @Override
    public List<SwaggerRuleFailure> execute(Swagger swagger) {

        final String expectedResponseType = "ErrorResponse";

        ArrayList<SwaggerRuleFailure> failures = new ArrayList<>();

        for (Map.Entry<String, Path> pathEntry : swagger.getPaths().entrySet()) {

            for (Map.Entry<HttpMethod, Operation> operationEntry : pathEntry.getValue().getOperationMap().entrySet()) {

                operationEntry.getValue().getResponses().entrySet().removeIf(
                        responseEntry ->
                                Integer.valueOf(responseEntry.getKey()) < 4 || Integer.valueOf(responseEntry.getKey()) > 5
                );

                for (Map.Entry<String, Response> responseEntry : operationEntry.getValue().getResponses().entrySet()) {

                    if (responseEntry.getValue().getResponseSchema() == null ||
                            responseEntry.getValue().getResponseSchema().getReference() == null) {

                        failures.add(getFailure(operationEntry.getKey().name(), pathEntry.getKey(), responseEntry.getKey()));
                        continue;
                    }

                    final String reference = responseEntry.getValue().getResponseSchema().getReference().
                            replace("#/definitions/", "");

                    if (!reference.equals(expectedResponseType)) {
                        failures.add(getFailure(operationEntry.getKey().name(), pathEntry.getKey(), responseEntry.getKey()));
                    }
                }
            }
        }

        return failures;
    }

    private SwaggerRuleFailure getFailure(String operation, String path, String errorCode) {
        return new SwaggerRuleFailure(
                getName(),
                String.format(
                        getMessage(),
                        operation + " " + path,
                        errorCode
                ),
                getSwaggerRuleType()
        );
    }
}
