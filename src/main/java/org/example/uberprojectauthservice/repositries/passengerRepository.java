package org.example.uberprojectauthservice.repositries;

import org.example.uberprojectauthservice.models.passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface passengerRepository extends JpaRepository<passenger, Long> {
    Optional<passenger> findpassengerByEmail(String email);
}
