package br.prova.jwt.provajwt.repository;

import br.prova.jwt.provajwt.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Classe de persistencia da entidade usuario
 */

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    /**
     * Lista usuarios pelo seu username
     *
     * @param nome
     * @return
     */
    Optional<UsuarioEntity> findByNome(String nome);
}
