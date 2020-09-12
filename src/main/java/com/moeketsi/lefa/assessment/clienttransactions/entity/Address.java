package com.moeketsi.lefa.assessment.clienttransactions.entity;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class Address implements Serializable {

    private String streetAddress;
    private String city;
    private String zipcode;
}
