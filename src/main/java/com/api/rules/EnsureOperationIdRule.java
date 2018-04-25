package com.api.rules;

import com.api.factory.EnumRule;
import com.api.factory.SwaggerRuleFailure;
import io.swagger.models.HttpMethod;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Swagger;
import org.codehaus.plexus.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class EnsureOperationIdRule extends SwaggerRule {

    public EnsureOperationIdRule() {
        super(EnumRule.RULE0010);
    }

    @Override
    public List<SwaggerRuleFailure> execute(Swagger swagger) {

        ArrayList<SwaggerRuleFailure> failures = new ArrayList<>();

        for (Map.Entry<String, Path> entryPath : swagger.getPaths().entrySet()) {
            for (Map.Entry<HttpMethod, Operation> entryOperation : entryPath.getValue().getOperationMap().entrySet()) {
                if (StringUtils.isEmpty(entryOperation.getValue().getOperationId())) {
                    SwaggerRuleFailure failure = new SwaggerRuleFailure(
                        getName(),
                        String.format(getMessage(), entryOperation.getKey().name() + " " + entryPath.getKey()),
                        getSwaggerRuleType()
                    );
                    failures.add(failure);
               }
            }
        }

        return failures;
    }
}
