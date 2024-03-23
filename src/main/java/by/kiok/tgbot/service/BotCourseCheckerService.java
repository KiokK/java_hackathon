package by.kiok.tgbot.service;

import by.kiok.tgbot.dto.request.UserDto;
import by.kiok.tgbot.dto.responce.CurrencyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BotCourseCheckerService {

    private final UserService userService;

    public void sendMessages(int modificationPercentage) {
        List<UserDto> users = userService.findAllByCoursePercentage(modificationPercentage);
        //TODO: sending

        return;
    }
}
