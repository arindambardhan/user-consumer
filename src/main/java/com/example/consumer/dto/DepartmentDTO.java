package com.example.consumer.dto;

import java.util.List;

public record DepartmentDTO(int id, String name, List<CourseDTO> courses) {}
