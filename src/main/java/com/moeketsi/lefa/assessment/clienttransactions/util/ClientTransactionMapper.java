package com.moeketsi.lefa.assessment.clienttransactions.util;

import com.moeketsi.lefa.assessment.clienttransactions.dto.request.AddClientRequestDTO;
import com.moeketsi.lefa.assessment.clienttransactions.dto.response.PhysicalAddressDTO;
import com.moeketsi.lefa.assessment.clienttransactions.dto.response.TransactionDTO;
import com.moeketsi.lefa.assessment.clienttransactions.dto.response.TransactionResponseDTO;
import com.moeketsi.lefa.assessment.clienttransactions.entity.Address;
import com.moeketsi.lefa.assessment.clienttransactions.entity.Client;
import com.moeketsi.lefa.assessment.clienttransactions.entity.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClientTransactionMapper {

    public TransactionResponseDTO mapToTransactionResponseDTO(Client client) {
        if (client == null)
            return TransactionResponseDTO.builder().build();
        PhysicalAddressDTO physicalAddressDTO = null;
        if (client.getAddress() != null)
            physicalAddressDTO = PhysicalAddressDTO.builder().
                    streetAddress(client.getAddress().getStreetAddress()).
                    city(client.getAddress().getCity()).
                    zipcode(client.getAddress().getZipcode()).
                    build();

        List<Transaction> transactions = client.getTransactions();
        List<TransactionDTO> transactionDTOs = null;
        if (transactions != null) {
            List<TransactionDTO> dtos = new ArrayList<>();
            transactions.forEach(transaction ->
                    dtos.add(TransactionDTO.builder().
                            amount(transaction.getAmount()).
                            transactionDate(transaction.getTransactionDate()).build()));
            transactionDTOs = dtos;
        }

        return TransactionResponseDTO.builder().
                firstName(client.getFirstName()).
                lastName(client.getLastName()).
                fullName(client.getFullName()).
                idNumber(client.getIdNumber()).
                transactions(transactionDTOs).
                physicalAddressDTO(physicalAddressDTO)
                .build();
    }

    public Client mapToClientEntity(AddClientRequestDTO addClientRequestDTO) {
        Client client = new Client();
        client.setFirstName(addClientRequestDTO.getFirstName());
        client.setLastName(addClientRequestDTO.getLastName());
        client.setFullName(addClientRequestDTO.getFullName());
        client.setIdNumber(addClientRequestDTO.getIdNumber());
        client.setMobileNumber(addClientRequestDTO.getMobileNumber());
        if (addClientRequestDTO.getPhysicalAddressDTO() != null) {
            Address address = new Address();
            address.setCity(addClientRequestDTO.getPhysicalAddressDTO().getCity());
            address.setStreetAddress(addClientRequestDTO.getPhysicalAddressDTO().getStreetAddress());
            address.setZipcode(addClientRequestDTO.getPhysicalAddressDTO().getZipcode());
            client.setAddress(address);
        }
        return client;
    }


}
