package com.api.rules;

import com.api.factory.EnumRule;
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

public final class Ensure400And500ResponseCodesRule extends SwaggerRule {

    public Ensure400And500ResponseCodesRule() {
        super(EnumRule.RULE0002);
    }

    @Override
    public List<SwaggerRuleFailure> execute(Swagger swagger) throws Exception {

        List<String> expectedResponses = Arrays.asList("400", "500");

        ArrayList<SwaggerRuleFailure> failures = new ArrayList<>();

        for (Map.Entry<String, Path> pathEntry : swagger.getPaths().entrySet()) {

            for (Map.Entry<HttpMethod, Operation> operationEntry : pathEntry.getValue().getOperationMap().entrySet()) {

                Map<String, Response> responsesEntry = operationEntry.getValue().getResponses();

                for (String responseCode: expectedResponses) {

                    if (!responsesEntry.containsKey(responseCode)) {
                        failures.add(new SwaggerRuleFailure(
                                getName(),
                                String.format(getMessage(), operationEntry.getKey().name() + " " + pathEntry.getKey()),
                                getSwaggerRuleType()
                        ));

                        break;
                    }
                }
            }
        }

        return failures;
    }
}
