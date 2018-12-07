package web;

import model.User;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static final int USER_ID1 = START_SEQ;
    public static final int USER_ID2 = START_SEQ + 1;
    public static final int USER_ID3 = START_SEQ + 2;
    public static final int DOCTOR_ID = START_SEQ + 3;
    public static final int ADMIN_ID = START_SEQ + 4;

    public static final User USER1 = new User(USER_ID1, "Patient1", "patient1", "patient1");
    public static final User USER2= new User(USER_ID2, "Patient2", "patient2", "patient2");
    public static final User USER3 = new User(USER_ID3, "Patient3", "patient3", "patient3");
    public static final User DOCTOR = new User(DOCTOR_ID, "Doctor", "doctor", "doctor");
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin", "admin");

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected);
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}

