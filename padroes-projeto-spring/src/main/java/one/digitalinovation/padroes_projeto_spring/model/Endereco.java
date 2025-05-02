package one.digitalinovation.padroes_projeto_spring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

// A anotação @Entity indica que a classe Endereco será mapeada como uma entidade JPA,
// ou seja, ela será associada a uma tabela no banco de dados.
@Entity
public class Endereco {

    // A anotação @Id indica que o campo "cep" será a chave primária da tabela no banco de dados.
    @Id
    private String cep; // Código postal (CEP) é o identificador único para um endereço.

    private String logradouro; // Nome da rua ou via do endereço.
    private String complemento; // Informações adicionais, como número do apartamento ou bloco.
    private String bairro; // Nome do bairro onde o endereço está localizado.
    private String localidade; // Cidade ou município do endereço.
    private String uf; // Unidade federativa (Estado) do endereço, como "SP", "RJ", etc.
    private String ibge; // Código do município segundo o IBGE.
    private String gia; // Código da região do estado, usado em alguns casos.
    private String ddd; // Código de Discagem Direta à Distância (DDD) para chamadas telefônicas.
    private String siafi; // Código utilizado para identificar o município na Secretaria do Tesouro Nacional.

    // Métodos getter e setter para o campo "cep".
    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    // Métodos getter e setter para o campo "logradouro".
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    // Métodos getter e setter para o campo "complemento".
    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    // Métodos getter e setter para o campo "bairro".
    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    // Métodos getter e setter para o campo "localidade".
    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    // Métodos getter e setter para o campo "uf".
    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    // Métodos getter e setter para o campo "ibge".
    public String getIbge() {
        return ibge;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
    }

    // Métodos getter e setter para o campo "gia".
    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    // Métodos getter e setter para o campo "ddd".
    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    // Métodos getter e setter para o campo "siafi".
    public String getSiafi() {
        return siafi;
    }

    public void setSiafi(String siafi) {
        this.siafi = siafi;
    }

}
