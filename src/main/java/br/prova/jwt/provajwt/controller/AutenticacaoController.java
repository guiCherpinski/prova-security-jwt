package br.prova.jwt.provajwt.controller;

import br.prova.jwt.provajwt.dto.token.TokenRequestDTO;
import br.prova.jwt.provajwt.dto.token.TokenResponseDTO;
import br.prova.jwt.provajwt.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Essa cotroller serve para a obtenção do token jwt
 */
@Tag(
        name = "AutenticacaoController",
        description = "Essa controller serve para a obtenção do token jwt"
)

@Schema(description = "Essa controller serve para a obtenção do token jwt")
@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    private final AuthenticationManager manager;
    private final TokenService service;

    public AutenticacaoController(AuthenticationManager manager, TokenService service){
        this.manager = manager;
        this.service = service;
    }

    @Schema(description = "Esse método é onde é feito o login com os dados e onde é retornado o token")
    /**
     * Esse metódo é onde e feito o login com os dados e onde é retornado o token
     * @param requestDTO
     * @return TokenResponseDTO
     */

    @Operation(
            summary = "Efetuar login",
            description = "Serve para efetuar o login e receber o token"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "login efetuado"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "dados inválidos"
            )
    })
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> efetuarLogin(@RequestBody @Valid TokenRequestDTO requestDTO){
        var usuario = UsernamePasswordAuthenticationToken.unauthenticated(requestDTO.username(),requestDTO.password());
        var autenticacao = manager.authenticate(usuario);
        var token = service.validarToken(autenticacao);

        return ResponseEntity.ok(new TokenResponseDTO(token));
    }
}
