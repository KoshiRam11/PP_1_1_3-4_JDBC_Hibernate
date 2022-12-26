package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.util.List;

public interface UserDao {
    // создается таблица
    void createUsersTable();
    // удаляется таблица
    void dropUsersTable();
// создается новый пользователь
    void saveUser(String name, String lastName, byte age);
// удаляется новый пользователь по ID
    void removeUserById(long id);
    // получаем всех пользователей из таблицы
    List<User> getAllUsers();
// очищаем всю таблицу
    void cleanUsersTable();
}
