package br.com.bank.persistence.dto;

import br.com.bank.persistence.model.AccountType;
import jakarta.validation.constraints.NotNull;

public class AccountDto {

    Long id;
    @NotNull
    AccountType tipoConta;
    @NotNull
    Long userId;
    @NotNull
    String nome;
    @NotNull
    Integer idade;
    @NotNull
    String telefone;
    @NotNull
    String endereco;

    public AccountDto() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountType getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(AccountType tipoConta) {
        this.tipoConta = tipoConta;
    }

    public void setSaldo(Double saldo) {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNome() {
        return (nome != null) ? nome : "Nome não disponível";
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return (idade != null) ? idade : 0;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getTelefone() {
        return (telefone != null) ? telefone : "Telefone não disponível";
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return (endereco != null) ? endereco : "Endereço não disponível";
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
