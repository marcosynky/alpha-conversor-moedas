package aula.spring_jwt_security.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tab_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    @NotNull(message = "Name cannot be null")  // Validação para garantir que 'name' não seja null
    @Size(min = 1, message = "Name cannot be empty") // Validação para garantir que 'name' não seja vazio
    private String name;

    @Column(length = 20, nullable = false)
    @NotNull(message = "Username cannot be null")
    private String username;

    @Column(length = 100, nullable = false)
    @NotNull(message = "Password cannot be null")
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tab_user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role_id")
    private List<String> roles = new ArrayList<>();

    public User(String username, String encodedPassword, String role) {
        this.username = username;
        this.password = encodedPassword;
        this.roles.add(role);
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    // Getters and setters...
}
