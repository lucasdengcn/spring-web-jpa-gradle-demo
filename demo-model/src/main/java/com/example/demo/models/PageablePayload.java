package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
public class PageablePayload<T> {
    T body;
    PageModel page;

    public PageablePayload(T body, PageModel page) {
        this.body = body;
        this.page = page;
    }
}

