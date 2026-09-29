package br.prova.jwt.provajwt.controller;

import br.prova.jwt.provajwt.dto.chamado.ChamadoResponseDTO;
import br.prova.jwt.provajwt.dto.chamado.ChamadoRequestDTO;
import br.prova.jwt.provajwt.dto.chamado.ChamadoUpdateDTO;
import br.prova.jwt.provajwt.service.ChamadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Esta é a controller da entity Chamado, ela é responsável pelo redirecionamento para as urls destinadas
 */
@Schema(description = "Esta é a controller da entity Chamado, ela é responsável pelo redirecionamento para as urls destinadas")

@Tag(
        name = "ChamadoController",
        description = "Essa controller é responsável pelo redirecionamento para as urls destinadas"
)

@RestController
@RequestMapping("/api/v1/chamados")
public class ChamadoController {

    private final ChamadoService service;

    public ChamadoController(ChamadoService service){
        this.service = service;
    }


    /**
     * Esse método é responsável por listar os chamados existentes
     * @return List<ChamadoResponseDTO>
     */
    @Schema(description = "Esse método é responsável por listar os chamados existentes")

    @Operation(
            summary = "Listar chamados",
            description = "Esse método é responsável por listar os chamados"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "listado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "não encontrado"
            )
    })
    @GetMapping
    public ResponseEntity<List<ChamadoResponseDTO>> listarChamados(){
        return ResponseEntity.ok(service.listarChamados());
    }



    /**
     * Esse método é responsável por deletar um chamado
     * @param id
     * @return void
     */
    @Schema(description = "Esse método é responsável por deletar um chamado ")

    @Operation(
            summary = "Deletar chamado",
            description = "Esse método é responsável por deletar um chamado"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "deletado"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "não encontrado"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarChamado(@PathVariable Long id){
        service.deletarChamado(id);
        return ResponseEntity.noContent().build();
    }



    /**
     * Esse método é responsável por criar um chamado
     * @param request
     * @return ChamadoResponseDTO
     */
    @Schema(description = "Método responsável por abrir um chamado novo")
    @Operation(
            summary = "Abrir chamado",
            description = "Esse método é responsável por abrir um chamado novo"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "adicionado"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "dados inválidos"
            )
    })
    @PostMapping
    public ResponseEntity<ChamadoResponseDTO> abrirChamado(@RequestBody @Valid ChamadoRequestDTO request){
        return ResponseEntity.ok(service.abrirChamado(request));
    }



    /**
     * Esse método é responsável por atualizar o status de um chamado
     * @param id
     * @param update
     * @return ChamadoResponseDTO
     */

    @Schema(description = "Esse método é responsável por atualizar o status de um chamado")
    @Operation(
            summary = "Atualizar status",
            description = "Esse metodo é responsável por atualizar o status"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "atualizado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "dados inválidos"
            )
    })
    @PatchMapping("/{id}/status")
    public ResponseEntity<ChamadoResponseDTO> atualizarStatus(@PathVariable Long id, @RequestBody @Valid ChamadoUpdateDTO update){
        return ResponseEntity.ok(service.atualizarChamado(id,update));
    }
}
