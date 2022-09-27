package br.com.marcelpinotti.camelspring.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.MediaType;

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

    }
}
