package com.moeketsi.lefa.assessment.clienttransactions.repository;


import com.moeketsi.lefa.assessment.clienttransactions.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    Client findByFirstName(String firstName);

    Client findByMobileNumber(String mobileNumber);

    Client findByIdNumber(String idNumber);
}
