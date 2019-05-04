package ru.ogrezem.codeWarsSolution.domain.vkApi;

import com.google.gson.JsonObject;

import java.util.List;

import static ru.ogrezem.codeWarsSolution.domain.vkApi.VkApiAccessor.OWNER_ID;

class VkBotEventHandler {

    private VkApiAccessor vk;
    private boolean repeatingMode = false;

    VkBotEventHandler(VkApiAccessor vk) {
        this.vk = vk;
    }

    Response handleEvent(List<JsonObject> updates) {
        var lastResponse = new Response(ResponseMessage.UNKNOWN_ERROR);
        for (JsonObject update : updates) {
            if (update.has("failed")) {
                return handleError(update);
            } else if (update.has("type")) {
                String updateType = update.get("type").getAsString();
                System.out.println("Update type: " + updateType);///
                switch (updateType) {
                    case "message_new":
                        lastResponse = handleMessage(update);
                        break;
                }
            }
            System.out.println(update);
        }
        return lastResponse;
    }

    private Response handleMessage(JsonObject update) {
        var lastResponse = new Response(ResponseMessage.EVERYTHING_OK);
        var messageJsonObject = update.get("object").getAsJsonObject();
        var peerId = messageJsonObject.get("peer_id").getAsInt();
        var fromId = messageJsonObject.get("from_id").getAsInt();
        var messageText = messageJsonObject.get("text").getAsString();
        System.out.println("Message text: " + messageText);///
        switch (messageText) {
            case "привет, бот":
                int sendingResult = vk.sendMessage(peerId, "Привет, сладость :3");
                System.out.println("Sending result: " + sendingResult);
                break;
            case "бот, ты кто?":
                vk.sendMessage(peerId, "Я маленькая лолечка c;");
                break;
            case "бот, сгенерируй массив целых чисел":
                StringBuilder sb = new StringBuilder("[");
                for (int i = 0; i < 10; i++) {
                    sb.append((int) (Math.random() * 100));
                    if (i != 9) {
                        sb.append(", ");
                    }
                }
                sb.append("]");
                System.out.println("Код: " + vk.sendMessage(peerId, "Слушаюсь: " + sb));
                break;
            case "скажи Диане, что она прекрасна":
                vk.sendMessage(peerId, "Будет сделано!");
                vk.sendMessage(2000000001, "Диана, ты прекрасна :*");
                break;
            case "бот, слушайся":
                if (peerId == OWNER_ID && !repeatingMode) {
                    vk.sendMessage(peerId, "Да, повелитель");
                    repeatingMode = true;
                }
                break;
            case "бот, отставить":
                if (peerId == OWNER_ID && repeatingMode) {
                    vk.sendMessage(peerId, "Да, повелитель");
                    repeatingMode = false;
                }
                break;
            case "бот, отключись":
                if (peerId == OWNER_ID) {
                    vk.sendMessage(peerId, "До встречи!");
                    return new Response(ResponseMessage.SHUT_DOWN_BOT);
                }
                break;
            case "команда1":
//                var photoLinkToParse = "https://vk.com/urcutelittlesister?z=photo62580436_456248337%2Fphotos62580436";
//                Map<String, String> uriParsingResultMap = VkApiUtils.parsePhotoLink(photoLinkToParse);
//                String photoOwnerId = uriParsingResultMap.get("ownerId");
//                String photoId = uriParsingResultMap.get("photoId");
//                vk.sendMessage(OWNER_ID, "");
                vk.sendMessage(peerId,
                        "первое сообщение",
                        "второе сообщение",
                        "третье сообщение"
                );
                System.out.println("Приготовься, мразь");
                break;
            case "команда2":
                break;
            default:
                if (repeatingMode && fromId == OWNER_ID) {
                    vk.sendMessage(2000000001, messageText);
                }
                break;
        }
        return lastResponse;
    }

    private Response handleError(JsonObject update) {
        switch (update.get("failed").getAsInt()) {
            case 1:
                return new Response(ResponseMessage.FIRST_ERROR, update.get("ts").getAsInt());
            case 2:
            case 3:
                return new Response(ResponseMessage.DATA_UPDATE_REQUIRED);
        }
        return new Response(ResponseMessage.UNKNOWN_ERROR);
    }

    class Response {

        private ResponseMessage message;
        private Integer newTs;

        Response(ResponseMessage message) {
            this.message = message;
        }

        Response(ResponseMessage message, Integer newTs) {
            this(message);
            this.newTs = newTs;
        }

        ResponseMessage getMessage() {
            return message;
        }

        Integer getNewTs() {
            return newTs;
        }
    }

    enum ResponseMessage {
        FIRST_ERROR,
        DATA_UPDATE_REQUIRED,
        EVERYTHING_OK,
        UNKNOWN_ERROR,
        SHUT_DOWN_BOT
    }
}
