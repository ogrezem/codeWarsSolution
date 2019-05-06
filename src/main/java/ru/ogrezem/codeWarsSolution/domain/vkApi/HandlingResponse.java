package ru.ogrezem.codeWarsSolution.domain.vkApi;

class HandlingResponse {

    private HandlingResponseMessage message;
    private Integer newTs;

    HandlingResponse(HandlingResponseMessage message) {
        this.message = message;
    }

    HandlingResponse(HandlingResponseMessage message, Integer newTs) {
        this(message);
        this.newTs = newTs;
    }

    HandlingResponseMessage getMessage() {
        return message;
    }

    Integer getNewTs() {
        return newTs;
    }
}
