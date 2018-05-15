package com.api;

import com.api.factory.FactoryRules;
import com.api.factory.SwaggerRuleType;
import com.api.rules.SwaggerRule;
import com.api.factory.SwaggerRuleFailure;
import com.google.gson.Gson;
import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public static String SUFIX_FILE_NAME = "ReportValidation";

    public static void executeValidation(Path pathBaseDir, ArrayList<String> pathsToIgnore, ArrayList<String> ignoreRules) {

        ArrayList<SwaggerRuleFailure> swaggerRuleFailures = new ArrayList<>();

        String pathWithErrors = "";

        try {

            List<Path> javaFiles = Files.find(
                    pathBaseDir,
                    Integer.MAX_VALUE,
                    (path, basicFileAttributes) -> path.toFile().getName().endsWith(".json"),
                    FileVisitOption.FOLLOW_LINKS
            ).collect(Collectors.toList());

            // Via reflection carrega uma lista de regras

            for (Path path : javaFiles) {

                boolean isValid = true;

                for (String ignorePath : pathsToIgnore) {

                    if (path.toString().contains(ignorePath)) {
                        isValid = false;
                    }

                }

                if (isValid) {

                    SwaggerParser swaggerParser = new SwaggerParser();

                    Swagger swagger = swaggerParser.read(path.toString());

                    for (SwaggerRule swaggerRule : FactoryRules.getRules(ignoreRules)) {

                        try {
                            swaggerRuleFailures.addAll(swaggerRule.execute(swagger));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }

                    if (swaggerRuleFailures.size() > 0) {

                        Gson gson = new Gson();

                        String json = gson.toJson(swaggerRuleFailures);

                        pathWithErrors = Utils.saveFile(path.toString().substring(0, path.toString().lastIndexOf(File.separator)), swagger.getInfo().getTitle(), json);

                    }

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (swaggerRuleFailures.size() > 0){

            for (SwaggerRuleFailure swaggerRuleFailure : swaggerRuleFailures){

                if (swaggerRuleFailure.getType().equals(SwaggerRuleType.ERROR)){
                    throw new RuntimeException("Validation Report with Errors in " + pathWithErrors);
                }

            }

        }

    }

    public static String saveFile(String pathTosave, String fileName, String json) {

        String nameFile = pathTosave + File.separator + fileName + SUFIX_FILE_NAME + ".json";

        try {

            File file = new File(nameFile);

            if (file.exists()) {
                file.delete();
            }

            byte data[] = json.getBytes();

            FileOutputStream out = new FileOutputStream(file);

            out.write(data);

            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return nameFile;

    }
}
