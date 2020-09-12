package com.moeketsi.lefa.assessment.clienttransactions.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionResponseDTO implements Serializable {

    List<TransactionDTO> transactions;
    private String firstName;
    private String lastName;
    private String fullName;
    private String mobileNumber;
    private String idNumber;
    @JsonProperty("Physical_Address")
    private PhysicalAddressDTO physicalAddressDTO;
}
