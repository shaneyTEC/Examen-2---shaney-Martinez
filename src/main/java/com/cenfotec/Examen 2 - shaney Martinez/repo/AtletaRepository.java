package com.cenfotec.deporte.repo;

import com.cenfotec.deporte.domain.Atleta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtletaRepository extends JpaRepository<Atleta, Long> {
    public List<Atleta> findByNombreContaining(String word);
}
