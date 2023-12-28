package com.example.exceptions;

public class UserStore {
    private boolean isStoreInvalidUsers = false;

    public User findUser(User[] users, String login) {
        User find = null;
        for (User user : users) {
            if (user.getUsername().equals(login)) {
                find = user;
                break;
            }
        }
        if (find == null) {
            throw new IllegalArgumentException("Пользователь не найден");
        }
        return find;
    }

    public boolean validate(User user) {
        if ((!user.isValid() || user.getUsername().length() < 3) && !isStoreInvalidUsers) {
            throw new IllegalStateException("Пользователь не валидный");
        }
        return true;
    }

    public static void main(String[] args) {
        User[] users = {
                new User("Ivan", true),
                new User("Fedor", false)
        };
        UserStore userStore = new UserStore();
        try {
            User user = userStore.findUser(users, "Fedor");
            if (userStore.validate(user)) {
                System.out.println("Пользователь имеет доступ");
            }
        } catch (IllegalArgumentException illegalArgumentException) {
            illegalArgumentException.printStackTrace();
        } catch (IllegalStateException illegalStateException) {
            illegalStateException.printStackTrace();
        } finally {
            System.out.println("Список пользователей:");
            for (User user: users) {
                System.out.println(user.toString());
            }
        }
    }
}
