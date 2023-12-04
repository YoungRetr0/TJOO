package com.tjoo.tjoo.repository;

import com.tjoo.tjoo.domain.Solicitante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitanteRepository extends JpaRepository<Solicitante, Long> {
    Solicitante findByCpf(String cpf);

}
