package com.moeketsi.lefa.assessment.clienttransactions.controller;

import com.moeketsi.lefa.assessment.clienttransactions.dto.request.*;
import com.moeketsi.lefa.assessment.clienttransactions.dto.response.ResultMessageDTO;
import com.moeketsi.lefa.assessment.clienttransactions.dto.response.TransactionResponseDTO;
import com.moeketsi.lefa.assessment.clienttransactions.repository.ClientRepository;
import com.moeketsi.lefa.assessment.clienttransactions.service.ClientTransactionService;
import com.moeketsi.lefa.assessment.clienttransactions.util.ClientTransactionMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("clients/")
@Validated
public class ClientController {

    private final ClientTransactionService clientTransactionService;

    private final ClientRepository clientRepository;
    private final ClientTransactionMapper clientTransactionMapper;

    @Autowired
    public ClientController(final ClientRepository clientRepository, final ClientTransactionService clientTransactionService, ClientRepository clientRepository1, ClientTransactionMapper clientTransactionMapper) {
        this.clientTransactionService = clientTransactionService;
        this.clientRepository = clientRepository1;
        this.clientTransactionMapper = clientTransactionMapper;
    }

    @SneakyThrows
    @PostMapping(path = "/search/firstname", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<TransactionResponseDTO> getTransactionsByFirstName(final @RequestBody @Valid SearchByFirstNameRequestDTO request) {
        final TransactionResponseDTO response = clientTransactionService.searchByFirstName(request);
        return ResponseEntity.ok(response);
    }

    @SneakyThrows
    @PostMapping(path = "/search/phonenumber", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<TransactionResponseDTO> getTransactionsByPhoneNumber(final @RequestBody @Valid SearchByPhoneNumberRequestDTO request) {
        final TransactionResponseDTO response = clientTransactionService.searchByMobileNumber(request);
        return ResponseEntity.ok(response);
    }

    @SneakyThrows
    @PostMapping(path = "/search/idnumber", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<TransactionResponseDTO> getTransactionsIdNumber(final @RequestBody @Valid SearchByIdNumberRequestDTO request) {
        final TransactionResponseDTO response = clientTransactionService.searchByIdNumber(request);
        return ResponseEntity.ok(response);
    }

    @SneakyThrows
    @PostMapping(path = "/add/client", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<ResultMessageDTO> addClient(@RequestBody @Valid AddClientRequestDTO requestDTO) {
        final ResultMessageDTO response = clientTransactionService.addClient(requestDTO);
        return ResponseEntity.ok(response);
    }

    @SneakyThrows
    @PostMapping(path = "/add/client/transactions", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<ResultMessageDTO> addClientTransactions(@RequestBody @Valid AddClientTransactionRequestDTO addClientTransactionRequestDTO) {
        final ResultMessageDTO response = clientTransactionService.addClientTranaction(addClientTransactionRequestDTO);
        return ResponseEntity.ok(response);
    }

}
