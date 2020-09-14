package com.moeketsi.lefa.assessment.clienttransactions.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import static com.moeketsi.lefa.assessment.clienttransactions.util.ApplicationConstants.VALIDATE_MOBILE_NUMBER_REGEX;
import static com.moeketsi.lefa.assessment.clienttransactions.util.ApplicationConstants.VALID_SA_MOBILE_NUMBER;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchByPhoneNumberRequestDTO {

    @NotEmpty(message = VALID_SA_MOBILE_NUMBER)
    @Pattern(regexp = VALIDATE_MOBILE_NUMBER_REGEX, message = VALID_SA_MOBILE_NUMBER)
    String phoneNumber;
}
