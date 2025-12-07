package se.sprinto.hakan.springboot2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostRequestDTO(
        @NotBlank(message = "Text får inte vara tom.")
        @Size(min = 3, max = 500, message = "Text måste vara mellan 3 och 500 tecken.")
        String text

) {
}

