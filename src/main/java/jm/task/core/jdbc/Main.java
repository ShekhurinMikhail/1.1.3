package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        List<User> userList = new ArrayList<>();
        userList.add(new User("Misha", "Shekhurin", (byte) 25));
        userList.add(new User("Sasha", "Ivanov", (byte) 26));
        userList.add(new User("Petya", "Sidorov", (byte) 27));
        userList.add(new User("Vasya", "Pupkin", (byte) 28));
        for (User user : userList) {
            userService.saveUser(user.getName(), user.getLastName(), user.getAge());
            System.out.println("User с именем " + user.getName() + " добавлен в базу данных");
        }
        List<User> allUsers = userService.getAllUsers();
        allUsers.forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
