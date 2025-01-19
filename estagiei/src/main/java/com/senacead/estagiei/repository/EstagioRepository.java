package com.senacead.estagiei.repository;

import com.senacead.estagiei.model.Estagio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstagioRepository extends JpaRepository<Estagio, Integer> {
    public Estagio findByIdAluno (Integer idAluno);
}
