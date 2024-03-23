package by.kiok.tgbot.config;

import by.kiok.tgbot.TelegramBot;
import by.kiok.tgbot.facade.TelegramFacade;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

@Configuration
public class AppConfig {

    @Value("${telegrambot.webHookPath}")
    private String webHookPath;

    @Value("${telegrambot.userName}")
    private String userName;

    @Value("${telegrambot.botToken}")
    private String botToken;

    @Bean
    public SetWebhook setWebhookInstance() {
        return SetWebhook.builder().url(webHookPath).build();
    }

    @Bean
    public TelegramBot springWebhookBot(SetWebhook setWebhook, TelegramFacade telegramFacade) {
        TelegramBot bot = new TelegramBot(telegramFacade, setWebhook);
        bot.setBotToken(botToken);
        bot.setBotUsername(userName);
        bot.setBotPath(webHookPath);

        return bot;
    }

}
