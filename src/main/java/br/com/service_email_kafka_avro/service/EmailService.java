package br.com.service_email_kafka_avro.service;

import br.com.service_email_kafka_avro.model.Carrinho;
import br.com.service_email_kafka_avro.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(int id, String email, String nome, String cpf, float totalPedido) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);

        msg.setSubject("Pedido Criado: Cód.: " + id);
        msg.setText(
                "Pedido realizado pelo cliente: "+ nome +
                "\n"+ "CPF: "+cpf+
                "\n"+ "Valor Total : R$"+totalPedido);

        javaMailSender.send(msg);

    }

    public boolean enviarEmail(Pedido pedido) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(pedido.getEmail());



        msg.setSubject("Pedido Criado: Cód.: " + pedido.getId());
        msg.setText(
                "Pedido realizado pelo cliente: "+ pedido.getEmail() +
                        "\n"+ "Status: " + pedido.getStatus() +
                        "\n"+ "Hora: " + pedido.getMomento() +
                        "\n"+ "Data da entrega: " + pedido.getDataEntrega() +
                        "\n"+ "Local da entrega: " + pedido.getLocalEntrega() +
                        "\n\n"+ "Pedidos:");

        for (Carrinho carrinho : pedido.getCarrinho()) {
            msg.setText(msg.getText() +
                    "\n"+ "Nome: " + carrinho.getNome() +
                    "\n"+ "Preco: " + carrinho.getPreco() +
                    "\n"+ "Modelo: " + carrinho.getModelo() +
                    "\n"+ "Quantidade: " + carrinho.getQuantidade() +
                    "\n"+ "Tamanho: " + carrinho.getTamanho() +
                    "\n"+ "----------------------");
        }
        try{
            javaMailSender.send(msg);
            return true;
        }catch(MailException e){
            e.getStackTrace();
        }
        return false;
    }
}
