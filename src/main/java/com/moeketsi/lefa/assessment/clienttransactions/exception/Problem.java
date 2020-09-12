package com.moeketsi.lefa.assessment.clienttransactions.exception;

import lombok.Builder;
import lombok.Data;

import java.net.URI;

@Data
@Builder
public class Problem {


    String title;

    Integer status;

    String detail;

    URI instance;


}
