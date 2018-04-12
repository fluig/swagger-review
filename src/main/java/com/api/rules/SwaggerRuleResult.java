package com.api.rules;

import java.util.List;

public class SwaggerRuleResult {
    public final boolean success;
    public List<String> errors;

    public SwaggerRuleResult(boolean success, List<String> errors) {
        this.success = success;
        this.errors = errors;
    }

}
