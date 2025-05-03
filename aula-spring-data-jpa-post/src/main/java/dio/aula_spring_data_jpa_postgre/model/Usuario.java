package dio.aula_spring_data_jpa_postgre.model;

import jakarta.persistence.*;

// Definindo a classe como uma entidade JPA que mapeia a tabela 'usuario' no banco de dados
@Entity
@Table(name = "usuario") // Mudando de "user" para "usuario", nome da tabela
public class Usuario {

    // Definindo o campo 'id' como a chave primária da tabela e gerando seu valor automaticamente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id") // Nome da coluna no banco de dados
    private Integer id;

    // Definindo o campo 'name' como uma coluna que não pode ser nula e com um limite de 50 caracteres
    @Column(length = 50, nullable = false)
    private String name;

    // Definindo o campo 'username' como uma coluna única e com limite de 20 caracteres
    @Column(length = 20, nullable = false, unique = true)
    private String username;

    // Definindo o campo 'password' como uma coluna que não pode ser nula e com limite de 100 caracteres
    @Column(length = 100, nullable = false)
    private String password;

    // Getters e Setters para os campos
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    // Método toString para exibir a representação textual do objeto Usuario
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
