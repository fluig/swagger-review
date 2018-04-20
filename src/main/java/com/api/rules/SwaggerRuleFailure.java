package com.api.rules;

public class SwaggerRuleFailure {
    private String sourceRuleName;
    private String fileName;
    private String message;
    private SwaggerRuleType type;

    public SwaggerRuleFailure(String sourceRuleName, String fileName, String message, SwaggerRuleType type){
        this.sourceRuleName = sourceRuleName;
        this.message = message;
        this.type = type;
    }

}
