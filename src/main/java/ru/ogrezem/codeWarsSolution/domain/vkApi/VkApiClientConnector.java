package ru.ogrezem.codeWarsSolution.domain.vkApi;

import com.vk.api.sdk.actions.LongPoll;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.queries.groups.GroupsGetLongPollServerQuery;

class VkApiClientConnector {

    private VkApiClient client;
    private static final int DIANA_ID = 62580436;
    private static final int GROUP_ID = 169388302;
    private static final String GROUP_ACCESS_TOKEN
            = "82de6328ef42022f6293059dd73eee32669b4dabbcb41c9823b89d529afe2e4db80ec2e16e0019d7f2aa7";
    private GroupActor groupActor = new GroupActor(GROUP_ID, GROUP_ACCESS_TOKEN);

    VkApiClientConnector() {
        var transportClient = new HttpTransportClient();
        client = new VkApiClient(transportClient);
    }

    LongPoll getLongPoll() {
        return client.longPoll();
    }

    GroupsGetLongPollServerQuery longPollServerQuery() throws ApiException, ClientException {
        return client.groups().getLongPollServer(groupActor, GROUP_ID);
    }

    public VkApiClient getClient() {
        return client;
    }

    public static int getGroupId() {
        return GROUP_ID;
    }

    public GroupActor getGroupActor() {
        return groupActor;
    }
}
