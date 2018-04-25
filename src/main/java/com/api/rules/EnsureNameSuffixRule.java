package com.api.rules;

import com.api.factory.EnumRule;
import com.api.factory.SwaggerRuleFailure;
import io.swagger.models.Model;
import io.swagger.models.Swagger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class EnsureNameSuffixRule extends SwaggerRule {

    public EnsureNameSuffixRule() {
        super(EnumRule.RULE0006);
    }

    @Override
    public List<SwaggerRuleFailure> execute(Swagger swagger) {

        ArrayList<String> entitySuffixes = new ArrayList<>();
        entitySuffixes.add("DTO");
        entitySuffixes.add("VO");

        ArrayList<SwaggerRuleFailure> failures = new ArrayList<>();

        for (Map.Entry<String, Model> modelEntry : swagger.getDefinitions().entrySet()) {

            for (String suffix: entitySuffixes) {

                if (modelEntry.getKey().endsWith(suffix)) {
                    failures.add(new SwaggerRuleFailure(
                            getName(),
                            String.format(getMessage(), modelEntry.getKey()),
                            getSwaggerRuleType()
                    ));

                    break;
                }
            }
        }

        return failures;
    }
}
