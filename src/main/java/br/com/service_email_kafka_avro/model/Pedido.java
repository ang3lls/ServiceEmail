package br.com.service_email_kafka_avro.model;

import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Pedido {

    private Long id;

    private StatusEnum status;

    private LocalDateTime momento;

    private LocalDate dataEntrega;

    private String localEntrega;

    private String email;

    private List<Carrinho> carrinho;
}
