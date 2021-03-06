package com.api.factory;

public enum EnumRule {

    RULE0001("O documento swagger %s não está configurado para suportar http e https",
            "Todas as APIs devem estar configuradas para suportar tanto HTTP quanto HTTPS",
            SwaggerRuleType.ERROR),

    RULE0002("O path %s não mapeia os códigos de retorno 400 ou 500.",
            "Todas as APIs devem mapear os códigos de resposta 400 e 500.",
            SwaggerRuleType.ERROR),

    RULE0003("O path da api %s não deve conter verbos equivalentes aos métodos do protocolo http. Ex.: get, post, update, delete, create",
            "Nenhuma das operações pode conter verbos equivalentes aos métodos do protocolo http.",
            SwaggerRuleType.ERROR),

    RULE0004("O path da api %s possui um placeholder que identifica uma entidade e portanto deve mapear o código de erro 404.",
            "Paths que identificam uma entidade pelo id/code devem retornar 404.",
            SwaggerRuleType.ERROR),

    RULE0005("O path %s usa o método POST e portanto, quando for status 201, deve retornar a entidade que está sendo atualizada",
            "Os métodos do tipo POST devem retornar as entidades que estão sendo atualizadas quando for status 201.",
            SwaggerRuleType.ERROR),

    RULE0006("A entidade de retorno %s não deve conter os sufixos: DTO, VO.",
            "As entidades de retorno não podem conter os sufixos DTO ou VO.",
            SwaggerRuleType.ERROR),

    RULE0007("O path %s retorna um valor primitivo ao invés de uma JSON válido.",
            "As entidades de retorno das APIs deve ser um JSON válido e não um valor primitivo.",
            SwaggerRuleType.ERROR),

    RULE0008("O path %s usa o método %s e, portanto, não deve ser utilizado corpo na mensagem.",
            "Métodos DELETE, GET, HEAD e OPTIONS não deve ser utilizado corpo na mensagem e sim utilizar query string.",
            SwaggerRuleType.ERROR),

    RULE0009("O path %s não contém uma descrição.",
            "Todas as operações devem ter uma descrição.",
            SwaggerRuleType.ERROR),

    RULE0010("O path %s não define um operationID",
            "Todas as operações devem definir um operationID.",
            SwaggerRuleType.ERROR),

    RULE0011("O path %s com o operationID %s não é único.",
            "Cada operação deve ter um operationID único.",
            SwaggerRuleType.ERROR),

    RULE0012("O path %s retorna uma collection, mas não permite a operação de ordenação.",
            "APIs que retornam collections devem permitir ordenação.",
            SwaggerRuleType.WARNING),

    RULE0013("O path %s retorna uma collection, mas não permite a operação de filtro.",
            "APIs que retornam collections devem permitir filtros.",
            SwaggerRuleType.WARNING),

    RULE0014("O path %s retorna uma collection, mas não permite a operação de paginação.",
            "APIs que retornam collections devem permitir paginação.",
            SwaggerRuleType.WARNING),

    RULE0015("O path %s possui uma tag que possui Service no nome.",
            "Tags não devem conter Service no nome.",
            SwaggerRuleType.WARNING),

    RULE0016("O path %s possui query params que não obedecem a prática camelCase.",
            "Query params devem estar no padrão camelCase.",
            SwaggerRuleType.ERROR),

    RULE0017("O path %s retorna uma collection, mas seu nome está no singular.",
            "APIs que retornam collections devem estar no plural.",
            SwaggerRuleType.WARNING),

    RULE0018("O path %s não possui tags.",
            "Cada operação deve ter pelo menos uma tag.",
            SwaggerRuleType.ERROR),

    RULE0019("O path %s não possui um formato de retorno adequado.",
            "Todas as operações devem conter um formato de retorno adequado.",
            SwaggerRuleType.ERROR),

    RULE0020("O path %s não retorna o tipo ErrorResponse para código de erro %s.",
            "Todos os código de erros devem utilizar o tipo ErrorResponse.",
            SwaggerRuleType.ERROR)

    ;

    private final String mMessage;

    private final String mDescription;

    private final SwaggerRuleType mSwaggerRuleType;

    EnumRule(String message, String description, SwaggerRuleType swaggerRuleType){
        mMessage = message;
        mDescription = description;
        mSwaggerRuleType = swaggerRuleType;
    }

    public String getMessage() {
        return mMessage;
    }

    public String getDescription() {
        return mDescription;
    }

    public SwaggerRuleType getSwaggerRuleType() {
        return mSwaggerRuleType;
    }

}
