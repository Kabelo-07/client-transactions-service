package com.moeketsi.lefa.assessment.clienttransactions.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Transaction {

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_client_id"))
    Client client;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "Decimal(10,2) default '0.00'")
    private Double amount;
    @CreationTimestamp
    private Date transactionDate;


}
