package br.com.rabbithole.permissions.enums;

public enum Warns {
    INSERT_CACHE_ERROR("<red>[Permissions] Erro ao adicionar jogador no Cache!"),
    REMOVE_CACHE_ERROR("<red>[Permissions] Erro ao remoer jogador do Cache!"),
    REGISTER_PLAYER_ERROR("<red>[Permissions] Erro ao registrar jogador no Banco de Dados!"),
    UPDATING_PLAYER_ERROR("<red>[Permissions] Erro ao atualizar registro de jogador no Banco de Dados!");

    public final String message;

    Warns(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
