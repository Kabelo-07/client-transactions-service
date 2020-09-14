package com.moeketsi.lefa.assessment.clienttransactions.util;

import com.moeketsi.lefa.assessment.clienttransactions.dto.request.AddClientRequestDTO;
import com.moeketsi.lefa.assessment.clienttransactions.dto.response.PhysicalAddressDTO;
import com.moeketsi.lefa.assessment.clienttransactions.dto.response.TransactionDTO;
import com.moeketsi.lefa.assessment.clienttransactions.dto.response.TransactionResponseDTO;
import com.moeketsi.lefa.assessment.clienttransactions.entity.Address;
import com.moeketsi.lefa.assessment.clienttransactions.entity.Client;
import com.moeketsi.lefa.assessment.clienttransactions.entity.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClientTransactionMapperTest {

    @Autowired
    ClientTransactionMapper mapper;

    @Test
    void mapClientEntityToTransactionResponseDTOTest() {
       TransactionResponseDTO responseDTO = mapper.mapClientEntityToTransactionResponseDTO(setUpClient());
       assertEquals(setUpClient().getFirstName(),responseDTO.getFirstName());
        assertEquals(setUpClient().getLastName(),responseDTO.getLastName());
        assertEquals(setUpClient().getIdNumber(),responseDTO.getIdNumber());
        assertEquals(setUpClient().getMobileNumber(),responseDTO.getMobileNumber());
        assertEquals(setUpClient().getAddress().getCity(),responseDTO.getPhysicalAddressDTO().getCity());
        assertEquals(setUpClient().getAddress().getStreetAddress(),responseDTO.getPhysicalAddressDTO().getStreetAddress());
        assertEquals(setUpClient().getAddress().getZipcode(),responseDTO.getPhysicalAddressDTO().getZipcode());
    }

    @Test
    void mapClientEntityToTransactionResponseDTONullClientTest() {
        TransactionResponseDTO responseDTO = mapper.mapClientEntityToTransactionResponseDTO(null);
        assertEquals(null,responseDTO.getFirstName());
    }

    @Test
    void mapToClientEntityTest() {
        AddClientRequestDTO requestDTO = buildAddCilentRequest();
        Client client = mapper.mapToClientEntity(requestDTO);
        assertEquals(requestDTO.getFirstName(),client.getFirstName());
        assertEquals(requestDTO.getLastName(),client.getLastName());
        assertEquals(requestDTO.getIdNumber(),client.getIdNumber());
        assertEquals(requestDTO.getMobileNumber(),client.getMobileNumber());
        assertEquals(requestDTO.getPhysicalAddressDTO().getCity(),client.getAddress().getCity());
        assertEquals(requestDTO.getPhysicalAddressDTO().getStreetAddress(),client.getAddress().getStreetAddress());
        assertEquals(requestDTO.getPhysicalAddressDTO().getZipcode(),client.getAddress().getZipcode());

    }

    private static Client setUpClient() {
        Client client = new Client();
        client.setFirstName("Zlatan");
        client.setLastName("Ibrahimovic");
        client.setFullName("Zlatan Ibrahimovic");
        client.setIdNumber("1111111111111");
        client.setMobileNumber("27791234567");
        client.setAddress(setUpAddress());
        client.setTransactions(Collections.singletonList(setUpTransaction()));
        return client;
    }

    private static Transaction setUpTransaction() {
        Transaction transaction = new Transaction();
        transaction.setId(1);
        transaction.setAmount(13000.0D);
        transaction.setTransactionDate(new Date());
        return transaction;
    }

    private static Address setUpAddress() {
        Address address = new Address();
        address.setStreetAddress("8 Martin Street");
        address.setCity("Melbourn");
        address.setZipcode("1400");
        return address;
    }

    private static AddClientRequestDTO buildAddCilentRequest() {
        AddClientRequestDTO addClientRequestDTO = new AddClientRequestDTO();
        addClientRequestDTO.setFirstName("Zlatan");
        addClientRequestDTO.setLastName("Ibrahimovic");
        addClientRequestDTO.setFullName("Zlatan Ibrahimovic");
        addClientRequestDTO.setIdNumber("1111111111111");
        addClientRequestDTO.setMobileNumber("27789007728");
        addClientRequestDTO.setPhysicalAddressDTO(setUpPhysicalAddressDTO());
        return addClientRequestDTO;
    }

    private static TransactionDTO setUpTransactionDTO() {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAmount(13000.0D);
        transactionDTO.setTransactionDate(new Date());
        return transactionDTO;
    }


    private static PhysicalAddressDTO setUpPhysicalAddressDTO() {
        PhysicalAddressDTO addressDTO = new PhysicalAddressDTO();
        addressDTO.setStreetAddress("8 Martin Street");
        addressDTO.setCity("Melbourn");
        addressDTO.setZipcode("1400");
        return addressDTO;
    }
}