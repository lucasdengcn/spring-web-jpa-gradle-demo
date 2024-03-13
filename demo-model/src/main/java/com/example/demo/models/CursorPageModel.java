package com.example.demo.models;

public record CursorPageModel(String startCursor, String endCursor, Boolean hasNextPage, Boolean hasPreviousPage) {
}
