package com.api.factory;

import com.api.rules.EnsureHttpHttpsRule;
import com.api.rules.EnsureNameSuffixRule;
import com.api.rules.EnsureOperationIdRule;
import com.api.rules.EnsureValidVerbsRule;
import com.api.rules.SwaggerRule;

import java.util.ArrayList;
import java.util.List;

public class FactoryRules {

    public static List<SwaggerRule> getRules(){

        ArrayList<SwaggerRule> swaggerRules = new ArrayList<>();

        swaggerRules.add(new EnsureHttpHttpsRule());
        swaggerRules.add(new EnsureOperationIdRule());
        swaggerRules.add(new EnsureValidVerbsRule());
        swaggerRules.add(new EnsureNameSuffixRule());

        return swaggerRules;

    }

}
