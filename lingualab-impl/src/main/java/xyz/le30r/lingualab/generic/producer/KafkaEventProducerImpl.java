package xyz.le30r.lingualab.generic.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import xyz.le30r.lingualab.generic.producer.message.KafkaEventMessage;

@RequiredArgsConstructor
@Slf4j
@Component
public class KafkaEventProducerImpl implements KafkaEventProducer{

    private final KafkaTemplate<String, SpecificRecord> kafkaTemplate;
    @Override
    public void produce(KafkaEventMessage kafkaEventMessage) {
        String topic = kafkaEventMessage.topic();
        log.debug("Start messaging to {}", topic);
        kafkaTemplate.send(topic, kafkaEventMessage.key(), kafkaEventMessage.payload()).toCompletableFuture().join();
        log.debug("End messaging to {}", topic);
    }
}
