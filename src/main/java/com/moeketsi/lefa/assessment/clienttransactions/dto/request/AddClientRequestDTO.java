package com.moeketsi.lefa.assessment.clienttransactions.dto.request;

import com.moeketsi.lefa.assessment.clienttransactions.dto.response.PhysicalAddressDTO;
import com.moeketsi.lefa.assessment.clienttransactions.dto.response.TransactionDTO;
import com.moeketsi.lefa.assessment.clienttransactions.entity.Transaction;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Set;

import static com.moeketsi.lefa.assessment.clienttransactions.util.ApplicationConstants.*;

@Data
public class AddClientRequestDTO {

    @NotEmpty(message = FIRSTNAME_NON_NULL)
    private String firstName;
    @NotEmpty(message = FIRSTNAME_NON_NULL)
    private String lastName;
    private String fullName;
    @NotEmpty(message = MOBILE_NUMBER_NON_NULL)
    @Pattern(regexp = "^27[0-9]{9}", message = VALID_SA_MOBILE_NUMBER)
    private String mobileNumber;

    @NotEmpty(message = NON_NULL)
    @Pattern(regexp =VALIDATE_RSA_ID_NUMBER_REGEX, message = VALID_RSA_ID_NUMBER)
    private String idNumber;

    private PhysicalAddressDTO physicalAddressDTO;

}
