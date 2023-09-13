package ru.sber.contest.bracketscheckerservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sber.contest.bracketscheckerservice.api.request.TextCheckRequest;
import ru.sber.contest.bracketscheckerservice.api.response.TextCheckResponse;

import java.util.Stack;

@Service
@RequiredArgsConstructor
public class TextService {
    
    public TextCheckResponse checkBrackets(TextCheckRequest request) {
        Stack<Character> brackets = new Stack<>();
        Stack<Integer> flags = new Stack<>();
        char[] chars = request.getText().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                if (flags.isEmpty() || i - flags.peek() >= 2) {
                    brackets.push(chars[i]);
                    flags.push(i);
                } else {
                    return new TextCheckResponse(false);
                }
            } else if (chars[i] == ')') {
                if (!flags.isEmpty() && i - flags.peek() >= 2) {
                    brackets.pop();
                    flags.pop();
                } else {
                    return new TextCheckResponse(false);
                }
            }
        }
        return new TextCheckResponse(brackets.isEmpty() && flags.isEmpty());
    }
}
