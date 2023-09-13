package ru.sber.contest.bracketscheckerservice.api.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TextCheckRequest {
    
    @NotBlank(message = "Text is required")
    @Size(min = 1, max = 2000, message = "Text must be between 1 and 2000 characters")
    private String text;
}
