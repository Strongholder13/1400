package jm.task.core.jdbc;
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {



        UserService userService2 = new UserServiceImpl();
        userService2.dropUsersTable();
        userService2.createUsersTable();
        userService2.saveUser("Name101", "LastName01", (byte) 20);
        userService2.saveUser("Name22", "LastName02", (byte) 25);
        userService2.saveUser("Name33", "LastName03", (byte) 31);
        userService2.saveUser("Name44", "LastName04",  (byte)38);
        userService2.removeUserById(4);
        userService2.getAllUsers();
        userService2.cleanUsersTable();
        userService2.dropUsersTable();

    }
}
