package com.api.rules;

import io.swagger.models.HttpMethod;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Swagger;
import org.codehaus.plexus.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class EnsureOperationIdRule implements SwaggerRule {
    private static final String RULENAME = "RULE0010";
    private static final String MESSAGE = "O path {path} não define um operationID";

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
        return "Todas as operações devem definir um operationID.";
    }

    @Override
    public List<SwaggerRuleFailure> execute(Swagger swagger) {
        ArrayList<SwaggerRuleFailure> failures = new ArrayList<>();

        Map<String, Path> paths = swagger.getPaths();

        for (Map.Entry<String, Path> entryPath : swagger.getPaths().entrySet()) {
            for (Map.Entry<HttpMethod, Operation> entryOperation : entryPath.getValue().getOperationMap().entrySet()) {
                if (StringUtils.isEmpty(entryOperation.getValue().getOperationId())) {
                    SwaggerRuleFailure failure = new SwaggerRuleFailure(
                        getName(),
                        String.format(MESSAGE, entryOperation.getKey().name() + entryPath.getKey()),
                        getType()
                    );
               }
            }
        }

        return failures;
    }
}
