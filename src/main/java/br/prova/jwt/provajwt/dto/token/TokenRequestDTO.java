package br.prova.jwt.provajwt.dto.token;

public record TokenRequestDTO (
        String username,
        String password
){
}
