package ru.sber.contest.bracketscheckerservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import ru.sber.contest.bracketscheckerservice.api.request.TextCheckRequest;
import ru.sber.contest.bracketscheckerservice.api.response.TextCheckResponse;

@Tag(name = "Help controller", description = "Получение доступных видов помощи")
public interface TextController {
    
    @Operation(summary = "checkBrackets", description = "Проверка текста на правильную расстановку скобок")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Проверка успешно завершена"),
            @ApiResponse(responseCode = "400", description = "Неверный запрос"),
    })
    ResponseEntity<TextCheckResponse> checkBrackets(@Parameter(description = "Запрос на проверку текста")
                                                    TextCheckRequest request);
}
