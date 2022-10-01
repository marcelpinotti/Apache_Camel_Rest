package br.com.marcelpinotti.camelspring.route;

import br.com.marcelpinotti.camelspring.model.UserDTO;
import br.com.marcelpinotti.camelspring.model.UserViaCepDTO;
import br.com.marcelpinotti.camelspring.model.ViaCepDTO;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
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

        
        from("direct:rest-api-user-viaCep")
                .id("rest-api-user-viaCep")
                .log("Inicializando Rota GET de User com Cep")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .setHeader(Exchange.HTTP_PATH, simple("${header.id}"))
                .to("http://localhost:3000/api/user/")
                .unmarshal()
                .json(JsonLibrary.Jackson, UserDTO.class)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        UserDTO user = exchange.getIn().getBody(UserDTO.class);
                        ViaCepDTO viaCepDTO = new ViaCepDTO();
                        viaCepDTO.setCep(user.getCep());

                        UserViaCepDTO userViaCepDTO = new UserViaCepDTO(user.getId(), user.getName(), user.getLastname(),
                                user.getEmail(), user.getPassword(), viaCepDTO);

                        String cep = user.getCep();

                        exchange.getIn().setBody(userViaCepDTO);
                        exchange.getIn().setHeader("cep", cep);
                    }
                })
                .enrich("direct:rest-api-viaCep", new AggregationStrategy() {
                    @Override // AggregationStrategy() -> Agrega dados das RestApis que não se conhecem.
                    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {

                        UserViaCepDTO userCep = oldExchange.getIn().getBody(UserViaCepDTO.class);
                        ViaCepDTO viaCepDTO = newExchange.getIn().getBody(ViaCepDTO.class);

                        userCep.setEndereco(viaCepDTO);

                        newExchange.getIn().setBody(userCep);

                        return newExchange;
                    }
                });

    }
}
