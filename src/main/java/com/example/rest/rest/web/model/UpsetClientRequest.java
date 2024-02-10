package com.example.rest.rest.web.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsetClientRequest {
    @NotBlank(message = "Client name should be filled")
    @Size(min = 3, max = 30, message = "Client name cant be lower than {min}, and not grater then {max}")
    private String name;
}
