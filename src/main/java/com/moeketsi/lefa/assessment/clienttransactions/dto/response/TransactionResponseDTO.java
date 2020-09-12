package com.moeketsi.lefa.assessment.clienttransactions.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionResponseDTO {

    List<TransactionDTO> transactions;
    @JsonProperty("First Name")
    private String firstName;
    @JsonProperty("Last Name")
    private String lastName;
    @JsonProperty("Full Name")
    private String fullName;
    @JsonProperty("Mobile Number")
    private String mobileNumber;
    @JsonProperty("Id Number")
    private String idNumber;
    @JsonProperty("Physical Address")
    private PhysicalAddressDTO physicalAddressDTO;
}
