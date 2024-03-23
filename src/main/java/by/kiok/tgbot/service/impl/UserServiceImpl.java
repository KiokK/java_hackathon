package by.kiok.tgbot.service.impl;

import by.kiok.tgbot.dto.request.UserDto;
import by.kiok.tgbot.dto.request.UserUpdateDto;
import by.kiok.tgbot.mapper.UserMapper;
import by.kiok.tgbot.model.User;
import by.kiok.tgbot.repository.UserRepository;
import by.kiok.tgbot.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Override
    public UserDto create(UserDto userDto) {
        User user = userMapper.userDtoToUser(userDto);

        return userRepository.create(user)
                .map(userMapper::userToUserDto)
                .orElseThrow();
    }

    @Override
    public UserDto findUserByUsername(String username) throws EntityNotFoundException {

        return userRepository.findUserByUsername(username)
                .map(userMapper::userToUserDto)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public List<UserDto> findAll() {

        return userRepository.findAll()
                .stream()
                .map(userMapper::userToUserDto)
                .toList();
    }

    @Override
    public List<UserDto> findAllByCoursePercentage(int coursePercentage) {

        return userRepository.findAllNotLessThatCoursePercentage(coursePercentage)
                .stream()
                .map(userMapper::userToUserDto)
                .toList();
    }

    @Override
    public UserDto updateByUsername(String username, UserUpdateDto userDto) throws EntityNotFoundException {
        User user = userMapper.userUpdateDtoToUser(userDto);

        return userRepository.updateByUsername(username, user)
                .map(userMapper::userToUserDto)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public boolean deleteByUsername(String username) {

        return userRepository.deleteByUsername(username);
    }
}
