package com.api.rules;

import io.swagger.models.Swagger;

import java.util.ArrayList;
import java.util.List;

public final class EnsureHttpHttpsRule implements SwaggerRule {
    private static final String RULENAME = "RULE0001";
    private static final String MESSAGE = "O documento swagger '{0}' não está configurado para suportar http ou https";


    @Override
    public String getName() {
        return RULENAME;
    }

    @Override
    public SwaggerRuleType getType() {
        return SwaggerRuleType.ERROR;
    }

    @Override
    public List<SwaggerRuleFailure> execute(Swagger swagger) {
        ArrayList<SwaggerRuleFailure> failures = new ArrayList<>();
        ArrayList<String> list = new ArrayList<>();
        list.add("http");
        list.add("https");

        if (!swagger.getSchemes().containsAll(list)) {
            SwaggerRuleFailure failure = new SwaggerRuleFailure(getName(),
                    "",
                    String.format(MESSAGE, swagger.getInfo().getTitle()),
                    getType()
            );

            failures.add(failure);

        }

        return failures;
    }
}
