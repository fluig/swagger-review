package com.api;

import com.api.rules.EnsureHttpHttpsRule;
import com.api.rules.SwaggerRule;
import com.api.rules.SwaggerRuleFailure;
import com.google.gson.Gson;
import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public static void executeValidation(Path pathBaseDir){

        try {
            List<Path> javaFiles = Files.find(
                    pathBaseDir,
                    Integer.MAX_VALUE,
                    (path, basicFileAttributes) -> path.toFile().getName().endsWith(".json"),
                    FileVisitOption.FOLLOW_LINKS
            ).collect(Collectors.toList());

            // Via reflection carrega uma lista de regras

            for (Path path : javaFiles) {

                if (!path.toString().contains("target")) {

                    ArrayList<SwaggerRuleFailure> swaggerRuleFailures = new ArrayList<>();

                    SwaggerParser swaggerParser = new SwaggerParser();

                    Swagger swagger = swaggerParser.read(path.toString());

                    // para cada regra
                    // result.add( regra.execute())

                    SwaggerRule swaggerRule = new EnsureHttpHttpsRule();

                    swaggerRuleFailures.addAll(swaggerRule.execute(swagger));

                    if (swaggerRuleFailures.size() > 0){

                        Gson gson = new Gson();

                        String json = gson.toJson(swaggerRuleFailures);

                        Utils.saveFile(path.toString().substring(0, path.toString().lastIndexOf(File.separator)), swagger.getInfo().getTitle(), json);

                    }

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void saveFile(String pathTosave, String fileName, String json){

        try {

            byte data[] = json.getBytes();

            FileOutputStream out = new FileOutputStream(pathTosave + File.separator + fileName + "Validation" + ".json");

            out.write(data);

            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
