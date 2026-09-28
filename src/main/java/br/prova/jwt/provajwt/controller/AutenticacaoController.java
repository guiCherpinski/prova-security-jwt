package br.prova.jwt.provajwt.controller;

import br.prova.jwt.provajwt.dto.token.TokenRequestDTO;
import br.prova.jwt.provajwt.dto.token.TokenResponseDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @PostMapping("/login")
    public TokenResponseDTO efetuarLogin(@RequestBody @Valid TokenRequestDTO requestDTO){

    }
}
