package com.moeketsi.lefa.assessment.clienttransactions.service;


import com.moeketsi.lefa.assessment.clienttransactions.dto.request.*;
import com.moeketsi.lefa.assessment.clienttransactions.dto.response.ResultMessageDTO;
import com.moeketsi.lefa.assessment.clienttransactions.dto.response.TransactionResponseDTO;

import java.util.List;

public interface ClientTransactionService {

    List<TransactionResponseDTO> searchByFirstName(SearchByFirstNameRequestDTO searchByFirstNameRequestDTO);

    TransactionResponseDTO searchByMobileNumber(SearchByPhoneNumberRequestDTO searchByIdNumberRequestDTO);

    TransactionResponseDTO searchByIdNumber(SearchByIdNumberRequestDTO searchByIdNumberRequestDTO);

    ResultMessageDTO addClient(AddClientRequestDTO addClientRequestDTO);

    ResultMessageDTO addClientTranaction(AddClientTransactionRequestDTO addClientTransactionRequestDTO);

}
