package br.com.bank.persistence.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

import java.util.List;

public class UserDto {

    @NotBlank(message = "Este campo não pode estar vazio")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    String nome;

    @NotBlank
    @Min(value = 0, message = "A idade deve ser maior ou igual a 0")
    @Max(value = 120, message = "A idade deve ser menor ou igual a 120")
    Integer idade;

    @NotBlank(message = "Este campo não pode estar vazio")
    String telefone;

    @NotBlank(message = "Este campo não pode estar vazio")
    String endereco;

    List<AccountDto> contas;

    public UserDto() {
    }

    public UserDto(String nome,
                   Integer idade,
                   String telefone,
                   String endereco,
                   List<AccountDto> contas) {
        this.nome = nome;
        this.idade = idade;
        this.telefone = telefone;
        this.endereco = endereco;
        this.contas = contas;
    }

    public List<AccountDto> getContas() {
        return contas;
    }

    public void setContas(List<AccountDto> contas) {
        this.contas = contas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
