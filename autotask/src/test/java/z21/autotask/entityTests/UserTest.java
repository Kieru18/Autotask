package z21.autotask.entityTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import z21.autotask.entities.Employee;
import z21.autotask.entities.User;

public class UserTest {
    @Test
    void testUserConstructor() {
        User user = new User(1, "login", "password", "user", "email", new Employee());

        assertEquals(1, user.getUserId());
        assertEquals("login", user.getLogin());
        assertEquals("password", user.getPassword());
        assertEquals("user", user.getRole());
        assertEquals("email", user.getMail());
        assertNotNull(user.getEmployee());
    }

    @Test
    void testUserConstructorEmptyValues() {
        User user = new User(0, "", "", "", "", null);

        assertEquals(0, user.getUserId());
        assertEquals("", user.getLogin());
        assertEquals("", user.getPassword());
        assertEquals("", user.getRole());
        assertEquals("", user.getMail());
        assertNull(user.getEmployee());
    }

    @Test
    void testUserNoArgConstructor() {
        User user = new User();

        assertNull(user.getUserId());
        assertNull(user.getLogin());
        assertNull(user.getPassword());
        assertNull(user.getRole());
        assertNull(user.getMail());
        assertNull(user.getEmployee());
    }

    @Test
    void testUserSetters() {
        User user = new User();

        user.setUserId(1);
        user.setLogin("login");
        user.setPassword("password");
        user.setRole("user");
        user.setMail("email");
        user.setEmployee(new Employee());

        assertEquals(1, user.getUserId());
        assertEquals("login", user.getLogin());
        assertEquals("password", user.getPassword());
        assertEquals("user", user.getRole());
        assertEquals("email", user.getMail());
        assertNotNull(user.getEmployee());
    }

    @Test
    void testUserGetters() {
        User user = new User();

        user.setUserId(1);
        user.setLogin("login");
        user.setPassword("password");
        user.setRole("user");
        user.setMail("email");
        user.setEmployee(new Employee());

        assertEquals(1, user.getUserId());
        assertEquals("login", user.getLogin());
        assertEquals("password", user.getPassword());
        assertEquals("user", user.getRole());
        assertEquals("email", user.getMail());
        assertNotNull(user.getEmployee());
    }

    @Test
    void testUserEqual() {
        User user1 = new User(1, "login", "password", "user", "email", new Employee());
        User user2 = new User(1, "login", "password", "user", "email", new Employee());
        User user3 = new User(2, "logine", "haslo", "admine", "emails", new Employee());

        assertEquals(user1, user2);
        assertNotEquals(user1, user3);
    }

    @Test
    void testUserHashCode() {
        User user1 = new User(1, "login", "password", "user", "email", new Employee());
        User user2 = new User(1, "login", "password", "user", "email", new Employee());
        User user3 = new User(2, "logine", "haslo", "admine", "emails", new Employee());

        assertEquals(user1.hashCode(), user2.hashCode());
        assertNotEquals(user1.hashCode(), user3.hashCode());
    }

}
