package com.api;

import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;

public class SwaggerAnalyzer {

    public SwaggerAnalyzer(String filePath) {
        SwaggerParser p = new SwaggerParser();
        Swagger s = p.read(filePath);
    }

}
