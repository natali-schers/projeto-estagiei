package com.senacead.estagiei.repository;

import com.senacead.estagiei.model.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByDocumentoAndSenha(String documento, String senha);

    List<Usuario> findByTipo(String tipoUsuario);
        
    boolean existsByDocumento(String documento);
}
