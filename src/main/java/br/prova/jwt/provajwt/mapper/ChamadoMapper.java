package br.prova.jwt.provajwt.mapper;

import br.prova.jwt.provajwt.dto.chamado.ChamadoResponseDTO;
import br.prova.jwt.provajwt.dto.chamado.ChamadoRequestDTO;
import br.prova.jwt.provajwt.entity.ChamadoEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChamadoMapper {

    public ChamadoEntity toEntity(ChamadoRequestDTO requestDTO){
        return ChamadoEntity.builder()
                .titulo(requestDTO.titulo())
                .descricao(requestDTO.descricao())
                .status(requestDTO.status())
                .prioridade(requestDTO.prioridade())
                .cliente(requestDTO.clienteId())
                .tecnico(requestDTO.tecnicoId())
                .dataCriacao(requestDTO.dataCriacao())
                .build();
    }

    public ChamadoResponseDTO toResponse(ChamadoEntity entity){
        return new ChamadoResponseDTO(
                entity.getId(),
                entity.getTitulo(),
                entity.getDescricao(),
                entity.getStatus(),
                entity.getPrioridade(),
                entity.getCliente(),
                entity.getTecnico(),
                entity.getDataCriacao(),
                entity.getDataAtualizacao()
        );
    }

    public List<ChamadoResponseDTO> toResponseList (List<ChamadoEntity> entity){
        return entity.stream().map(this :: toResponse).toList();
    }
}
