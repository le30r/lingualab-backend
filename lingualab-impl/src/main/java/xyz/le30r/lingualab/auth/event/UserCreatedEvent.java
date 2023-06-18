package xyz.le30r.lingualab.auth.event;

import org.springframework.context.ApplicationEvent;

public record UserCreatedEvent(Long userId, Long teacherId) {
}
