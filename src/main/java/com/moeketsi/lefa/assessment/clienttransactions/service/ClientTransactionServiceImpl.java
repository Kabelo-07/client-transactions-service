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

import static com.moeketsi.lefa.assessment.clienttransactions.util.ApplicationConstants.*;

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
        return mapper.mapClientEntityToTransactionResponseDTO(client);
    }

    @Override
    public TransactionResponseDTO searchByMobileNumber(SearchByPhoneNumberRequestDTO searchByPhoneNumberRequestDTO) {
        Client client = clientRepository.findByMobileNumber(searchByPhoneNumberRequestDTO.getPhoneNumber());
        return mapper.mapClientEntityToTransactionResponseDTO(client);
    }

    @Override
    public TransactionResponseDTO searchByIdNumber(SearchByIdNumberRequestDTO searchByIdNumberRequestDTO) {
        Client client = clientRepository.findByIdNumber(searchByIdNumberRequestDTO.getIdNumber());
        return mapper.mapClientEntityToTransactionResponseDTO(client);
    }

    @Override
    @Transactional
    public ResultMessageDTO addClient(AddClientRequestDTO addClientRequestDTO) {
        try {
            if (ifFieldsExist(addClientRequestDTO.getIdNumber(), addClientRequestDTO.getMobileNumber(), addClientRequestDTO.getFirstName()))
                return ResultMessageDTO.builder().resultMessage(CLIENT_FIELD_ALREADY_EXISTS_RESULT_MESSAGE).build();
            clientRepository.save(mapper.mapToClientEntity(addClientRequestDTO));
            return ResultMessageDTO.builder().resultMessage(CLIENT_SUCCESSFUL_RESULT_MESSAGE).build();
        } catch (Exception ex) {
            log.error("Failed to add client : ", ex.getMessage());
            return ResultMessageDTO.builder().resultMessage(FAILED_TO_ADD_CLIENT_RESULT_MESSAGE).build();
        }
    }

    @Override
    @Transactional
    public ResultMessageDTO addClientTranaction(AddClientTransactionRequestDTO addClientTransactionRequestDTO) {
        try {
            Client client = clientRepository.findByIdNumber(addClientTransactionRequestDTO.getIdNumber());
            if (client == null)
                return ResultMessageDTO.builder().resultMessage(NO_CLIENT_DETAILS_FOUND_RESULT_MESSAGE).build();
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
            return ResultMessageDTO.builder().resultMessage(TRANSACTIONS_SUCCESSFULLY_ADDED_RESULT_MESSAGE).build();
        } catch (Exception ex) {
            log.error("Failed to add transactions : ", ex.getMessage());
            return ResultMessageDTO.builder().resultMessage(FAILED_TO_TRANSACTIONS_ESULT_MESSAGE).build();
        }
    }

    private boolean ifFieldsExist(String idNumber, String mobileNumber, String firsname) {
        if (clientRepository.findByMobileNumber(mobileNumber) != null ||
                clientRepository.findByFirstName(firsname) != null || clientRepository.findByIdNumber(idNumber) != null)
            return true;
        return false;
    }


}
