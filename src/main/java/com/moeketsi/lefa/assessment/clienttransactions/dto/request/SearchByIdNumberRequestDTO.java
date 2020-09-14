package com.moeketsi.lefa.assessment.clienttransactions.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import static com.moeketsi.lefa.assessment.clienttransactions.util.ApplicationConstants.VALIDATE_RSA_ID_NUMBER_REGEX;
import static com.moeketsi.lefa.assessment.clienttransactions.util.ApplicationConstants.VALID_RSA_ID_NUMBER;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchByIdNumberRequestDTO {

    @NotEmpty(message = VALID_RSA_ID_NUMBER)
    @Pattern(regexp = VALIDATE_RSA_ID_NUMBER_REGEX, message = VALID_RSA_ID_NUMBER)
    String idNumber;
}
