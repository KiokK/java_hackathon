package by.kiok.tgbot.mapper;

import by.kiok.tgbot.dto.request.UserDto;
import by.kiok.tgbot.dto.request.UserUpdateDto;
import by.kiok.tgbot.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User userDtoToUser(UserDto userDto);

    User userUpdateDtoToUser(UserUpdateDto userDto);

    UserDto userToUserDto(User user);

}
