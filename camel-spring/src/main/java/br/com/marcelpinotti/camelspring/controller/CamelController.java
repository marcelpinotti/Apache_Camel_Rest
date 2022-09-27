package br.com.marcelpinotti.camelspring.controller;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.ExchangeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CamelController {

    //Permite enviar trocas de mensagens para endpoints
    @Autowired
    private final ProducerTemplate template;

    //É o contêiner do Camel em tempo de execução
    @Autowired
    private final CamelContext context;

    public CamelController(ProducerTemplate template, CamelContext context) {
        this.template = template;
        this.context = context;
    }

    //Primeiro endpoint
    @GetMapping("/camel-users")
    public Object getAll(){
        final Exchange response = ExchangeBuilder
                .anExchange(context)
                .build();
        return template.requestBody("direct:rest-api-users", response);
    }


}
