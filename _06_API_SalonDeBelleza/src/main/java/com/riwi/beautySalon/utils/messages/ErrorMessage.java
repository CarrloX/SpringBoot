package com.riwi.beautySalon.utils.messages;

public class ErrorMessage {

    public static String idNotFound(String entity) {
        // return "no hay resgistros en la entidad"+entity+"con el id suministrado";

        final String message = "no hay resgistros en la entidad %s con el id suministrado";

        return String.format(message, entity);
    }
}
