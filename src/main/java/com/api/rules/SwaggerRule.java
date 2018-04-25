package com.api.rules;

import com.api.factory.EnumRule;
import com.api.factory.SwaggerRuleFailure;
import com.api.factory.SwaggerRuleType;
import io.swagger.models.Swagger;

import java.util.List;

public abstract class SwaggerRule {

    EnumRule mEnumRule;

    public SwaggerRule(EnumRule enumRule){
        mEnumRule = enumRule;
    }

    public String getName(){
        return mEnumRule.toString();
    }

    public String getMessage(){
        return mEnumRule.getMessage();
    }

    public String getDescription(){
        return mEnumRule.getDescription();
    }

    public SwaggerRuleType getSwaggerRuleType(){
        return mEnumRule.getSwaggerRuleType();
    }

    public abstract List<SwaggerRuleFailure> execute(Swagger swagger);

}
