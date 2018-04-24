package com.api.rules;

import com.api.factory.SwaggerRuleFailure;
import com.api.factory.SwaggerRuleType;
import io.swagger.models.HttpMethod;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Swagger;
import org.codehaus.plexus.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class EnsureValidVerbsRule implements SwaggerRule {
    private static final String RULENAME = "RULE0003";
    private static final String MESSAGE = "O path da api %s não deve conter verbos equivalentes aos métodos do protocolo http. Ex.: get, post, update, delete, create";

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
        return "Nenhuma das operações pode conter verbos equivalentes aos métodos do protocolo http.";
    }

    @Override
    public List<SwaggerRuleFailure> execute(Swagger swagger) {

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
                            String.format(MESSAGE, entryPath.getKey()),
                            getType()
                    ));

                    break;

                }

            }

        }

        return failures;
    }
}
