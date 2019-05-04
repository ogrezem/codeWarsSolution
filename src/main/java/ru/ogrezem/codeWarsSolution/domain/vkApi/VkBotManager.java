package ru.ogrezem.codeWarsSolution.domain.vkApi;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;

public class VkBotManager {

    private VkApiClientConnector clientConnector;
    private VkApiAccessor vkApiAccessor;
    private LongPollManager longPollManager;
    private boolean isInitialised = false;
    private static VkBotManager instance;

    private VkBotManager() {
        clientConnector = new VkApiClientConnector();
        vkApiAccessor = new VkApiAccessor(clientConnector);
        try {
            longPollManager = new LongPollManager (
                    clientConnector.getLongPoll(),
                    clientConnector.longPollServerQuery(),
                    vkApiAccessor
            );
            isInitialised = true;
        } catch (ApiException | ClientException e) {
            e.printStackTrace();
        }
    }

    public static VkBotManager getInstance() {
        if (instance == null)
            instance = new VkBotManager();
        return instance;
    }

    public void runBot() {
        new Thread(longPollManager, "botOperationThread").start();
    }

    public boolean isInitialised() {
        return isInitialised;
    }
}
