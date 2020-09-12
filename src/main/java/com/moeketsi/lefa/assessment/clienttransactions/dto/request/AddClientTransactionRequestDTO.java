package com.moeketsi.lefa.assessment.clienttransactions.dto.request;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.List;

import static com.moeketsi.lefa.assessment.clienttransactions.util.ApplicationConstants.NON_NULL;
import static com.moeketsi.lefa.assessment.clienttransactions.util.ApplicationConstants.TRANSACTION_AMOUNT_SIZE_MUST;
import static com.moeketsi.lefa.assessment.clienttransactions.util.ApplicationConstants.VALIDATE_RSA_ID_NUMBER_REGEX;
import static com.moeketsi.lefa.assessment.clienttransactions.util.ApplicationConstants.VALID_RSA_ID_NUMBER;

@Data
public class AddClientTransactionRequestDTO {

    @Size(min = 1, message = TRANSACTION_AMOUNT_SIZE_MUST)
    private List<Double> transactionAmounts;

    @NotEmpty(message = NON_NULL)
    @Pattern(regexp = VALIDATE_RSA_ID_NUMBER_REGEX,
            message = VALID_RSA_ID_NUMBER)
    private String idNumber;
}
