package com.api.rules;

import com.api.factory.EnumRule;
import com.api.factory.SwaggerRuleFailure;
import io.swagger.models.HttpMethod;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Swagger;
import io.swagger.models.parameters.Parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class EnsureCamelCaseQueryParamsRule extends SwaggerRule {

    public EnsureCamelCaseQueryParamsRule() {
        super(EnumRule.RULE0016);
    }

    @Override
    public List<SwaggerRuleFailure> execute(Swagger swagger) {

        final String expectedParameterType = "query";
        final String camelCasePattern = "^[a-z]+([A-Z]([a-zA-Z0-9])*)*";

        ArrayList<SwaggerRuleFailure> failures = new ArrayList<>();

        for (Map.Entry<String, Path> pathEntry : swagger.getPaths().entrySet()) {

            for (Map.Entry<HttpMethod, Operation> operationEntry : pathEntry.getValue().getOperationMap().entrySet()) {

                for (Parameter parameter : operationEntry.getValue().getParameters()) {

                    if (!parameter.getIn().equals(expectedParameterType)) {
                        continue;
                    }

                    Pattern pattern = Pattern.compile(camelCasePattern);
                    Matcher matcher = pattern.matcher(parameter.getName());

                    if (!matcher.matches()) {
                        failures.add(new SwaggerRuleFailure(
                                getName(),
                                String.format(getMessage(), operationEntry.getKey().name() + " " + pathEntry.getKey()),
                                getSwaggerRuleType()
                        ));
                    }
                }
            }
        }

        return failures;
    }
}
