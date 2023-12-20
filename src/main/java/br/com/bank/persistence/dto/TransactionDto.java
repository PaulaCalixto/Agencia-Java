package br.com.bank.persistence.dto;

import jakarta.validation.constraints.NotNull;

public record TransactionDto(
        @NotNull(message = "ID da conta não pode ser nulo") Long accountId,
        @NotNull(message = "O valor da transação não pode ser nulo") Double amount) {

    public TransactionDto(Long accountId, Double amount) {
        this.accountId = accountId;
        this.amount = amount;
    }

    @Override
    public Long accountId() {
        return accountId;
    }

    @Override
    public Double amount() {
        return amount;
    }
}
