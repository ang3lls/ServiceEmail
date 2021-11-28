package br.com.service_email_kafka_avro.model;

import lombok.Data;

@Data
public class Carrinho {

    private Long id;

    private String nome;

    private Double preco;

    private String modelo;

    private Long quantidade;

    private TamanhoEnum tamanho;
}
