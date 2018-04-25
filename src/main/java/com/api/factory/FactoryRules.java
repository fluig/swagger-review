package com.api.factory;

import com.api.rules.SwaggerRule;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class FactoryRules {

    public static List<SwaggerRule> getRules(ArrayList<String> ignoreRules){

        ArrayList<SwaggerRule> swaggerRules = new ArrayList<>();

        try {

            Reflections reflections = new Reflections("com.api.rules");

            Set<Class<? extends SwaggerRule>> allClasses =
                    reflections.getSubTypesOf(SwaggerRule.class);

            Iterator<Class<? extends SwaggerRule>> iterator = allClasses.iterator();

            while (iterator.hasNext()) {

                Class classVerification = iterator.next();

                Object swaggerRule = classVerification.newInstance();

                /*

                Method method = swaggerRule.getClass().getMethod("getName");

                String ruleName = (String) method.invoke(swaggerRule);

                if (ignoreRules == null || !ignoreRules.contains(ruleName)){
                    swaggerRules.add((SwaggerRule) swaggerRule);
                }

                */

                Method method = swaggerRule.getClass().getSuperclass().getDeclaredMethod("getName");

                String ruleName = (String) method.invoke(swaggerRule);

                if (ignoreRules == null || !ignoreRules.contains(ruleName)){
                    swaggerRules.add((SwaggerRule) swaggerRule);
                }

            }

        } catch (Exception ex){
            ex.printStackTrace();
        }

        return swaggerRules;

    }

}
