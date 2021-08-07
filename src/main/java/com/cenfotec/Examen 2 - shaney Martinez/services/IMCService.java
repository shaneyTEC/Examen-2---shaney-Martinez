package com.cenfotec.deporte.services;

import com.cenfotec.deporte.domain.IMC;

import java.util.List;
import java.util.Optional;

public interface IMCService {
    public void save(IMC atleta);
    public Optional<IMC> get(Long id);
    public List<IMC> getAll();
    public List<IMC> findByAtleta(long ID);
}
