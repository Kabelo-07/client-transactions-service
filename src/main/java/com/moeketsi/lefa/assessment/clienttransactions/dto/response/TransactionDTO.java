package com.moeketsi.lefa.assessment.clienttransactions.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.io.Serializable;
import java.util.Date;

import static com.moeketsi.lefa.assessment.clienttransactions.util.ApplicationConstants.VALID_AMOUNT;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDTO implements Serializable {

    private Date transactionDate;
    @DecimalMin(value = "0.0", message = VALID_AMOUNT)
    @Digits(integer = 15, fraction = 2, message = VALID_AMOUNT)
    private Double amount;
}
