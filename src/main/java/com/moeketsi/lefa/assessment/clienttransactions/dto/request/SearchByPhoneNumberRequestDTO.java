package com.moeketsi.lefa.assessment.clienttransactions.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchByPhoneNumberRequestDTO {

    private static final String MOBILE_NUMBER_NON_NULL = "Mobile number cannot be empty or null";
    private static final String VALID_SA_MOBILE_NUMBER = "Must be a valid SA Mobile Number";

    @NotEmpty(message = MOBILE_NUMBER_NON_NULL)
    @Pattern(regexp = "^27[0-9]{9}",message = VALID_SA_MOBILE_NUMBER)
    String phoneNumber;
}
