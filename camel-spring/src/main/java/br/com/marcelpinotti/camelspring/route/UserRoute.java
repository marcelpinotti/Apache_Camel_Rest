package br.com.marcelpinotti.camelspring.route;

import br.com.marcelpinotti.camelspring.model.UserDTO;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

import org.springframework.stereotype.Component;

@Component
public class UserRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        // Define de onde os dados serão consumidos
        from("direct:rest-api-users")
                .id("rest-api-users")
                .log("Inicializando Rota GET de Users")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))// Definição do método de solicitação HTTP
                .to("http://localhost:3000/api/users") // Uri de consumo
                .unmarshal() // Faz a transformação de mensagens (transforma dados recebidos pela rede)
                .json(JsonLibrary.Jackson, UserDTO[].class) // Formato de conversão
                .process(exchange -> exchange.getIn().getBody(UserDTO[].class)); // É um nó capaz de usar, criar ou
                // modificar uma troca recebida.

        from("direct:rest-api-user-id")
                .id("rest-api-user-id")
                .log("Inicializando Rota GET de User por id")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .setHeader(Exchange.HTTP_PATH, simple("${header.id}")) // Informa e busca dados dinâmicos da url
                .to("http://localhost:3000/api/user/") // O DSL to (Url+${header.id}) também poderia ser sem o DSL setHeader acima
                .unmarshal()
                .json(JsonLibrary.Jackson, UserDTO.class)
                .process(exchange -> exchange.getIn().getBody(UserDTO.class));
    }
}
