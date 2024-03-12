package com.example.demo.base.models;

public record CursorPageModel(String startCursor, String endCursor, Boolean hasNextPage, Boolean hasPreviousPage) {
}
