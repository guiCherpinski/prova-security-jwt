package br.prova.jwt.provajwt.repository;

import br.prova.jwt.provajwt.entity.ChamadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<ChamadoEntity, Long> {
}
