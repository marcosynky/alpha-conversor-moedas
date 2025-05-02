package one.digitalinovation.padroes_projeto_spring.model;

import jakarta.persistence.*;

// A anotação @Entity indica que a classe é uma entidade JPA, ou seja, ela será mapeada para uma tabela no banco de dados.
@Entity
public class Cliente {

    // A anotação @Id marca o campo id como a chave primária da tabela no banco de dados.
    // A anotação @GeneratedValue define que o valor do id será gerado automaticamente pelo banco de dados.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // Identificador único do cliente, gerado automaticamente.

    private String nome; // Nome do cliente.

    // A anotação @ManyToOne indica que muitos objetos Cliente podem estar associados a um único Endereco.
    // Isso implica que cada cliente tem um endereço, mas o endereço pode ser compartilhado por vários clientes.
    @ManyToOne
    private Endereco endereco; // Relacionamento de muitos-para-um com a classe Endereco.

    // Métodos getter e setter para o campo id.
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Métodos getter e setter para o campo nome.
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Métodos getter e setter para o campo endereco.
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
