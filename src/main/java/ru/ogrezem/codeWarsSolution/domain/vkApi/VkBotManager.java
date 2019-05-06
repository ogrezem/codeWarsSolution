package ru.ogrezem.codeWarsSolution.domain.vkApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VkBotManager {

    @Autowired
    private LongPollManager longPollManager;

    public void runBot() {
        new Thread(longPollManager, "botOperationThread").start();
    }
}
