package com.moeketsi.lefa.assessment.clienttransactions.dto.request;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

import static com.moeketsi.lefa.assessment.clienttransactions.util.ApplicationConstants.*;

@Data
public class AddClientTransactionRequestDTO {

    @Size(min = 1, message = TRANSACTION_AMOUNT_SIZE_MUST)
    private List<Double> transactionAmounts;

    @Pattern(regexp = VALIDATE_RSA_ID_NUMBER_REGEX,
            message = VALID_RSA_ID_NUMBER)
    private String idNumber;
}
