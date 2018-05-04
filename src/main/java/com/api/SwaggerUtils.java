package com.api;

import io.swagger.models.HttpMethod;
import io.swagger.models.Model;
import io.swagger.models.Operation;
import io.swagger.models.Response;
import io.swagger.models.Swagger;
import io.swagger.models.parameters.Parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SwaggerUtils {

    private SwaggerUtils() {
    }

    public static String getLastNonParamResourceFromPath(String path) {
        if (path == null || path.length() == 0) {
            return null;
        }

        String[] resources = path.split("/");

        for (int i = resources.length - 1; i > -1; --i) {
            final String resource = resources[i];
            if (resource.contains("{")) {
                continue;
            }

            return resource;
        }

        return null;
    }

    //region Collection Responses
    public static List<String> getAllCollectionNames(Swagger swagger) {
        ArrayList<String> collectionNames = new ArrayList<>();

        for (Map.Entry<String, Model> modelEntry : swagger.getDefinitions().entrySet()) {

            if (modelEntry.getValue().getProperties().containsKey("items") &&
                    modelEntry.getValue().getProperties().get("items").getType().equals("array") &&
                    modelEntry.getValue().getProperties().containsKey("hasNext")) {

                collectionNames.add(modelEntry.getKey());
            }
        }

        return collectionNames;
    }

    public static boolean operationReturnsCollection(Map.Entry<HttpMethod, Operation> operationEntry,
                                                     List<String> collections) {
        for (Map.Entry<String, Response> responseEntry : operationEntry.getValue().getResponses().entrySet()) {

            if (responseEntry.getValue().getResponseSchema() != null && responseEntry.getValue().getResponseSchema().getReference() != null) {

                String reference = responseEntry.getValue().getResponseSchema().getReference().
                        replace("#/definitions/", "");

                for (String collection : collections) {
                    if (reference.equals(collection)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
    //endregion

    //region Parameters
    private static boolean operationHasParameter(Map.Entry<HttpMethod, Operation> operationEntry,
                                                 String parameterName,
                                                 String parameterType) {

        for (Parameter parameter : operationEntry.getValue().getParameters()) {

            if (parameter.getName().equals(parameterName) &&
                    parameter.getIn().equals(parameterType)) {
                return true;
            }
        }

        return false;
    }

    public static boolean operationHasQueryParameter(Map.Entry<HttpMethod, Operation> operationEntry,
                                                     String parameterName) {

        return operationHasParameter(operationEntry, parameterName, "query");
    }
    //endregion
}
