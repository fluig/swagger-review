package com.api.rules;

import com.api.factory.SwaggerRuleFailure;
import com.api.factory.SwaggerRuleType;
import io.swagger.models.HttpMethod;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Swagger;
import org.codehaus.plexus.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class EnsureUniqueOperationIdRule implements SwaggerRule {

    private static final String RULENAME = "RULE0011";
    private static final String MESSAGE = "O path %s com o operationID %s não é único.";

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
        return "Cada operação deve ter um operationID único.";
    }

    @Override
    public List<SwaggerRuleFailure> execute(Swagger swagger) {

        Set<String> uniqueOperationIds = new HashSet<>();

        ArrayList<SwaggerRuleFailure> failures = new ArrayList<>();

        for (Map.Entry<String, Path> pathEntry : swagger.getPaths().entrySet()) {

            pathEntry.getValue().getOperationMap().entrySet().removeIf(
                    operationEntry -> StringUtils.isEmpty(operationEntry.getValue().getOperationId())
            );

            for (Map.Entry<HttpMethod, Operation> operationEntry : pathEntry.getValue().getOperationMap().entrySet()) {

                String operationId = operationEntry.getValue().getOperationId();

                if (uniqueOperationIds.contains(operationId)) {
                    failures.add(new SwaggerRuleFailure(
                            getName(),
                            String.format(MESSAGE, operationEntry.getKey().name() + " " + pathEntry.getKey(), operationId),
                            getType()
                    ));
                }

                uniqueOperationIds.add(operationId);
            }
        }

        return failures;
    }
}
