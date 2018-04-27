package com.api.rules;

import com.api.SwaggerUtils;
import com.api.factory.EnumRule;
import com.api.factory.SwaggerRuleFailure;
import io.swagger.models.HttpMethod;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Swagger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class EnsureCollectionPagingRule extends SwaggerRule {

    public EnsureCollectionPagingRule() {
        super(EnumRule.RULE0014);
    }

    @Override
    public List<SwaggerRuleFailure> execute(Swagger swagger) throws Exception {

        final String expectedQueryParam = "page";

        List<String> collectionNames = SwaggerUtils.getAllCollectionNames(swagger);

        ArrayList<SwaggerRuleFailure> failures = new ArrayList<>();

        for (Map.Entry<String, Path> pathEntry : swagger.getPaths().entrySet()) {

            for (Map.Entry<HttpMethod, Operation> operationEntry : pathEntry.getValue().getOperationMap().entrySet()) {

                if (SwaggerUtils.operationReturnsCollection(operationEntry, collectionNames)) {

                    if (!SwaggerUtils.operationHasQueryParameter(operationEntry, expectedQueryParam)) {
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
