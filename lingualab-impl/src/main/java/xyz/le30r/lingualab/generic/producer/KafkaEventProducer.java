package xyz.le30r.lingualab.generic.producer;

import xyz.le30r.lingualab.generic.producer.message.KafkaEventMessage;

public interface KafkaEventProducer {
    void produce(KafkaEventMessage kafkaEventMessage);
}
