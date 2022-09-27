package br.com.marcelpinotti.camelspring.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

public class MyFirstRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        //Configura o servidor, Host, porta e habilita o modo de ligação json
        restConfiguration()
                .component("servlet")
                .host("localhost")
                .port(8081)
                .bindingMode(RestBindingMode.auto);

    }
}
