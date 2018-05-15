package com.api.factory;

public class SwaggerRuleFailure {
    private String sourceRuleName;
    private String message;
    private SwaggerRuleType type;

    public SwaggerRuleFailure(String sourceRuleName, String message, SwaggerRuleType type){
        this.sourceRuleName = sourceRuleName;
        this.message = message;
        this.type = type;
    }

    public SwaggerRuleType getType() {
        return type;
    }
}
