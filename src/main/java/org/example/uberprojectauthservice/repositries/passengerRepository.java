package org.example.uberprojectauthservice.repositries;

import org.example.uberprojectauthservice.models.passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface passengerRepository extends JpaRepository<passenger, Long> {
}
