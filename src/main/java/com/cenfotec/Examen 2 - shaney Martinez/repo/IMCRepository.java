package com.cenfotec.deporte.repo;

import com.cenfotec.deporte.domain.Atleta;
import com.cenfotec.deporte.domain.IMC;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMCRepository extends JpaRepository<IMC, Long> {
    public List<IMC> findByAtleta(long ID);
}
