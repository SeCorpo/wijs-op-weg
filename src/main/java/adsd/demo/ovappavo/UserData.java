package adsd.demo.ovappavo;

import java.util.HashMap;
import java.util.Map;

public class UserData {

    private Map<String, User> userMap = new HashMap<>();
    {
        User user = new User("user", "password123");
        userMap.put(user.getUsername(), user);

        user = new User("joey", "joey1234");
        userMap.put(user.getUsername(), user);

        user = new User("bobopolo", "bobopolo");
        userMap.put(user.getUsername(), user);
    }

    public Map<String, User> getUserMap() {
        return userMap;
    }
}
