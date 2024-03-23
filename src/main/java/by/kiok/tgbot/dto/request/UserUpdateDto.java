package by.kiok.tgbot.dto.request;

import jakarta.validation.constraints.Min;

public class UserUpdateDto {

    public String username;

    @Min(1)
    public int coursePercentage;
}
