package ru.sber.contest.bracketscheckerservice.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TextCheckResponse {

    @NotNull
    @JsonProperty("isCorrect")
    private boolean isCorrect;
}
