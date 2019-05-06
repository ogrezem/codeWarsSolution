package ru.ogrezem.codeWarsSolution.domain.vkApi;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class VkBotEventHandler {

    @Autowired
    MessagesHandler messagesHandler;

    HandlingResponse handleEvent(List<JsonObject> updates) {
        var lastResponse = new HandlingResponse(HandlingResponseMessage.UNKNOWN_ERROR);
        for (JsonObject update : updates) {
            if (update.has("failed")) {
                return handleError(update);
            } else if (update.has("type")) {
                String updateType = update.get("type").getAsString();
                System.out.println("Update type: " + updateType);///
                switch (updateType) {
                    case "message_new":
                        lastResponse = messagesHandler.handleMessage(update);
                        break;
                }
            }
            System.out.println(update);
        }
        return lastResponse;
    }

    private HandlingResponse handleError(JsonObject update) {
        switch (update.get("failed").getAsInt()) {
            case 1:
                return new HandlingResponse(HandlingResponseMessage.FIRST_ERROR, update.get("ts").getAsInt());
            case 2:
            case 3:
                return new HandlingResponse(HandlingResponseMessage.DATA_UPDATE_REQUIRED);
        }
        return new HandlingResponse(HandlingResponseMessage.UNKNOWN_ERROR);
    }
}
