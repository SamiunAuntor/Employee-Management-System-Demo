import java.util.HashMap;
import java.util.Map;

public class Connection {
    private Map<String, String> users = new HashMap<>();

    protected Connection() {
        users.put("Admin", "Admin123");
    }

    public boolean authenticate(String username, String password) {
        if (users.containsKey(username)) {
            return password.equals(users.get(username));
        }
        return false;
    }
}
