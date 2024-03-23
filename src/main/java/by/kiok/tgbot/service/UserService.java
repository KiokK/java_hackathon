package by.kiok.tgbot.service;

import by.kiok.tgbot.dto.request.UserDto;
import by.kiok.tgbot.dto.request.UserUpdateDto;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface UserService {

    UserDto create(UserDto userDto);

    UserDto findUserByUsername(String username) throws EntityNotFoundException;

    List<UserDto> findAll();

    List<UserDto> findAllByCoursePercentage(int coursePercentage);

    UserDto updateByUsername(String username, UserUpdateDto userDto) throws EntityNotFoundException;

    boolean deleteByUsername(String username);
}
