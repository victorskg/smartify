package com.github.victorskg.core.domain.event;

public interface DomainEventListener<T> {
    
    void handle(T object);

}