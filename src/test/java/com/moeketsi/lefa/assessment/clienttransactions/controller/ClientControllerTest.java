package com.moeketsi.lefa.assessment.clienttransactions.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moeketsi.lefa.assessment.clienttransactions.dto.request.AddClientRequestDTO;
import com.moeketsi.lefa.assessment.clienttransactions.dto.request.SearchByFirstNameRequestDTO;
import com.moeketsi.lefa.assessment.clienttransactions.dto.request.SearchByIdNumberRequestDTO;
import com.moeketsi.lefa.assessment.clienttransactions.dto.request.SearchByPhoneNumberRequestDTO;
import com.moeketsi.lefa.assessment.clienttransactions.dto.response.PhysicalAddressDTO;
import com.moeketsi.lefa.assessment.clienttransactions.dto.response.TransactionDTO;
import com.moeketsi.lefa.assessment.clienttransactions.dto.response.TransactionResponseDTO;
import com.moeketsi.lefa.assessment.clienttransactions.exception.Problem;
import com.moeketsi.lefa.assessment.clienttransactions.service.ClientTransactionService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.Date;

import static com.moeketsi.lefa.assessment.clienttransactions.util.ApplicationConstants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
class ClientControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @Mock
    private ClientTransactionService clientTransactionService;
    @InjectMocks
    private ClientController clientController;

    @Test
    void getTransactionsByFirstName() throws Exception {
        SearchByFirstNameRequestDTO searchByFirstNameRequestDTO = new SearchByFirstNameRequestDTO("Zlatan");
        when(clientTransactionService.searchByFirstName(any(SearchByFirstNameRequestDTO.class))).thenReturn(setUpTransactionResponseDTO());
        ResponseEntity<TransactionResponseDTO> responseEntity = clientController.getTransactionsByFirstName(searchByFirstNameRequestDTO);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        assertEquals("Zlatan",responseEntity.getBody().getFirstName());
    }

    @Test
    void getTransactionsByFirstNameException() throws Exception {
        SearchByFirstNameRequestDTO searchByFirstNameRequestDTO = new SearchByFirstNameRequestDTO("");
        Problem problem = mapper.readValue(mockMvc.perform(MockMvcRequestBuilders.post("/clients/search/firstname")
                .content(mapper.writeValueAsString(searchByFirstNameRequestDTO))
                .accept(MediaType.APPLICATION_JSON).contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(),Problem.class);
        assertEquals(FIRSTNAME_NON_NULL,problem.getDetail());

    }

    @Test
    void getTransactionsByPhoneNumber() throws Exception {
        SearchByPhoneNumberRequestDTO searchByPhoneNumberRequestDTO = new SearchByPhoneNumberRequestDTO("27789007728");
        when(clientTransactionService.searchByMobileNumber(any(SearchByPhoneNumberRequestDTO.class))).thenReturn(setUpTransactionResponseDTO());
        ResponseEntity<TransactionResponseDTO> responseEntity =   clientController.getTransactionsByPhoneNumber(searchByPhoneNumberRequestDTO);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        assertEquals("Zlatan",responseEntity.getBody().getFirstName());
    }

    @Test
    void getTransactionsByPhoneNumberException() throws Exception {
        SearchByPhoneNumberRequestDTO searchByPhoneNumberRequestDTO = new SearchByPhoneNumberRequestDTO("0781234567");
        Problem problem = mapper.readValue(mockMvc.perform(MockMvcRequestBuilders.post("/clients/search/phonenumber")
                .content(mapper.writeValueAsString(searchByPhoneNumberRequestDTO))
                .accept(MediaType.APPLICATION_JSON).contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(),Problem.class);
        assertEquals(VALID_SA_MOBILE_NUMBER,problem.getDetail());
    }

    @Test
    void getTransactionsIdNumber() {
        SearchByIdNumberRequestDTO searchByIdNumberRequestDTO = new SearchByIdNumberRequestDTO("1111111111111");
        when(clientTransactionService.searchByIdNumber(any(SearchByIdNumberRequestDTO.class))).thenReturn(setUpTransactionResponseDTO());
        ResponseEntity<TransactionResponseDTO> responseEntity =   clientController.getTransactionsIdNumber(searchByIdNumberRequestDTO);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        assertEquals("1111111111111",responseEntity.getBody().getIdNumber());
    }

    @Test
    void getTransactionsIdNumberException() throws Exception {
        SearchByIdNumberRequestDTO searchByIdNumberRequestDTO = new SearchByIdNumberRequestDTO("1122111111111");
        Problem problem = mapper.readValue(mockMvc.perform(MockMvcRequestBuilders.post("/clients/search/idnumber")
                .content(mapper.writeValueAsString(searchByIdNumberRequestDTO))
                .accept(MediaType.APPLICATION_JSON).contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(),Problem.class);
        assertEquals(VALID_RSA_ID_NUMBER,problem.getDetail());
    }

    @Test
    void addClient() {
        when(clientTransactionService.addClient(buildAddCilentRequest())).thenReturn(null);
    }

    @Test
    void addClientTransactions(){
    }
    
    private static TransactionResponseDTO setUpTransactionResponseDTO() {

        return TransactionResponseDTO.builder()
                .firstName("Zlatan")
                .lastName("Ibrahimovic")
                .fullName("Zlatan Ibrahimovic")
                .idNumber("1111111111111")
                .mobileNumber("27789007728")
                .transactions(Collections.singletonList(setUpTransactionDTO()))
                .physicalAddressDTO(null)
                .build();

    }

    
    private static TransactionDTO setUpTransactionDTO() {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAmount(13000.0D);
        transactionDTO.setTransactionDate(new Date());
        return transactionDTO;
    }

    
    private static  PhysicalAddressDTO setUpPhysicalAddressDTO() {
        PhysicalAddressDTO addressDTO = new PhysicalAddressDTO();
        addressDTO.setStreetAddress("8 Martin Street");
        addressDTO.setCity("Melbourn");
        addressDTO.setZipcode("1400");
        return addressDTO;
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
}