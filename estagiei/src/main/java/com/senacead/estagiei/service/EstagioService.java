package com.senacead.estagiei.service;

import com.senacead.estagiei.model.Estagio;
import com.senacead.estagiei.repository.EstagioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstagioService {

    @Autowired
    EstagioRepository estagioRepository;

    public boolean saveEstagio(Estagio estagio) {
        estagioRepository.save(estagio);
        return true;
    }
    
    public Estagio findByIdAluno(Integer idAluno) {
        return estagioRepository.findByIdAluno(idAluno);
    }
}
