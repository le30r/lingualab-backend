package xyz.le30r.lingualab.auth.event.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import xyz.le30r.lingualab.auth.event.UserCreatedEvent;
import xyz.le30r.lingualab.event.EventData;
import xyz.le30r.lingualab.generic.producer.KafkaEventProducer;
import xyz.le30r.lingualab.generic.producer.message.KafkaEventMessage;
import xyz.le30r.lingualab.user.event.CreateUserEvent;
import xyz.le30r.lingualab.user.event.dto.CreateUserDto;

import java.time.Instant;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthEventListener {

    private final KafkaEventProducer kafkaEventProducer;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleAuthCreateEventListener(UserCreatedEvent userCreatedEvent) {
        var avroUserCreatedEvent = CreateUserEvent.newBuilder()
                .setEventData(EventData.newBuilder()
                        .setPublishedAt(Instant.now())
                        .build())
                .setEventPayload(CreateUserDto.newBuilder()
                        .setId(userCreatedEvent.userId())
                        .setInvitedBy(userCreatedEvent.teacherId())
                        .build())
                .build();
        kafkaEventProducer.produce(
                new KafkaEventMessage("auth", userCreatedEvent.userId(), avroUserCreatedEvent)
        );
        log.debug("User with id {} was created and sent to Kafka", userCreatedEvent.userId());
    }
}
