package model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NamedQueries({
        @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id"),
        @NamedQuery(name = User.GET_ALL, query = "SELECT u FROM User u ORDER BY u.login"),
        @NamedQuery(name = User.GET_BY_ROLE, query = "SELECT u FROM User u WHERE u.role=:role ORDER BY u.login"),
        @NamedQuery(name = User.GET_BY_LOGIN, query = "SELECT DISTINCT u FROM User u WHERE u.login=?1")
})

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "login", name = "users_unique_login_idx")})
public class User extends AbstractBaseEntity{

    public static final String DELETE = "User.delete";
    public static final String GET_ALL = "User.getAll";
    public static final String GET_BY_ROLE = "User.getByRole";
    public static final String GET_BY_LOGIN = "User.getByLogin";

    @Column(name = "login", nullable = false, unique = true)
    @NotBlank
    @Size(max = 100)
    private String login;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 1, max = 100)
    private String password;

    @Column(name = "role", nullable = false)
    @NotBlank
    private String role;

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public User(@NotBlank @Size(max = 100) String login, @NotBlank @Size(min = 5, max = 100) String password, @NotBlank String role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }


    public User(Integer id, @NotBlank @Size(max = 100) String login, @NotBlank @Size(min = 5, max = 100) String password, @NotBlank String role) {
        super(id);
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", id=" + id +
                '}';
    }
}
