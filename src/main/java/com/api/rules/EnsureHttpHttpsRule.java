package com.api.rules;


import com.api.factory.SwaggerRuleFailure;
import com.api.factory.SwaggerRuleType;
import io.swagger.models.Scheme;
import io.swagger.models.Swagger;

import java.util.ArrayList;
import java.util.List;

public final class EnsureHttpHttpsRule implements SwaggerRule {
    private static final String RULENAME = "RULE0001";
    private static final String MESSAGE = "O documento swagger %s não está configurado para suportar http e https";

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
        return "Todas as APIs devem estar configuradas para suportar tanto HTTP quanto HTTPS";
    }

    @Override
    public List<SwaggerRuleFailure> execute(Swagger swagger) {
        ArrayList<SwaggerRuleFailure> failures = new ArrayList<>();

        ArrayList<String> list = new ArrayList<>();
        list.add("http");
        list.add("https");

        ArrayList<String> listScheme = new ArrayList<>();

        for (Scheme scheme : swagger.getSchemes()){
            listScheme.add(scheme.toValue());
        }

        if (!listScheme.containsAll(list)) {
            SwaggerRuleFailure failure = new SwaggerRuleFailure(getName(),
                    String.format(MESSAGE, swagger.getInfo().getTitle()),
                    getType()
            );

            failures.add(failure);

        }

        return failures;
    }
}
