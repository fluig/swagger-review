package com.api.rules;

import com.api.SwaggerUtils;
import com.api.factory.SwaggerRuleFailure;
import com.api.factory.SwaggerRuleType;
import io.swagger.models.HttpMethod;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Swagger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class EnsureCollectionSortingRule implements SwaggerRule {

    private static final String RULENAME = "RULE0012";
    private static final String MESSAGE = "O path %s retorna uma collection, mas não permite a operação de ordenação.";

    @Override
    public String getName() {
        return RULENAME;
    }

    @Override
    public SwaggerRuleType getType() {
        return SwaggerRuleType.WARNING;
    }

    @Override
    public String getDescription() {
        return "APIs que retornam collections devem permitir ordenação.";
    }

    @Override
    public List<SwaggerRuleFailure> execute(Swagger swagger) {

        final String expectedQueryParam = "order";

        List<String> collectionNames = SwaggerUtils.getAllCollectionNames(swagger);

        ArrayList<SwaggerRuleFailure> failures = new ArrayList<>();

        for (Map.Entry<String, Path> pathEntry : swagger.getPaths().entrySet()) {

            for (Map.Entry<HttpMethod, Operation> operationEntry : pathEntry.getValue().getOperationMap().entrySet()) {

                if (SwaggerUtils.operationReturnsCollection(operationEntry, collectionNames)) {

                    if (!SwaggerUtils.operationHasQueryParameter(operationEntry, expectedQueryParam)) {
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
