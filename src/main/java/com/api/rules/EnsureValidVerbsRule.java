package com.api.rules;

import com.api.factory.EnumRule;
import com.api.factory.SwaggerRuleFailure;
import io.swagger.models.Path;
import io.swagger.models.Swagger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class EnsureValidVerbsRule extends SwaggerRule {

    public EnsureValidVerbsRule() {
        super(EnumRule.RULE0003);
    }

    @Override
    public List<SwaggerRuleFailure> execute(Swagger swagger) throws Exception {

        ArrayList<String> methodNames = new ArrayList<>();

        methodNames.add("get");
        methodNames.add("post");
        methodNames.add("update");
        methodNames.add("delete");
        methodNames.add("create");

        ArrayList<SwaggerRuleFailure> failures = new ArrayList<>();

        for (Map.Entry<String, Path> entryPath : swagger.getPaths().entrySet()) {

            for(String methodName : methodNames){

                if (entryPath.getKey().contains(methodName)){

                    failures.add(new SwaggerRuleFailure(
                            getName(),
                            String.format(getMessage(), entryPath.getKey()),
                            getSwaggerRuleType()
                    ));

                    break;

                }

            }

        }

        return failures;
    }
}
