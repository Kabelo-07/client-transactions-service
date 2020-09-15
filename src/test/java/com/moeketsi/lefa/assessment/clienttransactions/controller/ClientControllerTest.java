package com.moeketsi.lefa.assessment.clienttransactions.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moeketsi.lefa.assessment.clienttransactions.dto.request.*;
import com.moeketsi.lefa.assessment.clienttransactions.dto.response.PhysicalAddressDTO;
import com.moeketsi.lefa.assessment.clienttransactions.dto.response.ResultMessageDTO;
import com.moeketsi.lefa.assessment.clienttransactions.dto.response.TransactionDTO;
import com.moeketsi.lefa.assessment.clienttransactions.dto.response.TransactionResponseDTO;
import com.moeketsi.lefa.assessment.clienttransactions.exception.Problem;
import com.moeketsi.lefa.assessment.clienttransactions.service.ClientTransactionService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.moeketsi.lefa.assessment.clienttransactions.util.ApplicationConstants.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
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

    private static PhysicalAddressDTO setUpPhysicalAddressDTO() {
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

    @Test
    void getTransactionsByFirstName(){
        SearchByFirstNameRequestDTO searchByFirstNameRequestDTO = new SearchByFirstNameRequestDTO("Zlatan");
        when(clientTransactionService.searchByFirstName(any(SearchByFirstNameRequestDTO.class))).thenReturn(Collections.singletonList(setUpTransactionResponseDTO()));
        ResponseEntity<List<TransactionResponseDTO>> responseEntity = clientController.getTransactionsByFirstName(searchByFirstNameRequestDTO);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Zlatan", responseEntity.getBody().get(0).getFirstName());
    }

    @Test
    void getTransactionsByFirstNameException() throws Exception {
        SearchByFirstNameRequestDTO searchByFirstNameRequestDTO = new SearchByFirstNameRequestDTO("");
        Problem problem = mapper.readValue(mockMvc.perform(MockMvcRequestBuilders.post("/clients/search/firstname")
                .content(mapper.writeValueAsString(searchByFirstNameRequestDTO))
                .accept(MediaType.APPLICATION_JSON).contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(), Problem.class);
        assertEquals(FIRSTNAME_NON_NULL, problem.getDetail());

    }

    @Test
    void getTransactionsByPhoneNumber()  {
        SearchByPhoneNumberRequestDTO searchByPhoneNumberRequestDTO = new SearchByPhoneNumberRequestDTO("27789007728");
        when(clientTransactionService.searchByMobileNumber(any(SearchByPhoneNumberRequestDTO.class))).thenReturn(setUpTransactionResponseDTO());
        ResponseEntity<TransactionResponseDTO> responseEntity = clientController.getTransactionsByPhoneNumber(searchByPhoneNumberRequestDTO);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Zlatan", responseEntity.getBody().getFirstName());
    }

    @Test
    void getTransactionsByPhoneNumberException() throws Exception {
        SearchByPhoneNumberRequestDTO searchByPhoneNumberRequestDTO = new SearchByPhoneNumberRequestDTO("0781234567");
        Problem problem = mapper.readValue(mockMvc.perform(MockMvcRequestBuilders.post("/clients/search/phonenumber")
                .content(mapper.writeValueAsString(searchByPhoneNumberRequestDTO))
                .accept(MediaType.APPLICATION_JSON).contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(), Problem.class);
        assertEquals(VALID_SA_MOBILE_NUMBER, problem.getDetail());
    }

    @Test
    void getTransactionsIdNumber() {
        SearchByIdNumberRequestDTO searchByIdNumberRequestDTO = new SearchByIdNumberRequestDTO("1111111111111");
        when(clientTransactionService.searchByIdNumber(any(SearchByIdNumberRequestDTO.class))).thenReturn(setUpTransactionResponseDTO());
        ResponseEntity<TransactionResponseDTO> responseEntity = clientController.getTransactionsIdNumber(searchByIdNumberRequestDTO);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("1111111111111", responseEntity.getBody().getIdNumber());
    }

    @Test
    void getTransactionsIdNumberException() throws Exception {
        SearchByIdNumberRequestDTO searchByIdNumberRequestDTO = new SearchByIdNumberRequestDTO("1122111111111");
        Problem problem = mapper.readValue(mockMvc.perform(MockMvcRequestBuilders.post("/clients/search/idnumber")
                .content(mapper.writeValueAsString(searchByIdNumberRequestDTO))
                .accept(MediaType.APPLICATION_JSON).contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(), Problem.class);
        assertEquals(VALID_RSA_ID_NUMBER, problem.getDetail());
    }

    @Test
    void addClientTest() {
        when(clientTransactionService.addClient(any(AddClientRequestDTO.class))).
                thenReturn(ResultMessageDTO.builder().resultMessage(CLIENT_SUCCESSFUL_RESULT_MESSAGE).build());
        ResponseEntity<ResultMessageDTO> responseEntity = clientController.addClient(buildAddCilentRequest());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(CLIENT_SUCCESSFUL_RESULT_MESSAGE, responseEntity.getBody().getResultMessage());
    }

    @Test
    void addClientExceptionTest() throws Exception {
        when(clientTransactionService.addClient(any(AddClientRequestDTO.class))).
                thenReturn(ResultMessageDTO.builder().resultMessage(CLIENT_SUCCESSFUL_RESULT_MESSAGE).build());
        AddClientRequestDTO addClientRequestDTO = buildAddCilentRequest();
        addClientRequestDTO.setLastName(null);
        Problem problem = mapper.readValue(mockMvc.perform(MockMvcRequestBuilders.post("/clients/add/client")
                .content(mapper.writeValueAsString(addClientRequestDTO))
                .accept(MediaType.APPLICATION_JSON).contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(), Problem.class);
        assertEquals(LASTNAME_NON_NULL, problem.getDetail());
    }

    @Test
    void addClientTransactionsTestException() {
        when(clientTransactionService.addClientTranaction(any(AddClientTransactionRequestDTO.class))).
                thenReturn(ResultMessageDTO.builder().resultMessage(CLIENT_SUCCESSFUL_RESULT_MESSAGE).build());
        AddClientTransactionRequestDTO addClientTransactionRequestDTO = new AddClientTransactionRequestDTO();
        addClientTransactionRequestDTO.setTransactionAmounts(Collections.singletonList(-20.01));
        addClientTransactionRequestDTO.setIdNumber("1111111111111");
        ResponseEntity<ResultMessageDTO> responseEntity = clientController.addClientTransactions(addClientTransactionRequestDTO);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(CLIENT_SUCCESSFUL_RESULT_MESSAGE, responseEntity.getBody().getResultMessage());
    }

}