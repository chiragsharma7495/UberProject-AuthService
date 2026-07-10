package org.example.uberprojectauthservice.dto;

import lombok.*;
import org.example.uberprojectauthservice.models.passenger;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassengerResponsedto {

    private Long id;

    private String name;

    private String email;

    private String password; //encrypted password

    private String phoneNumber;

    private Date createdAt;

    public static PassengerResponsedto from(passenger p){
        PassengerResponsedto result = PassengerResponsedto.builder()
                .id(p.getId())
                .createdAt(p.getCreatedAt())
                .email(p.getEmail())
                .password(p.getPassword())
                .phoneNumber(p.getPhoneNumber())
                .name(p.getName())
                .build();

        return result;
    }
}
