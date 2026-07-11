package org.example.uberprojectauthservice.Service;

import org.example.uberprojectauthservice.helpers.authPassengerDetails;
import org.example.uberprojectauthservice.models.passenger;
import org.example.uberprojectauthservice.repositries.passengerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


// this class is responsible for loading the user in the form of userDetails object for Auth;
@Service
public class userDetailsServiceImpl implements UserDetailsService {
    private final passengerRepository passengerRepository;

    public userDetailsServiceImpl(passengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<passenger> passenger = passengerRepository.findpassengerByEmail(username);
        if(passenger.isPresent()) return new authPassengerDetails(passenger.get());
        else throw new UsernameNotFoundException("User not found");
    }
}
