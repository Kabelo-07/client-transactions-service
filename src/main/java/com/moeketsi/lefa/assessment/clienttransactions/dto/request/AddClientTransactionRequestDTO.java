package com.moeketsi.lefa.assessment.clienttransactions.dto.request;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

import static com.moeketsi.lefa.assessment.clienttransactions.util.ApplicationConstants.*;

@Data
public class AddClientTransactionRequestDTO {

    @NotEmpty(message = TRANSACTION_AMOUNT_SIZE_MUST)
    @Size(min = 1, message = TRANSACTION_AMOUNT_SIZE_MUST)
    private List<@Valid Double> transactionAmounts;

    @NotEmpty(message = VALID_RSA_ID_NUMBER)
    @Pattern(regexp = VALIDATE_RSA_ID_NUMBER_REGEX,
            message = VALID_RSA_ID_NUMBER)
    private String idNumber;
}
