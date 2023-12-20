package br.com.bank.persistence.model;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_conta")
    AccountType tipoConta;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User usuario;

    Double saldo;

    public Account() {
        this.usuario = null;
    }

    public Account(User usuario) {
        this.usuario = usuario;
    }
    public Long getId() {
        return id;
    }

    public AccountType getTipoConta() {
        return tipoConta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void depositar(Double valor) {
        if (tipoConta == AccountType.CONTA_POUPANCA) {
            this.saldo += valor + 0.5;
        } else {
            throw new RuntimeException("Depósito permitido apenas para conta poupança.");
        }
    }

    public void sacar(Double valor) {
        if (tipoConta == AccountType.CONTA_CORRENTE) {
            this.saldo -= valor;
        } else {
            throw new RuntimeException("Saque permitido apenas para conta corrente.");
        }
    }

    public User getUsuario() {
        return usuario;
    }

    public void setSaldo(double saldo) {
    }

    public void setTipoConta(AccountType tipoConta) {
    }
}
