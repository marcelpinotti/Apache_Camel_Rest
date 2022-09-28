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

        from("direct:rest-api-user-id")
                .id("rest-api-user-id")
                .log("Inicializando Rota GET de User por id")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                //.setHeader(Exchange.HTTP_PATH, simple("${header.cep}"))
                .toD("http://viacep.com.br/ws/${header.cep}/json/")
                .unmarshal()
                .json(JsonLibrary.Jackson, ViaCepDTO.class)
                .process(exchange -> exchange.getIn().getBody(ViaCepDTO.class));

    }
}
