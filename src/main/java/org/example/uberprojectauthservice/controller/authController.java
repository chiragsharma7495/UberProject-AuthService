package org.example.uberprojectauthservice.controller;

import org.example.uberprojectauthservice.Service.authService;
import org.example.uberprojectauthservice.dto.PassengerRequestDto;
import org.example.uberprojectauthservice.dto.PassengerResponsedto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class authController {

    private final authService authservice;

    @PostMapping("/signup/passenger")
    public ResponseEntity<PassengerResponsedto> signup(@RequestBody PassengerRequestDto passengerRequestDto){
        PassengerResponsedto response =  authservice.signUpPassenger(passengerRequestDto);
        return new ResponseEntity<>(response , HttpStatus.CREATED);
    }

    @GetMapping("/signin/passenger")
    public ResponseEntity<?> signin(){

        return new ResponseEntity<>(10 , HttpStatus.CREATED);
    }

}
