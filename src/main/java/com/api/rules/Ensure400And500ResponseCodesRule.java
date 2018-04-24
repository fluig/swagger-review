package com.api.rules;

import com.api.factory.SwaggerRuleFailure;
import com.api.factory.SwaggerRuleType;
import io.swagger.models.HttpMethod;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Response;
import io.swagger.models.Swagger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class Ensure400And500ResponseCodesRule implements SwaggerRule {

    private static final String RULENAME = "RULE0005";
    private static final String MESSAGE = "O path %s não mapeia os códigos de retorno 400 ou 500.";

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
        return "Todas as APIs devem mapear os códigos de resposta 400 e 500.";
    }

    @Override
    public List<SwaggerRuleFailure> execute(Swagger swagger) {

        List<String> expectedResponses = Arrays.asList("400", "500");

        ArrayList<SwaggerRuleFailure> failures = new ArrayList<>();

        for (Map.Entry<String, Path> pathEntry : swagger.getPaths().entrySet()) {

            for (Map.Entry<HttpMethod, Operation> operationEntry : pathEntry.getValue().getOperationMap().entrySet()) {

                Map<String, Response> responsesEntry = operationEntry.getValue().getResponses();

                for (String responseCode: expectedResponses) {

                    if (!responsesEntry.containsKey(responseCode)) {
                        failures.add(new SwaggerRuleFailure(
                                getName(),
                                String.format(MESSAGE, operationEntry.getKey().name() + " " + pathEntry.getKey()),
                                getType()
                        ));

                        break;
                    }
                }
            }
        }

        return failures;
    }
}
