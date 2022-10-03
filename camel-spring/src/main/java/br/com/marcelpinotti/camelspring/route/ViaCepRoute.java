package br.com.marcelpinotti.camelspring.route;

import br.com.marcelpinotti.camelspring.model.ViaCepDTO;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class ViaCepRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("direct:rest-api-viaCep")
                .id("rest-api-viaCep")
                .log("Inicializando Rota GET do ViaCep")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .toD("http://viacep.com.br/ws/${header.cep}/json/")
                .unmarshal()
                .json(JsonLibrary.Jackson, ViaCepDTO.class)
                .process(exchange -> exchange.getIn().getBody(ViaCepDTO.class));
    }
}
