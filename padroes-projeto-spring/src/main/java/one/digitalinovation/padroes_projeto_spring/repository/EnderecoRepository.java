package one.digitalinovation.padroes_projeto_spring.repository;

// Importando o modelo Endereco, que será utilizado no repositório.
import one.digitalinovation.padroes_projeto_spring.model.Endereco;

// Importando as interfaces necessárias para o repositório do Spring Data.
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// A anotação @Repository indica que esta interface é um repositório do Spring, ou seja, será responsável pela
// comunicação com o banco de dados e pela manipulação das entidades.
@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, String> {
    // Assim como o repositório de Cliente, este repositório utiliza a interface CrudRepository que fornece
    // implementações automáticas para operações básicas (salvar, buscar, atualizar, deletar) para a entidade Endereco.
}
