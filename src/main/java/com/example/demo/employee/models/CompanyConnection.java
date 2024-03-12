package com.example.demo.employee.models;

import com.example.demo.base.models.CursorPageModel;

import java.util.List;

public record CompanyConnection(List<CompanyEdge> edges, CursorPageModel pageInfo) {
}
