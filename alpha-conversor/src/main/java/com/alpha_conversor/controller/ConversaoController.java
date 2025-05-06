package com.alpha_conversor.controller;

import com.alpha_conversor.service.ConversaoService;
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
            @RequestParam(name = "moedaOrigem", required = false) String moedaOrigem,
            @RequestParam(name = "valor", required = false) Double valor,
            @RequestParam(name = "moedaDestino", required = false) String moedaDestino,
            Model model
    ) {
        // Verificação básica de parâmetros
        if (moedaOrigem == null || moedaOrigem.isEmpty() || moedaDestino == null || moedaDestino.isEmpty() || valor == null || valor <= 0) {
            model.addAttribute("erro", "Por favor, preencha todos os campos corretamente.");
            return "index";  // Retorna o template com a mensagem de erro
        }

        try {
            // Realizando a conversão
            double valorConvertido = conversaoService.converterMoeda(moedaOrigem, valor, moedaDestino);
            String resultado = String.format("O valor de %.2f %s convertido para %s é %.2f", valor, moedaOrigem, moedaDestino, valorConvertido);

            // Passando o resultado para o template
            model.addAttribute("resultado", resultado);
        } catch (Exception e) {
            // Caso ocorra algum erro na conversão
            model.addAttribute("erro", "Erro ao realizar a conversão. Tente novamente mais tarde.");
        }

        return "index";  // Retorna o template index.html com o resultado ou erro
    }
}