package xyz.le30r.lingualab.generic.producer.message;

import lombok.Builder;
import lombok.Data;
import org.apache.avro.specific.SpecificRecord;

import java.util.Objects;

public record KafkaEventMessage(String topic, String key, SpecificRecord payload) {

    public KafkaEventMessage(String topic, Object key, SpecificRecord payload) {
        this(topic, Objects.toString(key, null), payload);
    }
}
