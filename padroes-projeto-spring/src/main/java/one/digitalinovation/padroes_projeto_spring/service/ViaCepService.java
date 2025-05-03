package one.digitalinovation.padroes_projeto_spring.service;



import one.digitalinovation.padroes_projeto_spring.model.Endereco; // Importando a classe Endereco, que será usada para mapear a resposta da API.
import org.springframework.cloud.openfeign.FeignClient; // Importando a anotação FeignClient para criar clientes HTTP.
import org.springframework.web.bind.annotation.GetMapping; // Importando a anotação GetMapping para fazer requisições GET.
import org.springframework.web.bind.annotation.PathVariable; // Importando a anotação PathVariable para passar o parâmetro CEP na URL.

// A anotação @FeignClient é usada para declarar que esta interface é um cliente Feign para consumir um serviço REST.
// O nome "viacep" é o nome dado a este cliente, e a URL base é o endereço do serviço de consulta de CEP.
@FeignClient(name = "viacep", url = "https://viacep.com.br/")
public interface ViaCepService {

    // O método consultarCep é um mapeamento de uma requisição GET para a URL com o CEP especificado.
    // Ele recebe um parâmetro "cep" que é passado como parte da URL e retorna um objeto Endereco.
    @GetMapping("/{cep}/json/") // O parâmetro "cep" será substituído pelo valor fornecido.
    Endereco consultarCep(@PathVariable("cep") String cep); // O valor do "cep" é injetado na URL através de @PathVariable.
}
