package Boxter.AppExamenesAPI.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {

    private String token;

    public JwtResponse() {}

    public JwtResponse(String token) {
        this.token = token;
    }
}
