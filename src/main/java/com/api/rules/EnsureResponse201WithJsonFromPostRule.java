package com.api.rules;

import com.api.factory.SwaggerRuleFailure;
import com.api.factory.SwaggerRuleType;
import io.swagger.models.HttpMethod;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Swagger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class EnsureResponse201WithJsonFromPostRule implements SwaggerRule {
    private static final String RULENAME = "RULE0005";
    private static final String MESSAGE = "O path %s usa o método POST e portanto, quando for status 201, deve retornar a entidade que está sendo atualizada";
    private static final String CODE = "201";

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
        return "Os métodos do tipo POST devem retornar as entidades que estão sendo atualizadas quando for status 201.";
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
                                    String.format(MESSAGE, entryOperation.getKey().name() + " " + entryPath.getKey()),
                                    getType()
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
