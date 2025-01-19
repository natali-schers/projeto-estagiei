package com.senacead.estagiei.service;

import com.senacead.estagiei.model.Usuario;
import com.senacead.estagiei.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario login(String documento, String senha) {
        Optional<Usuario> usuario = usuarioRepository.findByDocumentoAndSenha(documento, senha);
        return usuario.orElse(null);
    }

    public boolean saveOrUpdateUsuario(Usuario usuario) {
        if (usuario.getId() == null) {
            if (usuarioRepository.existsByDocumento(usuario.getDocumento())) {
                return false;
            }
            usuario.setStatus(usuario.getTipo().contains("Aluno") ? "Em análise" : "Ativo");
        } else {
            try {
                Integer usuarioId = usuario.getId();
                Usuario usuarioExistente = usuarioRepository.findById(usuarioId)
                        .orElseThrow(() -> new IllegalArgumentException("Usuário com ID " + usuarioId + " não encontrado."));

                usuarioExistente.setNome(usuario.getNome());
                usuarioExistente.setDocumento(usuario.getDocumento());
                usuarioExistente.setEmail(usuario.getEmail());
                usuarioExistente.setEmailSecundario(usuario.getEmailSecundario());
                usuarioExistente.setTelefone(usuario.getTelefone());
                usuarioExistente.setTelefoneSecundario(usuario.getTelefoneSecundario());
                usuarioExistente.setEndereco(usuario.getEndereco());
                usuarioExistente.setSenha(usuario.getSenha());
                usuarioExistente.setTipo(usuario.getTipo());

                usuario = usuarioExistente;
            } catch (Exception e) {
                System.err.println("Erro ao atualizar usuário: " + e.getMessage());
                return false;
            }
        }

        usuarioRepository.save(usuario);
        return true;
    }

    public List<Usuario> findByTIpo(String tipoUsuario) {
        return usuarioRepository.findByTipo(tipoUsuario);
    }

    public Optional<Usuario> findById(Integer id) {
        return usuarioRepository.findById(id);
    }
}
