package com.moeketsi.lefa.assessment.clienttransactions.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhysicalAddressDTO {

    private String streetAddress;
    private String city;
    private String zipcode;

}
