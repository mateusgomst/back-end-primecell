package com.prime_cell.back_end.infra.security;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    public String generateToken(User user) {
        try{
            Algorithm algorithm = Algorithm.HMAC256("secret");//ADIONAR CHAVE PRIVADA NO .ENV DPS

        }catch (JWTCreationException e){
            throw new JWTCreationException("Error while generating token", e);
        }
    }
}
