package com.moeketsi.lefa.assessment.clienttransactions.service;


import com.moeketsi.lefa.assessment.clienttransactions.dto.request.*;
import com.moeketsi.lefa.assessment.clienttransactions.dto.response.ResultMessageDTO;
import com.moeketsi.lefa.assessment.clienttransactions.dto.response.TransactionResponseDTO;
import com.moeketsi.lefa.assessment.clienttransactions.entity.Client;
import com.moeketsi.lefa.assessment.clienttransactions.entity.Transaction;
import com.moeketsi.lefa.assessment.clienttransactions.repository.ClientRepository;
import com.moeketsi.lefa.assessment.clienttransactions.util.ClientTransactionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ClientTransactionServiceImpl implements ClientTransactionService {

    private final ClientRepository clientRepository;

    private final ClientTransactionMapper mapper;

    @Autowired
    public ClientTransactionServiceImpl(ClientRepository clientRepository, ClientTransactionMapper mapper) {
        this.clientRepository = clientRepository;
        this.mapper = mapper;
    }

    @Override
    public TransactionResponseDTO searchByFirstName(SearchByFirstNameRequestDTO searchByFirstNameRequestDTO) {
        Client client = clientRepository.findByFirstName(searchByFirstNameRequestDTO.getFirstName());
        return mapper.mapToTransactionResponseDTO(client);
    }

    @Override
    public TransactionResponseDTO searchByMobileNumber(SearchByPhoneNumberRequestDTO searchByPhoneNumberRequestDTO) {
        Client client = clientRepository.findByMobileNumber(searchByPhoneNumberRequestDTO.getPhoneNumber());
        return mapper.mapToTransactionResponseDTO(client);
    }

    @Override
    public TransactionResponseDTO searchByIdNumber(SearchByIdNumberRequestDTO searchByIdNumberRequestDTO) {
        Client client = clientRepository.findByIdNumber(searchByIdNumberRequestDTO.getIdNumber());
        return mapper.mapToTransactionResponseDTO(client);
    }

    @Override
    @Transactional
    public ResultMessageDTO addClient(AddClientRequestDTO addClientRequestDTO) {
        try {
            if(ifFieldsExist(addClientRequestDTO.getIdNumber(),addClientRequestDTO.getMobileNumber(),addClientRequestDTO.getFirstName()))
                return ResultMessageDTO.builder().resultMessage("Client idNumber, mobile number or firstname already exists").build();
            clientRepository.save(mapper.mapToClientEntity(addClientRequestDTO));
            return ResultMessageDTO.builder().resultMessage("Client Successfully added").build();
        } catch (Exception ex) {
            log.error("Failed to add client : ", ex.getMessage());
            return ResultMessageDTO.builder().resultMessage("Failed to add client").build();
        }
    }

    @Override
    @Transactional
    public ResultMessageDTO addClientTranaction(AddClientTransactionRequestDTO addClientTransactionRequestDTO) {
        try {
            Client client = clientRepository.findByIdNumber(addClientTransactionRequestDTO.getIdNumber());
            if (client == null)
                return ResultMessageDTO.builder().resultMessage("No Client Details found").build();
            List<Transaction> transactions = new ArrayList<>();
            addClientTransactionRequestDTO.getTransactionAmounts().
                    forEach(transactionAmount -> {
                        Transaction transaction = new Transaction();
                        transaction.setAmount(transactionAmount);
                        transaction.setClient(client);
                        transactions.add(transaction);
                    });
            client.setTransactions(transactions);
            clientRepository.save(client);
            return ResultMessageDTO.builder().resultMessage("Transactions successfully added").build();
        } catch (Exception ex) {
            log.error("Failed to add transactions : ", ex.getMessage());
            return ResultMessageDTO.builder().resultMessage("Failed to add transactions").build();
        }
    }

    private boolean ifFieldsExist(String idNumber,String mobileNumber,String firsname){
        if(clientRepository.findByMobileNumber(mobileNumber) != null)
            return true;
        if(clientRepository.findByFirstName(firsname) != null)
            return true;
        if(clientRepository.findByIdNumber(idNumber) != null)
            return true;
        return false;
    }


}
