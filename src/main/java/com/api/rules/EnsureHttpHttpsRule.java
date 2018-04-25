package com.api.rules;


import com.api.factory.EnumRule;
import com.api.factory.SwaggerRuleFailure;
import io.swagger.models.Scheme;
import io.swagger.models.Swagger;

import java.util.ArrayList;
import java.util.List;

public final class EnsureHttpHttpsRule extends SwaggerRule {

    public EnsureHttpHttpsRule() {
        super(EnumRule.RULE0001);
    }

    public String aaa(){
        return "sdfsdf";
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
                    String.format(getMessage(), swagger.getInfo().getTitle()),
                    getSwaggerRuleType()
            );

            failures.add(failure);

        }

        return failures;

    }

}
