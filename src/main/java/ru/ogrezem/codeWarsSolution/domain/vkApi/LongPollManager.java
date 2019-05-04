package ru.ogrezem.codeWarsSolution.domain.vkApi;

import com.vk.api.sdk.actions.LongPoll;
import com.vk.api.sdk.callback.longpoll.responses.GetLongPollEventsResponse;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.exceptions.LongPollServerKeyExpiredException;
import com.vk.api.sdk.objects.groups.LongPollServer;
import com.vk.api.sdk.queries.groups.GroupsGetLongPollServerQuery;

class LongPollManager implements Runnable {

    private LongPoll longPoll;
    private String longPollKey;
    private String longPollServerURL;
    private Integer ts;
    private boolean isInitialised = false;
    private GroupsGetLongPollServerQuery longPollServerQuery;
    private VkBotEventHandler botEventsHandler;
    private boolean mustInterrupt = false;

    LongPollManager(LongPoll longPoll, GroupsGetLongPollServerQuery longPollServerQuery, VkApiAccessor vk) {
        this.longPoll = longPoll;
        this.longPollServerQuery = longPollServerQuery;
        try {
            LongPollServer longPollServer = loadLongPollServer();
            longPollKey = longPollServer.getKey();
            longPollServerURL = longPollServer.getServer();
            ts = longPollServer.getTs();
            isInitialised = true;
        } catch (ClientException | ApiException e) {
            e.printStackTrace();
        }
        botEventsHandler = new VkBotEventHandler(vk);
    }

    @Override
    public void run() {
        if (!isInitialised) {
            System.err.println("LongPoll initialisation error");
            return;
        }
        botOperationLoop: while (!mustInterrupt) {
            System.out.println("Слушаем");
            GetLongPollEventsResponse getEventsResponse;
            try {
                getEventsResponse = longPoll.getEvents(longPollServerURL, longPollKey, ts)
                        .waitTime(3600000 * 5) // 3600000 ms == 1 h
                        .execute();
            } catch (LongPollServerKeyExpiredException e1) {
                try {
                    updateLongPollData();
                } catch (ClientException | ApiException e12) {
                    e12.printStackTrace();
                }
                continue;
            } catch (ApiException | ClientException e2) {
                e2.printStackTrace();
                continue;
            }
            ts = getEventsResponse.getTs();
            VkBotEventHandler.Response eventHandlerResponse = botEventsHandler.handleEvent(getEventsResponse.getUpdates());
            switch (eventHandlerResponse.getMessage()) {
                case EVERYTHING_OK:
                    break;
                case DATA_UPDATE_REQUIRED:
                    try {
                        updateLongPollData();
                    } catch (ClientException | ApiException e) {
                        e.printStackTrace();
                    }
                    break;
                case SHUT_DOWN_BOT:
                    System.out.println("The bot was switched off");
                    break botOperationLoop;
                case FIRST_ERROR:
                    ts = eventHandlerResponse.getNewTs();
                    break;
            }
        }
    }

    private LongPollServer loadLongPollServer() throws ClientException, ApiException {
        return longPollServerQuery.execute();
    }

    private void updateLongPollData() throws ClientException, ApiException {
        LongPollServer longPollServer = loadLongPollServer();
        longPollKey = longPollServer.getKey();
        longPollServerURL = longPollServer.getServer();
        ts = longPollServer.getTs();
    }

    public void setMustInterrupt(boolean mustInterrupt) {
        this.mustInterrupt = mustInterrupt;
    }
}
