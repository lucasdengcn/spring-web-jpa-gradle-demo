package com.example.demo.employee.apis.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CompanyCreateInput(
        @NotBlank(message = "name is empty")
        @Pattern(regexp = "[a-zA-Z\\d]{0,100}", message = "name format invalid")
        @Size(min = 5, max = 100, message = "name should contains 5 to 100 characters")
        String name)
{
}
