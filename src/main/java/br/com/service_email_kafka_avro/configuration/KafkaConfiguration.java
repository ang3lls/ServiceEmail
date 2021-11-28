package br.com.service_email_kafka_avro.configuration;

import br.com.service_email_kafka_avro.utils.Constants;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfiguration {

    private final KafkaProperties properties;

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String bootstrapServer;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Value("${spring.schema.registry.url}")
    private String schemaRegistryUrl;

    public KafkaConfiguration (final KafkaProperties properties) {
        this.properties = properties;
    }

    @Bean
    public ConsumerFactory<?, ?> kafkaConsumerFactory(KafkaProperties properties) {
        Map<String, Object> props = properties.buildConsumerProperties();

        // normal consumer
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServer);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, Constants.PARAM_FALSE);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, Constants.LATEST_CONFIG);

        // avro part (deserializer)
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class.getName());
        props.put(Constants.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);
        props.put(Constants.SPECIFIC_AVRO_READER_CONFIG, Constants.PARAM_TRUE);

        return new DefaultKafkaConsumerFactory<>(props);
    }
}
