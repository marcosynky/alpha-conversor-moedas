package one.digitalinovation.padroes_projeto_spring.service;

import one.digitalinovation.padroes_projeto_spring.model.Cliente;

// A interface ClienteService define um contrato para as classes que irão implementar os serviços relacionados ao cliente.
// Classes que implementam essa interface devem fornecer a implementação concreta dos métodos descritos aqui.
public interface ClienteService {

    // Método para buscar todos os clientes. Ele deve retornar uma coleção de clientes (Iterable).
    Iterable<Cliente> buscarTodos();

    // Método para buscar um cliente pelo ID.
    // Ele recebe o ID do cliente e retorna o objeto Cliente correspondente.
    Cliente buscarPorId(Long id);

    // Método para inserir um novo cliente.
    // Ele recebe um objeto Cliente e insere no banco de dados.
    void inserir(Cliente cliente);

    // Método para atualizar as informações de um cliente.
    // Recebe o ID do cliente a ser atualizado e o objeto Cliente com as novas informações.
    void atualizar(Long id, Cliente cliente);

    // Método para deletar um cliente.
    // Recebe o ID do cliente e realiza a exclusão.
    void deletar(Long id);
}
