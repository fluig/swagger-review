package com.api.rules;

import com.api.factory.EnumRule;
import com.api.factory.SwaggerRuleFailure;
import io.swagger.models.HttpMethod;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Swagger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class EnsureResponse201WithJsonFromPostRule extends SwaggerRule {

    private static final String CODE = "201";

    public EnsureResponse201WithJsonFromPostRule() {
        super(EnumRule.RULE0005);
    }

    @Override
    public List<SwaggerRuleFailure> execute(Swagger swagger) {

        ArrayList<SwaggerRuleFailure> failures = new ArrayList<>();

        for (Map.Entry<String, Path> entryPath : swagger.getPaths().entrySet()) {

            for (Map.Entry<HttpMethod, Operation> entryOperation : entryPath.getValue().getOperationMap().entrySet()) {

                if (entryOperation.getKey().equals(HttpMethod.POST)){

                    if (entryOperation.getValue().getResponses().containsKey(CODE)){

                        if (entryOperation.getValue().getResponses().get(CODE).getResponseSchema() == null ||
                                entryOperation.getValue().getResponses().get(CODE).getResponseSchema().getReference() == null){

                            SwaggerRuleFailure failure = new SwaggerRuleFailure(
                                    getName(),
                                    String.format(getMessage(), entryOperation.getKey().name() + " " + entryPath.getKey()),
                                    getSwaggerRuleType()
                            );
                        failures.add(failure);

                        }

                    }

                }

            }
        }

        return failures;
    }
}
