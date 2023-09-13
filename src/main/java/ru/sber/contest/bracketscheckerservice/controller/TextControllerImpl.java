package ru.sber.contest.bracketscheckerservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sber.contest.bracketscheckerservice.api.request.TextCheckRequest;
import ru.sber.contest.bracketscheckerservice.api.response.TextCheckResponse;
import ru.sber.contest.bracketscheckerservice.service.TextService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TextControllerImpl implements TextController {
    private final TextService textService;
    
    @PostMapping("/checkBrackets")
    public ResponseEntity<TextCheckResponse> checkBrackets(@RequestBody @Valid TextCheckRequest request) {
        TextCheckResponse response = textService.checkBrackets(request);
        return ResponseEntity.ok(response);
    }
}
