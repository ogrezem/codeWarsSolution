package ru.ogrezem.codeWarsSolution.domain.vkApi;

import com.google.gson.JsonObject;
import com.vk.api.sdk.actions.LongPoll;
import com.vk.api.sdk.callback.longpoll.responses.GetLongPollEventsResponse;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.groups.LongPollServer;

import java.util.List;

public class VKConnection {

    private static final int DIANA_ID = 62580436;
    private static final int GROUP_ID = 169388302;
    private static final String GROUP_ACCESS_KEY
            = "82de6328ef42022f6293059dd73eee32669b4dabbcb41c9823b89d529afe2e4db80ec2e16e0019d7f2aa7";
    private GroupActor groupActor = new GroupActor(GROUP_ID, GROUP_ACCESS_KEY);

    private VkApiClient client;
//    private Gson gson = new Gson();
    private LongPoll longPoll;
    private String longPollKey;
    private String longPollServerURL;
    private Integer ts;

    public VKConnection() {

        var transportClient = new HttpTransportClient();
        client = new VkApiClient(transportClient);
        longPoll = client.longPoll();
        try {
            LongPollServer longPollServer = loadLongPollServer();
            longPollKey = longPollServer.getKey();
            longPollServerURL = longPollServer.getServer();
            ts = longPollServer.getTs();
            System.out.println("Начинается прослушивание");
        } catch (ClientException | ApiException e) {
            e.printStackTrace();
        }
        boolean writingResume = false;//
        while (true) {
            System.out.println("Слушаем");
            GetLongPollEventsResponse response;
            try {
                response = longPoll.getEvents(longPollServerURL, longPollKey, ts)
                        .waitTime(3600000 * 5) // 3600000 ms = 1 h
                        .execute();
            } catch (ApiException | ClientException e) {
                e.printStackTrace();
                continue;
            }
            ts = response.getTs();
            List<JsonObject> updates = response.getUpdates();
            for (JsonObject update : updates) {
                if (update.has("failed")) {
                    switch (update.get("failed").getAsInt()) {
                        case 1:
                            ts = update.get("ts").getAsInt();
                            break;
                        case 2:
                        case 3:
                            try {
                                updateLongPollData();
                            } catch (ClientException | ApiException e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                } else if (update.has("type")) {
                    String updateType = update.get("type").getAsString();
                    System.out.println("Update type: " + updateType);///
                    switch (updateType) {
                        case "message_new":
                            JsonObject messageJsonObject = update.get("object").getAsJsonObject();
                            Integer peerId = messageJsonObject.get("peer_id").getAsInt();
                            Integer fromId = messageJsonObject.get("from_id").getAsInt();
                            String messageText = messageJsonObject.get("text").getAsString();
                            System.out.println("Message text: " + messageText);///
                            switch (messageText) {
                                case "привет, бот":
                                    int sendingResult = sendMessage(peerId, "Привет, сладость :3");
                                    System.out.println("Sending result: " + sendingResult);
                                    break;
                                case "бот, ты кто?":
                                    sendMessage(peerId, "Я маленькая лолечка c;");
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
                                    System.out.println("Код: " + sendMessage(peerId, "Слушаюсь: " + sb));
                                    break;
                                case "скажи Диане, что она прекрасна":
                                    sendMessage(peerId, "Будет сделано!");
                                    sendMessage(2000000001, "Диана, ты прекрасна :*");
                                    break;
                                case "бот, слушайся":
                                    if (peerId == 125167208 && !writingResume) {
                                        sendMessage(peerId, "Да, повелитель");
                                        writingResume = true;
                                    }
                                    break;
                                case "бот, отставить":
                                    if (peerId == 125167208 && writingResume) {
                                        sendMessage(peerId, "Да, повелитель");
                                        writingResume = false;
                                    }
                                    break;
                                default:
                                    if (writingResume && fromId == 125167208) {
                                        sendMessage(2000000001, messageText);
                                    }
                                    break;
                            }
                    }
                }

                System.out.println(update);
            }
        }
    }

    private int sendMessage(Integer peerId, String message) {
        try {
            if (message == null) {
                return 0;
            }
            return client.messages()
                    .send(groupActor)
                    .peerId(peerId)
                    .randomId((int) System.currentTimeMillis())
                    .message(message)
                    .confirm(true)
                    .execute();
        } catch (ApiException | ClientException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private LongPollServer loadLongPollServer() throws ApiException, ClientException {
        return client.groups().getLongPollServer(groupActor, GROUP_ID).execute();
    }

    private void updateLongPollData() throws ClientException, ApiException {
        LongPollServer longPollServer = loadLongPollServer();
        longPollKey = longPollServer.getKey();
        longPollServerURL = longPollServer.getServer();
        ts = longPollServer.getTs();
    }
}
