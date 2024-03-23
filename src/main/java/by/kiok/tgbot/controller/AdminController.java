package by.kiok.tgbot.controller;

import by.kiok.tgbot.dto.request.CurrencyRequest;
import by.kiok.tgbot.service.BotCourseCheckerService;
import by.kiok.tgbot.service.CurrencyModifyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final CurrencyModifyService currencyModifyService;
    private final BotCourseCheckerService botCourseCheckerService;

    @PostMapping
    public ResponseEntity<Integer> createUser(@Valid @RequestBody CurrencyRequest currencyRequest) {
        int modificationPercentage = currencyModifyService.update(currencyRequest);
        botCourseCheckerService.sendMessages(modificationPercentage);

        return ResponseEntity.status(201)
                .body(modificationPercentage);
    }
}
