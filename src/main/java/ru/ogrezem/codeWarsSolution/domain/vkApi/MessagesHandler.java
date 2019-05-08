package ru.ogrezem.codeWarsSolution.domain.vkApi;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ogrezem.codeWarsSolution.domain.jts.Company;
import ru.ogrezem.codeWarsSolution.domain.jts.Customer;
import ru.ogrezem.codeWarsSolution.domain.jts.CustomerRepository;

import java.util.List;
import java.util.Map;

import static ru.ogrezem.codeWarsSolution.domain.vkApi.VkApiAccessor.OWNER_ID;

@Service
public class MessagesHandler {

    @Autowired
    private VkApiAccessor vk;
    @Autowired
    CustomerRepository customerRepository;
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private boolean repeatingMode = false;


    HandlingResponse handleMessage(JsonObject update) {
        var lastResponse = new HandlingResponse(HandlingResponseMessage.EVERYTHING_OK);
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
                    return new HandlingResponse(HandlingResponseMessage.SHUT_DOWN_BOT);
                }
                break;
            case "команда1":
                var photoLinkToParse = "https://vk.com/urcutelittlesister?z=photo62580436_456248337%2Fphotos62580436";
                Map<String, String> uriParsingResultMap = VkApiUtils.parsePhotoLink(photoLinkToParse);
                String photoOwnerId = uriParsingResultMap.get("ownerId");
                String photoId = uriParsingResultMap.get("photoId");
                vk.sendMessage(peerId,
                        photoOwnerId,
                        photoId
                );
                System.out.println("Приготовься, мразь");
                break;
            case "команда2":
                break;
            default:
                if (messageText.matches("^//@\\w+.*")) {
                    if (messageText.matches("^//@add\\s.+\\s.+$")) {
                        String[] commandArgs = messageText.split("//@\\w+\\s")[1]
                                .split("\\s");
                        if (commandArgs.length < 3) {
                            vk.sendMessage(peerId, "Недостаточно данных для добавления");
                            break;
                        }
                        String newCustomerFirstName = commandArgs[0];
                        String newCustomerLastName = commandArgs[1];
                        String newCustomerCompanyName = commandArgs[3];
                        var newCustomerCompany = new Company(newCustomerCompanyName);
                        var newCustomer = new Customer(newCustomerFirstName, newCustomerLastName, newCustomerCompany);
                        customerRepository.insert(newCustomer);
                        Map<String, String> senderInfo = vk.getUserNameWithSexById(fromId);
                        var commandResponseBuilder = new StringBuilder();
                        commandResponseBuilder.append(senderInfo.get("firstName"))
                                .append(", ты успешно добавил")
                                .append(senderInfo.get("sex").equals("female") ? "a " : " ")
                                .append("в базу данных человека с именем ").append(newCustomerFirstName)
                                .append(" и фамилией ").append(newCustomerLastName)
                                .append(", работающего в компании ").append(newCustomerCompanyName);
                        vk.sendMessage(peerId, commandResponseBuilder.toString());
                    } else if (messageText.equals("//@show all")) {
                        if (customerRepository.count() == 0) {
                            vk.sendMessage(peerId, "Список пуст");
                            break;
                        }
                        List<Customer> allCustomersList = customerRepository.findAll();
                        var commandResponseBuilder = new StringBuilder();
                        commandResponseBuilder.append("Список всех customer:\n");
                        for (Customer customer : allCustomersList) {
                            commandResponseBuilder.append(gson.toJson(customer))
                                    .append("\n");
                        }
                        vk.sendMessage(peerId, commandResponseBuilder.toString());
                    } else if (messageText.matches("^//@delete\\s.+")) {
                        String customerToBeDeletedId = messageText.split("^//@delete\\s")[1];
                        if (customerRepository.findById(customerToBeDeletedId).isPresent()) {
                            customerRepository.deleteById(customerToBeDeletedId);
                            vk.sendMessage(peerId,
                                    "Customer с id " + customerToBeDeletedId + " успешно удалён");
                        } else
                            vk.sendMessage(peerId,
                                    "Customer с id " + customerToBeDeletedId + " отсутствует а базе");
                    }
                    break;
                }
                if (repeatingMode && fromId == OWNER_ID) {
                    vk.sendMessage(2000000001, messageText);
                }
                break;
        }
        return lastResponse;
    }
}
