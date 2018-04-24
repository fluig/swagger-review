package com.api.rules;

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

public final class EnsurePathParam404ResponseRule implements SwaggerRule {

    private static final String RULENAME = "RULE0003";
    private static final String MESSAGE = "O path da api %s possui um placeholder que identifica uma entidade e portanto deve mapear o código de erro 404.";

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
        return "Paths que identificam uma entidade pelo id/code devem retornar 404.";
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
                                String.format(MESSAGE, operationEntry.getKey().name() + " " + pathEntry.getKey()),
                                getType()
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
