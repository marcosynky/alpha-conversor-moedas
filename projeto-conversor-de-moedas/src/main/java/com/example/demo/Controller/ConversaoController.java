package com.example.demo.Controller;


import com.example.demo.service.ConversaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConversaoController {

    @Autowired
    private ConversaoService conversaoService;

    // Endpoint para exibir a página inicial
    @GetMapping("/")
    public String exibirPaginaInicial() {
        return "index";  // Retorna o template index.html
    }

    // Endpoint para converter as moedas e mostrar o resultado na página
    @GetMapping("/converter")
    public String converter(
            @RequestParam String moedaOrigem,
            @RequestParam double valor,
            @RequestParam String moedaDestino,
            Model model
    ) {
        double valorConvertido = conversaoService.converterMoeda(moedaOrigem, valor, moedaDestino);
        String resultado = String.format("O valor de %.2f %s convertido para %s é %.2f", valor, moedaOrigem, moedaDestino, valorConvertido);

        // Passando o resultado para o template
        model.addAttribute("resultado", resultado);

        return "index";  // Retorna o template index.html com o resultado
    }
}
