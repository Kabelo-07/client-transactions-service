package com.moeketsi.lefa.assessment.clienttransactions.repository;


import com.moeketsi.lefa.assessment.clienttransactions.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    List<Client> findByFirstName(String firstName);

    Client findByMobileNumber(String mobileNumber);

    Client findByIdNumber(String idNumber);

    Boolean existsByIdNumberOrMobileNumber(String idNumber, String mobileNumber);
}
