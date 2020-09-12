package com.moeketsi.lefa.assessment.clienttransactions.dto.request;

import com.moeketsi.lefa.assessment.clienttransactions.dto.response.PhysicalAddressDTO;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import static com.moeketsi.lefa.assessment.clienttransactions.util.ApplicationConstants.*;

@Data
public class AddClientRequestDTO {

    @NotEmpty(message = FIRSTNAME_NON_NULL)
    private String firstName;
    @NotEmpty(message = LASTNAME_NON_NULL)
    private String lastName;
    private String fullName;
    @Pattern(regexp = VALIDATE_MOBILE_NUMBER_REGEX, message = VALID_SA_MOBILE_NUMBER)
    private String mobileNumber;
    @Pattern(regexp = VALIDATE_RSA_ID_NUMBER_REGEX, message = VALID_RSA_ID_NUMBER)
    private String idNumber;

    private PhysicalAddressDTO physicalAddressDTO;

}
