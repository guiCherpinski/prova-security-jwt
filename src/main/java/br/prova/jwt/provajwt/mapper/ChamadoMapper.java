package br.prova.jwt.provajwt.mapper;

import br.prova.jwt.provajwt.dto.chamado.ChamadoResponseDTO;
import br.prova.jwt.provajwt.dto.chamado.ChamadoRequestDTO;
import br.prova.jwt.provajwt.dto.chamado.ChamadoUpdateDTO;
import br.prova.jwt.provajwt.entity.ChamadoEntity;
import br.prova.jwt.provajwt.entity.UsuarioEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * A ChamadoMapper serve para as conversões de entidades
 */

@Component
public class ChamadoMapper {

    /**
     * Transforma uma create em uma entidade
     *
     * @param requestDTO
     * @param cliente
     * @param tecnico
     * @return
     */
    public ChamadoEntity toEntity(ChamadoRequestDTO requestDTO, UsuarioEntity cliente, UsuarioEntity tecnico){
        return ChamadoEntity.builder()
                .titulo(requestDTO.titulo())
                .descricao(requestDTO.descricao())
                .status(requestDTO.status())
                .prioridade(requestDTO.prioridade())
                .cliente(cliente)
                .tecnico(tecnico)
                .dataCriacao(requestDTO.dataCriacao())
                .build();
    }

    /**
     * Transforma uma entidade em uma response
     *
     * @param entity
     * @return
     */
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

    /**
     * Transforma uma lista de entidades em uma lista de responses
     *
     * @param entity
     * @return
     */
    public List<ChamadoResponseDTO> toResponseList (List<ChamadoEntity> entity){
        return entity.stream().map(this :: toResponse).toList();
    }

    /**
     * Atualiza os dados do chamado
     * @param entity
     * @param update
     * @return
     */
    public ChamadoEntity toUpdate(ChamadoEntity entity, ChamadoUpdateDTO update){
        if (update.status() != null){
            entity.setStatus(update.status());
        }

        return entity;
    }
}
