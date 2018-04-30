package com.api.rules;

import com.api.factory.SwaggerRuleFailure;
import com.api.factory.SwaggerRuleType;
import io.swagger.models.HttpMethod;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Response;
import io.swagger.models.Swagger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class EnsureValidJsonEntity implements SwaggerRule {

    private static final String RULENAME = "RULE0007";
    private static final String MESSAGE = "O path %s retorna um valor primitivo ao invés de uma JSON válido.";

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
        return "As entidades de retorno das APIs deve ser um JSON válido e não um valor primitivo.";
    }

    @Override
    public List<SwaggerRuleFailure> execute(Swagger swagger) {

        final String expectedResponseType = "application/json; charset=UTF-8";

        ArrayList<SwaggerRuleFailure> failures = new ArrayList<>();

        for (Map.Entry<String, Path> pathEntry : swagger.getPaths().entrySet()) {

            Set<Map.Entry<HttpMethod, Operation>> operationsEntry = pathEntry.getValue().getOperationMap().entrySet();

            operationsEntry.removeIf(operationEntry ->
                    operationEntry.getValue().getProduces() == null ||
                    !operationEntry.getValue().getProduces().contains(expectedResponseType)
            );

            for (Map.Entry<HttpMethod, Operation> operationEntry : operationsEntry) {

                Set<Map.Entry<String, Response>> responsesEntry = operationEntry.getValue().getResponses().entrySet();

                responsesEntry.removeIf(responseEntry -> Integer.valueOf(responseEntry.getKey()) != 2);

                for (Map.Entry<String, Response> responseEntry : responsesEntry) {

                    if (responseEntry.getValue().getResponseSchema() == null) {
                        SwaggerRuleFailure failure = new SwaggerRuleFailure(
                                getName(),
                                String.format(MESSAGE, operationEntry.getKey().name() + " " + pathEntry.getKey()),
                                getType()
                        );

                        failures.add(failure);
                    }
                }
            }
        }

        return failures;
    }
}