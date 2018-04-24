package com.api.rules;

import com.api.factory.SwaggerRuleFailure;
import com.api.factory.SwaggerRuleType;
import io.swagger.models.HttpMethod;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Response;
import io.swagger.models.Swagger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EnsureNameSuffixRule implements SwaggerRule {

    private static final String RULENAME = "RULE0006";
    private static final String MESSAGE = "A entidade de retorno %s não deve conter os sufixos: DTO, VO.";

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
        return "As entidades de retorno não podem conter os sufixos DTO ou VO.";
    }

    @Override
    public List<SwaggerRuleFailure> execute(Swagger swagger) {

        ArrayList<String> entityNames = new ArrayList<>();

        entityNames.add("DTO");
        entityNames.add("VO");

        ArrayList<SwaggerRuleFailure> failures = new ArrayList<>();

        for (Map.Entry<String, Path> pathEntry : swagger.getPaths().entrySet()) {

            for (Map.Entry<HttpMethod, Operation> operationEntry : pathEntry.getValue().getOperationMap().entrySet()) {

                Set<Map.Entry<String, Response>> responses = operationEntry.getValue().getResponses().entrySet();

                responses.removeIf(responseEntry -> {
                    int code = Integer.valueOf(responseEntry.getKey());
                    return (code / 100) != 2 || responseEntry.getValue().getResponseSchema() == null;
                });

                for (Map.Entry<String, Response> responseEntry: responses) {

                    String reference = responseEntry.getValue().getResponseSchema().getReference();

                    for (String entityName: entityNames) {

                        if (reference.endsWith(entityName)) {
                            failures.add(new SwaggerRuleFailure(
                                    getName(),
                                    String.format(MESSAGE, reference),
                                    getType()
                            ));

                            break;
                        }
                    }
                }
            }
        }

        return failures;
    }
}
