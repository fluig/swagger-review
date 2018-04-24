package com.api.rules;

import com.api.factory.SwaggerRuleFailure;
import com.api.factory.SwaggerRuleType;
import io.swagger.models.HttpMethod;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Swagger;
import org.codehaus.plexus.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EnsureOperationDescriptionRule implements SwaggerRule {

    private static final String RULENAME = "RULE0009";
    private static final String MESSAGE = "O path %s não contém uma descrição.";

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
        return "Todas as operações devem ter uma descrição.";
    }

    @Override
    public List<SwaggerRuleFailure> execute(Swagger swagger) {

        ArrayList<SwaggerRuleFailure> failures = new ArrayList<>();

        for (Map.Entry<String, Path> pathEntry : swagger.getPaths().entrySet()) {

            for (Map.Entry<HttpMethod, Operation> operationEntry : pathEntry.getValue().getOperationMap().entrySet()) {

                if (StringUtils.isEmpty(operationEntry.getValue().getDescription())) {
                    SwaggerRuleFailure failure = new SwaggerRuleFailure(
                            getName(),
                            String.format(MESSAGE, operationEntry.getKey().name() + " " + pathEntry.getKey()),
                            getType()
                    );

                    failures.add(failure);
                }
            }
        }

        return failures;
    }
}
