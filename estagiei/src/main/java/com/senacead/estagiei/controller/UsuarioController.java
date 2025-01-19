package com.senacead.estagiei.controller;

import com.senacead.estagiei.model.Estagio;
import com.senacead.estagiei.model.Usuario;
import com.senacead.estagiei.service.EstagioService;
import com.senacead.estagiei.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EstagioService estagioService;

    @GetMapping("/")
    public String exibirHome() {
        return "Usuario/Index";
    }

    @GetMapping("/login")
    public String exibirLogin() {
        return "Usuario/Login";
    }

    @PostMapping("/login")
    public String realizarLogin(@RequestParam("documento") String documento, @RequestParam("senha") String senha, HttpSession session, Model model) {
        Usuario usuario = usuarioService.login(documento, senha);

        if (usuario != null) {
            if ("Em análise".equals(usuario.getStatus())) {
                return "Usuario/AguardandoAprovacao";
            }

            session.setAttribute("usuarioLogado", usuario);
            return "redirect:/usuario/perfil";
        }

        model.addAttribute("erro", "Documento ou senha inválidos!");
        return "Usuario/Login";
    }

    @GetMapping("/cadastro")
    public String exibirCadastroUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "Usuario/DadosPessoais";
    }

    @PostMapping("/cadastro")
    public String salvarOuAtualizarUsuario(@ModelAttribute Usuario usuario, Model model) {
        boolean sucesso = usuarioService.saveOrUpdateUsuario(usuario);

        if (sucesso) {
            if (usuario.getTipo().contains("Aluno") && usuario.getStatus() == "Em análise") {
                return "redirect:/cadastro/estagio/" + usuario.getId();
            }
        }

        model.addAttribute("erro", "Erro ao salvar o usuário. Tente novamente!");
        return "Usuario/Perfil";
    }

    @GetMapping("/cadastro/estagio/{id}")
    public String exibirCadastroEstagio(@PathVariable int id, Model model) {
        Estagio estagio = new Estagio();
        estagio.setIdAluno(id);

        List<Usuario> empresas = usuarioService.findByTIpo("Empresa");
        List<Usuario> instituicoes = usuarioService.findByTIpo("InstituicaoEnsino");

        model.addAttribute("empresas", empresas);
        model.addAttribute("instituicoes", instituicoes);
        model.addAttribute("estagio", estagio);
        return "Usuario/Estagio";
    }

    @PostMapping("/cadastro/estagio")
    public String salvarEstagio(Estagio estagio) {
        estagioService.saveEstagio(estagio);
        return "Usuario/AguardandoAprovacao";
    }

    @GetMapping("/logout")
    public String realizarLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/usuario/perfil")
    public String exibirPerfilUsuario(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null) {
            return "redirect:/login";
        }

        Estagio estagio = null;
        if ("Aluno".equals(usuario.getTipo())) {
            estagio = estagioService.findByIdAluno(usuario.getId());
            if (estagio != null) {
                Optional<Usuario> empresa = usuarioService.findById(estagio.getIdEmpresa());
                Optional<Usuario> instituicaoEnsino = usuarioService.findById(estagio.getIdInstituicaoEnsino());

                model.addAttribute("empresa", empresa.orElse(null));
                model.addAttribute("instituicaoEnsino", instituicaoEnsino.orElse(null));
            }
        }

        model.addAttribute("usuario", usuario);
        return "Usuario/perfil";
    }

    @GetMapping("/usuario/editar/{id}")
    public String exibirEdicaoPerfil(@PathVariable Integer id, Model model) {
        Optional<Usuario> usuario = usuarioService.findById(id);
        model.addAttribute("usuario", usuario.orElse(null));
        return "Usuario/DadosPessoais";
    }
}
