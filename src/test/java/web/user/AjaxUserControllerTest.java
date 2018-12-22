package web.user;

import model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import service.user.UserService;

import java.util.Collections;
import java.util.Date;

import static web.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})

@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class AjaxUserControllerTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private UserService service;

    @Test
    public void getAll() {
    }

    @Test
    public void get() {
    }

    @Test
    public void getByRole() {
    }

    @Test
    public void create() throws Exception{
        User newUser = new User(null, "New", "new@gmail.com", "admin");
        User created = service.create(newUser);
        newUser.setId(created.getId());
        assertMatch(service.getAll(), ADMIN, DOCTOR, newUser, USER1, USER2, USER3);
    }

    @Test
    public void create1() {
    }

    @Test
    public void update() {
    }
}