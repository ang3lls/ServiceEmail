package br.com.service_email_kafka_avro.controller;


import br.com.service_email_kafka_avro.model.Pedido;
import br.com.service_email_kafka_avro.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/confirmacao")
public class PedidoController {

    @Autowired
    private EmailService es;

    @PostMapping("/email")
    private ResponseEntity<Void> enviarEmail(@RequestBody Pedido pedido) {
        boolean retorno = es.enviarEmail(pedido);
        return retorno ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
}
