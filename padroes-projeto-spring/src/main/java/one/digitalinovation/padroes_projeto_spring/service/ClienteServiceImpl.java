package one.digitalinovation.padroes_projeto_spring.service;

import one.digitalinovation.padroes_projeto_spring.model.Endereco;
import one.digitalinovation.padroes_projeto_spring.repository.ClienteRepository;
import one.digitalinovation.padroes_projeto_spring.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import one.digitalinovation.padroes_projeto_spring.model.Cliente;

import java.util.Optional;

@Service // Adicionando @Service para garantir que a classe seja registrada como um Bean
public class ClienteServiceImpl implements ClienteService {


    @Autowired // Injetando o repositório de cliente
    private ClienteRepository clienteRepository;

    @Autowired // Injetando o repositório de endereço
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ViaCepService viaCepService;

    // Implementação dos métodos do serviço
    @Override
    public Iterable<Cliente> buscarTodos() {
        // Lógica de busca de todos os clientes
        return clienteRepository.findAll(); // Usando o repositório para buscar todos os clientes
    }

    // Implementação dos métodos do serviço
    @Override
    public Cliente buscarPorId(Long id) {
        // Lógica de busca de cliente por ID
        Optional<Cliente> cliente = clienteRepository.findById(id);// Usando o repositório para buscar o cliente pelo ID
        return cliente.get(); // Retornando o cliente encontrado
    }

    // Implementação dos métodos do serviço
    @Override
    public void inserir(Cliente cliente) {
        enderecoRepository.findById(cliente.getEndereco().getCep()); // Usando o repositório para buscar o endereço pelo CEP
        salvarClienteComCep(cliente);
    }

    // Implementação dos métodos do serviço
    @Override
    public void atualizar(Long id, Cliente cliente) {
        // Lógica de atualização de cliente
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);// Usando o repositório para buscar o cliente pelo ID
        if (clienteExistente.isPresent()) {
            clienteExistente.get().setNome(cliente.getNome()); // Atualizando o nome do cliente existente com o nome do cliente fornecido
            clienteExistente.get().setId(cliente.getId()); // Atualizando o ID do cliente existente com o ID do cliente fornecido
            clienteRepository.save(clienteExistente.get()); // Salvando o cliente existente no banco de dados
        }
    }

    // Implementação dos métodos do serviço
    @Override
    public void deletar(Long id) {
        // Lógica de deleção de cliente
        clienteRepository.deleteById(id); // Usando o repositório para deletar o cliente pelo ID
    }



    private void salvarClienteComCep(Cliente cliente) {
        // Lógica de inserção de cliente
        String cep = cliente.getEndereco().getCep();// Obtendo o CEP do endereço do cliente
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> { // Usando o repositório para buscar o endereço pelo CEP
            Endereco novoEndereco = viaCepService.consultarCep(cep); // Usando o serviço ViaCepService para consultar o CEP
            if (novoEndereco != null) {
                enderecoRepository.save(novoEndereco); // Salvando o endereço no banco de dados
                return novoEndereco;
            }
            return null;
        });// Usando o repositório para buscar o endereço pelo CEP
        clienteRepository.save(cliente); // Salvando o cliente no banco de dados
    }


}