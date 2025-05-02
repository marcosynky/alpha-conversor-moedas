package one.digitalinovation.padroes_projeto_spring.repository;

// Importando o modelo Cliente, que será usado no repositório.
import one.digitalinovation.padroes_projeto_spring.model.Cliente;

// Importando as interfaces necessárias para o repositório do Spring Data.
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// A anotação @Repository indica que esta interface é um repositório do Spring, responsável pela
// comunicação com o banco de dados e manipulação das entidades.
@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
    // Não é necessário definir nenhum método aqui, pois o CrudRepository já fornece métodos prontos
    // para operações básicas como salvar, excluir e buscar entidades (Clientes).
}
