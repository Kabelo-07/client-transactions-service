package com.moeketsi.lefa.assessment.clienttransactions.service;

import com.moeketsi.lefa.assessment.clienttransactions.dto.request.AddClientRequestDTO;
import com.moeketsi.lefa.assessment.clienttransactions.dto.request.SearchByFirstNameRequestDTO;
import com.moeketsi.lefa.assessment.clienttransactions.dto.request.SearchByIdNumberRequestDTO;
import com.moeketsi.lefa.assessment.clienttransactions.dto.request.SearchByPhoneNumberRequestDTO;
import com.moeketsi.lefa.assessment.clienttransactions.dto.response.PhysicalAddressDTO;
import com.moeketsi.lefa.assessment.clienttransactions.dto.response.ResultMessageDTO;
import com.moeketsi.lefa.assessment.clienttransactions.dto.response.TransactionDTO;
import com.moeketsi.lefa.assessment.clienttransactions.dto.response.TransactionResponseDTO;
import com.moeketsi.lefa.assessment.clienttransactions.entity.Address;
import com.moeketsi.lefa.assessment.clienttransactions.entity.Client;
import com.moeketsi.lefa.assessment.clienttransactions.entity.Transaction;
import com.moeketsi.lefa.assessment.clienttransactions.repository.ClientRepository;
import com.moeketsi.lefa.assessment.clienttransactions.util.ClientTransactionMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class ClientTransactionServiceTest {

    @Mock
    private ClientRepository clientRepository;
    @Mock
    private ClientTransactionMapper mapper;
    @InjectMocks
    ClientTransactionServiceImpl clientTransactionService;

    @Before
    private Client setUpClient(){
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

    private Transaction setUpTransaction(){
        Transaction transaction = new Transaction();
        transaction.setId(1);
        transaction.setAmount(13000.0D);
        transaction.setTransactionDate(new Date());
        return transaction;
    }

    private Address setUpAddress(){
        Address address = new Address();
        address.setStreetAddress("8 Martin Street");
        address.setCity("Melbourn");
        address.setZipcode("1400");
        return address;
    }

    private AddClientRequestDTO buildAddCilentRequest(){
        AddClientRequestDTO addClientRequestDTO = new AddClientRequestDTO();
        addClientRequestDTO.setFirstName("Zlatan");
        addClientRequestDTO.setLastName("Ibrahimovic");
        addClientRequestDTO.setFullName("Zlatan Ibrahimovic");
        addClientRequestDTO.setIdNumber("1111111111111");
        addClientRequestDTO.setMobileNumber("27789007728");
        addClientRequestDTO.setPhysicalAddressDTO(setUpPhysicalAddressDTO());
        return addClientRequestDTO;
    }
    @Before
    private TransactionResponseDTO setUpTransactionResponseDTO(){

        return TransactionResponseDTO.builder()
                .firstName("Zlatan")
                .lastName("Ibrahimovic")
                .fullName("Zlatan Ibrahimovic")
                .idNumber("1111111111111")
                .mobileNumber("27789007728")
                .transactions(Collections.singletonList(setUpTransactionDTO()))
                .physicalAddressDTO(setUpPhysicalAddressDTO())
                .build();
    }

    @Before
    private TransactionDTO setUpTransactionDTO(){
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAmount(13000.0D);
        transactionDTO.setTransactionDate(new Date());
        return transactionDTO;
    }

    private PhysicalAddressDTO setUpPhysicalAddressDTO(){
        PhysicalAddressDTO addressDTO = new PhysicalAddressDTO();
        addressDTO.setStreetAddress("8 Martin Street");
        addressDTO.setCity("Melbourn");
        addressDTO.setZipcode("1400");
        return addressDTO;
    }

    @Test
    void searchByFirstNameTest() {

        when(clientRepository.findByFirstName(anyString())).thenReturn(setUpClient());
        when(mapper.mapToTransactionResponseDTO(any(Client.class))).thenReturn(setUpTransactionResponseDTO());
        TransactionResponseDTO transactionResponseDTO = clientTransactionService.searchByFirstName(
                SearchByFirstNameRequestDTO.builder().firstName("Zlatan").build());
        assertEquals("Zlatan",transactionResponseDTO.getFirstName());
    }

    @Test
    void searchByMobileNumberTest() {

        when(clientRepository.findByMobileNumber(anyString())).thenReturn(setUpClient());
        when(mapper.mapToTransactionResponseDTO(any(Client.class))).thenReturn(setUpTransactionResponseDTO());
        TransactionResponseDTO transactionResponseDTO = clientTransactionService.searchByMobileNumber(
                SearchByPhoneNumberRequestDTO.builder().phoneNumber("27789007728").build());
        assertEquals("27789007728",transactionResponseDTO.getMobileNumber());
    }

    @Test
    void searchByIdNumberTest() {
        when(clientRepository.findByIdNumber(anyString())).thenReturn(setUpClient());
        when(mapper.mapToTransactionResponseDTO(any(Client.class))).thenReturn(setUpTransactionResponseDTO());
        TransactionResponseDTO transactionResponseDTO = clientTransactionService.searchByIdNumber(
                SearchByIdNumberRequestDTO.builder().idNumber("1111111111111").build());
        assertEquals("1111111111111",transactionResponseDTO.getIdNumber());
    }

    @Test
    void addClientTest() {
        when(mapper.mapToClientEntity(any(AddClientRequestDTO.class))).thenReturn(new Client());
        when(clientRepository.save(any(Client.class))).thenReturn(setUpClient());
            ResultMessageDTO resultMessageDTO = clientTransactionService.addClient(buildAddCilentRequest());
             assertEquals("Client Successfully added",resultMessageDTO.getResultMessage());

    }

    @Test
    void addClientTranactionTest() {
    }


}