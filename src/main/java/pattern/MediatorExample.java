package pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pom on 05.12.2016.
 */
public class MediatorExample {
    static public void main(String[] args) {
        Chat chat = new TextChat();

        User admin = new Admin(chat);
        User user1 = new SimpleUser(chat);
        User user2 = new SimpleUser(chat);

        ((TextChat) chat).addUser(user1);
        ((TextChat) chat).addUser(user2);
        ((TextChat) chat).setAdmin(admin);

        user1.sendMessage("Hello! I'm user1");
        admin.sendMessage("Hello! I'm admin");


    }
}

interface User {
    void sendMessage(String message);
    void getMessage(String message);
}

class SimpleUser implements User {
    private Chat chat;

    public SimpleUser(Chat chat) {
        this.chat = chat;
    }

    @Override
    public void sendMessage(String message) {
        chat.sendMessage(message, this);
    }

    @Override
    public void getMessage(String message) {
        System.out.println(this+"             SimpleUser became message: "+ message);
    }
}

class Admin implements User {
    private Chat chat;

    public Admin(Chat chat) {
        this.chat = chat;
    }

    @Override
    public void sendMessage(String message) {
        chat.sendMessage(message, this);
    }

    @Override
    public void getMessage(String message) {
        System.out.println("Admin became message: "+ message);
    }
}

interface Chat {
    void sendMessage(String message, User user);
}

class TextChat implements Chat {
    private User admin;
    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public void setAdmin (User admin) {
        this.admin = admin;
    }

    @Override
    public void sendMessage(String message, User user) {
        for (User u : users) {
            u.getMessage(message);
        }
        admin.getMessage(message);
    }

}




