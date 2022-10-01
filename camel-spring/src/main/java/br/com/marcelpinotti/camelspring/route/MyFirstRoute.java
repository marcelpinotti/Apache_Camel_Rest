package br.com.marcelpinotti.camelspring.route;

import br.com.marcelpinotti.camelspring.model.UserDTO;
import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
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
        rest("/api")
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .consumes(MediaType.APPLICATION_JSON_VALUE)

                // (Métodos e rotas do Controller)

                .get("/camel-users")
                .id("rest-camel-users") // id: Identificação da rota (funciona sem, no console aparece routeNº)
                .to("direct:rest-api-users") // direct: invocação direta e síncrona de qualquer consumidor
                // quando um produtor envia uma troca de mensagens

                .get("/camel-user/{id}")
                .id("rest-camel-user-id")
                .param() // Identifica que existe parâmetro
                .name("id") // Identifica qual é o parâmetro
                .type(RestParamType.path) // Identifica de onde vem o parâmetro
                .description("O id do usuário a ser obtido")
                .endParam()
                .to("direct:rest-api-user-id")

                .get("/camel-cep/{cep}")
                .id("rest-camel-viaCep")
                .param()
                .name("cep")
                .type(RestParamType.path)
                .description("O dados do cep são retornados")
                .endParam()
                .to("direct:rest-api-viaCep")

                .get("/camel-userViaCep/{id}")
                .id("rest-camel-userviaCep")
                .param()
                .name("id")
                .type(RestParamType.path)
                .description("Juntando dados do user e do cep retornados")
                .endParam()
                .to("direct:rest-api-user-viaCep");

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
