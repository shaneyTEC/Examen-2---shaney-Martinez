package com.cenfotec.deporte.services;

import com.cenfotec.deporte.domain.IMC;
import com.cenfotec.deporte.repo.IMCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IMCServiceImpl implements IMCService{

    @Autowired
    IMCRepository repo;

    @Override
    public void save(IMC imc) {
        repo.save(imc);
    }

    @Override
    public Optional<IMC> get(Long id) {
        return Optional.empty();
    }

    @Override
    public List<IMC> getAll() {
        return repo.findAll();
    }

    @Override
    public List<IMC> findByAtleta(long ID) {
        return repo.findByAtleta(ID);
    }
}
