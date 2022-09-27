package br.com.marcelpinotti.camelspring.route;

import br.com.marcelpinotti.camelspring.model.UserDTO;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class MyFirstRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        //Configura o servidor, Host, porta e habilita o modo de ligação json
        restConfiguration()
                .component("servlet")
                .host("localhost")
                .port(8081)
                .bindingMode(RestBindingMode.auto);

        // Define os rest services (endpoints que serão usados no Controller)
        rest()
                .path("/api")
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .consumes(MediaType.APPLICATION_JSON_VALUE)
                // (Métodos e rotas do Controller)
                .get()
                .id("rest-camel-users") // id: Identificação da rota (funciona sem, no console aparece routeNº)
                .path("/camel-users")
                .to("direct:rest-api-users"); // direct: invocação direta e síncrona de qualquer consumidor
                // quando um produtor envia uma troca de mensagens

        // Define de onde os dados serão consumidos
        from("direct:rest-api-users")
                .id("rest-api-users")
                .log("Inicializando JsonView Api Route")
                .setHeader(Exchange.HTTP_METHOD, constant("GET")) // Definição do método de solicitação HTTP
                .to("http://localhost:3000/api/users") // Uri de consumo
                .unmarshal() // Faz a transformação de mensagens (transforma dados recebidos pela rede)
                .json(JsonLibrary.Jackson, UserDTO[].class) // Formato de conversão
                .process(exchange -> exchange.getIn().getBody(UserDTO[].class)); // É um nó capaz de usar, criar ou
                // modificar uma troca recebida.

    }
}
