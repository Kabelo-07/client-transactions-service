package com.moeketsi.lefa.assessment.clienttransactions.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Client {

    @Embedded
    Address address;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    List<Transaction> transactions;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Integer id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    private String fullName;
    @Column(unique = true, nullable = false)
    private String mobileNumber;
    @Column(unique = true, nullable = false, updatable = false, length = 13)
    private String idNumber;

}
