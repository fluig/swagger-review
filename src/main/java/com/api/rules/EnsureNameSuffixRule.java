package com.api.rules;

import com.api.factory.SwaggerRuleFailure;
import com.api.factory.SwaggerRuleType;
import io.swagger.models.Model;
import io.swagger.models.Swagger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class EnsureNameSuffixRule implements SwaggerRule {

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

        for (Map.Entry<String, Model> modelEntry : swagger.getDefinitions().entrySet()) {

            for (String entityName: entityNames) {

                if (modelEntry.getKey().endsWith(entityName)) {
                    failures.add(new SwaggerRuleFailure(
                            getName(),
                            String.format(MESSAGE, modelEntry.getKey()),
                            getType()
                    ));

                    break;
                }
            }
        }

        return failures;
    }
}
