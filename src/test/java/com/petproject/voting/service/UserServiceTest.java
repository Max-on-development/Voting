package com.petproject.voting.service;

import com.petproject.voting.model.Role;
import com.petproject.voting.model.User;
import com.petproject.voting.util.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static com.petproject.voting.testdata.UserTestData.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserServiceTest extends AbstractServiceTest {

    @Autowired
    protected UserService service;

    @Test
    void create() throws Exception {
        User created = service.create(getNew());
        int newId = created.id();
        User newUser = getNew();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(created, newUser);
        USER_MATCHER.assertMatch(service.get(newId), newUser);
    }

    @Test
    void duplicateMailCreate() throws Exception {
        assertThrows(DataAccessException.class, () ->
                service.create(new User(null, "Duplicate", "user@yandex.ru", "newPass", Role.USER)));
    }

    @Test
    void delete() throws Exception {
        service.delete(USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(USER_ID));
    }

    @Test
    void deletedNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND));
    }

    @Test
    void get() throws Exception {
        User user = service.get(ADMIN_ID);
        USER_MATCHER.assertMatch(user, ADMIN);
    }

    @Test
    void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND));
    }

    @Test
    void getByEmail() throws Exception {
        User user = service.getByEmail("admin@gmail.com");
        USER_MATCHER.assertMatch(user, ADMIN);
    }

    @Test
    void update() throws Exception {
        User updated = getUpdated();
        service.update(updated);
        USER_MATCHER.assertMatch(service.get(USER_ID), getUpdated());
    }

    @Test
    void getAll() throws Exception {
        List<User> all = service.getAll();
        USER_MATCHER.assertMatch(all, ADMIN, USER);
    }

    @Test
    void createWithException() throws Exception {
        validateRootCause(() -> service.create(new User(null, "  ", "mail@yandex.ru", "password", Role.USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "  ", "password", Role.USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "mail@yandex.ru", "  ", Role.USER)), ConstraintViolationException.class);
    }
}
