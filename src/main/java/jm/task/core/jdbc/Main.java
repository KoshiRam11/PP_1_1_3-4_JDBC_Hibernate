package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    private static final UserService user = new UserServiceImpl();

    public static void main(String[] args) throws SQLException {
        Util util = new Util();
        util.getSessionFactory();
        user.createUsersTable();
        user.saveUser("Mike", "Tyson", (byte) 56);
        user.saveUser("Volodya", "Putin", (byte) 56);
        user.saveUser("Dasha", "Koshkina", (byte) 21);
        user.saveUser("Rama", "Safiullin", (byte) 22);
        user.removeUserById(1);
        for (User allUser : user.getAllUsers()) {
            System.out.println(allUser);
        }
        user.cleanUsersTable();
        user.dropUsersTable();
    }
}