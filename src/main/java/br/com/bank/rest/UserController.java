package br.com.bank.rest;

import br.com.bank.persistence.dto.AccountDto;
import br.com.bank.persistence.dto.TransactionDto;
import br.com.bank.persistence.dto.UserDto;
import br.com.bank.services.UserService;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/v1/users")
public class UserController {

    @Inject
    UserService userService;

    @POST
    @Path("/add-user")
    public Response createUser(@Valid UserDto userData) {
        userService.createUser(userData);
        return Response.status(Response.Status.OK).entity("Ok").build();
    }

    @POST
    @Path("/create-account")
    public Response createAccount(@Valid AccountDto accountData) {
        userService.createAccount(accountData);
        return Response.status(Response.Status.CREATED).entity("Conta criada com sucesso").build();
    }

    @POST
    @Path("/deposit")
    public Response deposit(@Valid TransactionDto transactionData) {
        userService.deposit(transactionData.getAccountId(), transactionData.getAmount());
        return Response.status(Response.Status.OK).entity("Depósito realizado com sucesso").build();
    }

    @POST
    @Path("/withdraw")
    public Response withdraw(@Valid TransactionDto transactionData) {
        userService.withdraw(transactionData.getAccountId(), transactionData.getAmount());
        return Response.status(Response.Status.OK).entity("Saque realizado com sucesso").build();
    }

    @GET
    @Path("/list-accounts/{userId}")
    public Response listAccounts(@PathParam("userId") Long userId) {
        List<AccountDto> accounts = userService.listAccounts(userId);
        return Response.status(Response.Status.OK).entity(accounts).build();
    }

    @GET
    @Path("/account-details/{accountId}")
    public Response accountDetails(@PathParam("accountId") Long accountId) {
        AccountDto accountDetails = userService.getAccountDetails(accountId);
        return Response.status(Response.Status.OK).entity(accountDetails).build();
    }

    @GET
    @Path("/user-details/{userId}")
    public Response userDetails(@PathParam("userId") Long userId) {
        UserDto userDetails = userService.getUserDetails(userId);
        return Response.status(Response.Status.OK).entity(userDetails).build();
    }
}
