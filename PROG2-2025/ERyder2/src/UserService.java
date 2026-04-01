import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class UserService {
    private final List<RegisteredUsers> registeredUsersList = new ArrayList<>();
    public void addUser(RegisteredUsers user) {
        registeredUsersList.add(user);
    }
    public List<RegisteredUsers> getAllUsers() {
        return registeredUsersList;
    }
    public boolean removeUserByEmail(String email) {
        Iterator<RegisteredUsers> iterator = registeredUsersList.iterator();
        while (iterator.hasNext()) {
            RegisteredUsers user = iterator.next();
            if (user.getEmailAddress().equals(email)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
    public RegisteredUsers findUserByEmail(String email) {
        for (RegisteredUsers user : registeredUsersList) {
            if (user.getEmailAddress().equals(email)) {
                return user;
            }
        }
        return null;
    }
}