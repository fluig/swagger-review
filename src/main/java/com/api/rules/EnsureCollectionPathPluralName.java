package com.api.rules;

import com.api.SwaggerUtils;
import com.api.factory.EnumRule;
import com.api.factory.SwaggerRuleFailure;
import io.swagger.models.HttpMethod;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Swagger;
import org.jibx.schema.codegen.extend.DefaultNameConverter;
import org.jibx.schema.codegen.extend.NameConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class EnsureCollectionPathPluralName extends SwaggerRule {

    public EnsureCollectionPathPluralName() {
        super(EnumRule.RULE0017);
    }

    private List<String> getIrregularResourceNames() {
        ArrayList<String> irregularResourceNames = new ArrayList<>();
        irregularResourceNames.add("data");
        return irregularResourceNames;
    }

    @Override
    public List<SwaggerRuleFailure> execute(Swagger swagger) {

        final List<String> collectionNames = SwaggerUtils.getAllCollectionNames(swagger);

        ArrayList<SwaggerRuleFailure> failures = new ArrayList<>();

        for (Map.Entry<String, Path> pathEntry : swagger.getPaths().entrySet()) {

            for (Map.Entry<HttpMethod, Operation> operationEntry : pathEntry.getValue().getOperationMap().entrySet()) {

                if (SwaggerUtils.operationReturnsCollection(operationEntry, collectionNames)) {
                    final String resource = SwaggerUtils.getLastNonParamResourceFromPath(pathEntry.getKey());
                    if (resource == null) {
                        continue;
                    }

                    final NameConverter converter = new DefaultNameConverter();
                    final String singular = converter.depluralize(resource);
                    if (getIrregularResourceNames().contains(singular)) {
                        continue;
                    }

                    if (singular.equals(resource)) {
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
