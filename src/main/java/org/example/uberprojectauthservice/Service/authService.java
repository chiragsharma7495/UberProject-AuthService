package org.example.uberprojectauthservice.Service;

import org.example.uberprojectauthservice.dto.PassengerRequestDto;
import org.example.uberprojectauthservice.dto.PassengerResponsedto;
import org.example.uberprojectauthservice.models.passenger;
import org.example.uberprojectauthservice.repositries.passengerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class authService {

    private final BCryptPasswordEncoder bcryptPasswordEncoder;

    private final passengerRepository passengerRepository;

    public PassengerResponsedto signUpPassenger(PassengerRequestDto passengerRequestDto){
        passenger newPassenger = passenger.builder()
                .email(passengerRequestDto.getEmail())
                .name(passengerRequestDto.getName())
                .phoneNumber(passengerRequestDto.getPhoneNumber())
                .password(bcryptPasswordEncoder.encode(passengerRequestDto.getPassword()))
                .build();

        // encode password before saving
        newPassenger.setPassword(bcryptPasswordEncoder.encode(newPassenger.getPassword()));

        passenger savedPassenger = passengerRepository.save(newPassenger);

        return PassengerResponsedto.from(savedPassenger);
    }

}
