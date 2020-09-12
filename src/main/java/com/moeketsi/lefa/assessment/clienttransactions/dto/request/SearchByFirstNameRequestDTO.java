package com.moeketsi.lefa.assessment.clienttransactions.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

import static com.moeketsi.lefa.assessment.clienttransactions.util.ApplicationConstants.FIRSTNAME_NON_NULL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchByFirstNameRequestDTO {


    @NotEmpty(message = FIRSTNAME_NON_NULL)
    @JsonProperty("first_name")
    String firstName;
}
