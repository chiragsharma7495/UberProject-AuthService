package org.example.uberprojectauthservice.controller;

import org.example.uberprojectauthservice.dto.PassengerRequestDto;
import org.example.uberprojectauthservice.dto.PassengerResponsedto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class authController {

    @PostMapping("/signup/passenger")
    public ResponseEntity<PassengerResponsedto> signup(@RequestBody PassengerRequestDto passengerRequestDto){
        return null;
    }

}
