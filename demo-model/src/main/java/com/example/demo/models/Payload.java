package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Payload<T> {
    T body;

    public Payload(T body) {
        this.body = body;
    }
}

