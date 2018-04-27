package com.api.rules;

import com.api.factory.EnumRule;
import com.api.factory.SwaggerRuleFailure;
import io.swagger.models.*;
import io.swagger.models.parameters.BodyParameter;
import io.swagger.models.parameters.Parameter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class EnsureMethodsWithoutBodyRule extends SwaggerRule {

    public EnsureMethodsWithoutBodyRule() {
        super(EnumRule.RULE0007);
    }

    @Override
    public List<SwaggerRuleFailure> execute(Swagger swagger) throws Exception {

        List<HttpMethod> expectedMethods = Arrays.asList(HttpMethod.DELETE, HttpMethod.GET, HttpMethod.HEAD, HttpMethod.OPTIONS);

        ArrayList<SwaggerRuleFailure> failures = new ArrayList<>();

        for (Map.Entry<String, Path> pathEntry : swagger.getPaths().entrySet()) {

            for (Map.Entry<HttpMethod, Operation> operationEntry : pathEntry.getValue().getOperationMap().entrySet()) {

                if (expectedMethods.contains(operationEntry.getKey())){

                    if (operationEntry.getValue().getParameters() != null && operationEntry.getValue().getParameters().size() > 0){

                        for (Parameter parameter : operationEntry.getValue().getParameters()){

                            if (parameter instanceof BodyParameter){

                                SwaggerRuleFailure failure = new SwaggerRuleFailure(
                                        getName(),
                                        String.format(getMessage(), pathEntry.getKey(), operationEntry.getKey().name()),
                                        getSwaggerRuleType()
                                );

                                failures.add(failure);

                                break;

                            }

                        }

                    }

                }

            }
        }

        return failures;
    }
}
