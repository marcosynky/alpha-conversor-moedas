package br.appLogin.appLogin.controller;

import br.appLogin.appLogin.model.Usuario;
import br.appLogin.appLogin.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository ur;

    @GetMapping("/login")
    public String login() {
        return "login"; // Página de login
    }

    @GetMapping("/home")
    public String home() {
        return "home"; // Retorna a página home.html
    }


    @PostMapping("/logar")
    public String loginUsuario(Usuario usuario, Model model, RedirectAttributes redirectAttributes) {
        Usuario userlogado = ur.login(usuario.getEmail(), usuario.getSenha());
        if (userlogado != null) {
            return "redirect:/home"; // Redireciona para a página home se o login for bem-sucedido
        } else {
            // Usando RedirectAttributes para passar a mensagem de erro
            redirectAttributes.addFlashAttribute("erro", "Email ou senha incorretos");
            return "redirect:/login"; // Redireciona para a página de login com a mensagem de erro
        }
    }


    @GetMapping("/cadastroUsuario")
    public String cadastro(Model model) {
        model.addAttribute("usuario", new Usuario()); // Cria um novo objeto 'Usuario' e passa para o template
        return "cadastro"; // Página de cadastro
    }

    @RequestMapping(value = "/cadastroUsuario", method = RequestMethod.POST)
    public String cadastroUsuario(@Valid Usuario usuario, BindingResult result, Model model) {
        // Verifica se houve erros de validação
        if (result.hasErrors()) {
            return "cadastro"; // Retorna para a página de cadastro em caso de erro
        }

        // Salva o usuário no banco de dados
        ur.save(usuario);
        return "redirect:/login"; // Redireciona para a página de login após o cadastro
    }
}


