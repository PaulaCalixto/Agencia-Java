package br.com.bank.persistence.dto;

import jakarta.validation.constraints.NotNull;

public record TransactionDto(
        @NotNull(message = "ID da conta não pode ser nulo") Long accountId,
        @NotNull(message = "O valor da transação não pode ser nulo") Double amount) {

    public TransactionDto(Long accountId, Double amount) {
        this.accountId = accountId;
        this.amount = amount;
    }

    public Long getAccountId() {
        return accountId;
    }

    public Double getAmount() {
        return amount;
    }
}
