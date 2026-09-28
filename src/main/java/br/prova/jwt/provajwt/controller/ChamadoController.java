package br.prova.jwt.provajwt.controller;

import br.prova.jwt.provajwt.dto.chamado.ChamadoResponseDTO;
import br.prova.jwt.provajwt.dto.chamado.ChamadoRequestDTO;
import br.prova.jwt.provajwt.service.ChamadoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chamados")
public class ChamadoController {

    private final ChamadoService service;

    public ChamadoController(ChamadoService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ChamadoResponseDTO>> listarChamados(){
        return ResponseEntity.ok(service.listarChamados());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarChamado(@PathVariable Long id){
        service.deletarChamado(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<ChamadoResponseDTO> abrirChamado(@RequestBody @Valid ChamadoRequestDTO request){



    }
}
