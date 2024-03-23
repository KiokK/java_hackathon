package by.kiok.tgbot.dto.request;

import jakarta.validation.constraints.Min;

public class PaginationInfo {

    @Min(1)
    private int pageSize;

    @Min(0)
    private int pageNumber;
}
