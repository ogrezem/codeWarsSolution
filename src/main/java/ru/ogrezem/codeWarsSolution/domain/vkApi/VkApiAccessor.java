package ru.ogrezem.codeWarsSolution.domain.vkApi;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;

class VkApiAccessor {

    private VkApiClientConnector clientConnector;
    private VkApiClient client;
    private GroupActor groupActor;
    static final int OWNER_ID = 125167208;
    private static final int APP_ID = 6607327;
    private static final String SECRET_APP_KEY = "DGYqlvTFMWgUyDAULtaL";
//https://oauth.vk.com/authorize?client_id=6607327&display=page&redirect_uri=http://vk.com/&scope=140492255&response_type=code&v=5.95
//https://oauth.vk.com/access_token?client_id=6607327&client_secret=DGYqlvTFMWgUyDAULtaL&redirect_uri=http://vk.com/&code=d4c4487e029c9c9633
    private static final int FULL_ACCESS_SCOPE = 140492255;
    private static final String USER_ACCESS_TOKEN = "70d51281d6ef50936eaf282211382991b0473e2bb4a4b4e1f4024dbdd1402627240ce50719fa316230718";
    private UserActor userActor = new UserActor(OWNER_ID, USER_ACCESS_TOKEN);

    VkApiAccessor(VkApiClientConnector clientConnector) {
        this.clientConnector = clientConnector;
        client = clientConnector.getClient();
        groupActor = clientConnector.getGroupActor();
    }

    int sendMessage(Integer peerId, String message) {
        try {
            if (message == null) {
                return 0;
            }
            return client.messages()
                    .send(groupActor)
                    .peerId(peerId)
                    .randomId((int) System.currentTimeMillis())
                    .message(message)
                    .dontParseLinks(true)
                    .execute();
        } catch (ApiException | ClientException e) {
            e.printStackTrace();
        }
        return 0;
    }

    int sendMessage(Integer peerId, String... messages) {
        for (String message : messages) {
            sendMessage(peerId, message);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
        return 0;
    }

//    int testFunc() {
//        client.likes().isLiked()
//    }
}
