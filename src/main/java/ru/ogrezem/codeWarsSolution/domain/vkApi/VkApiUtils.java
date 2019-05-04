package ru.ogrezem.codeWarsSolution.domain.vkApi;

import java.util.HashMap;
import java.util.Map;

public class VkApiUtils {

    // https://vk.com/urcutelittlesister?z=photo62580436_456248337%2Fphotos62580436
    static Map<String, String> parsePhotoLink(String linkUri) {
        HashMap<String, String> parsingResultMap = new HashMap<>(2);
        String[] parsingResultArray;
        try {
            parsingResultArray = linkUri.split("https://vk.com/[a-zA-Z0-9]+\\?z=photo")[1]
                    .split("%2Fphotos[0-9]+")[0]
                    .split("_");
            parsingResultMap.put("ownerId", parsingResultArray[0]);
            parsingResultMap.put("photoId", parsingResultArray[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Ошибка в массиве Vk.UriParser.parsePhotoLink()");
        }
        return parsingResultMap;
    }

    static boolean isEmail(String email) {
        var pattern1 = "([a-z0-9]+(-[a-z0-9])*)+";
        return email.matches("^"+pattern1+"(\\."+pattern1+")*@[a-z0-9]+(\\.[a-z0-9]+)*\\.[a-z]{2,}$");
    }
}
