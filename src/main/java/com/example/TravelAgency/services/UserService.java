package com.example.TravelAgency.services;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.TravelAgency.dto.UserDto;
import com.example.TravelAgency.entities.User;
import com.example.TravelAgency.mapper.UserMapper;
import com.example.TravelAgency.repositories.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserService {
    private PasswordEncoder encoder;
    private UserRepository userRepository;
    private UserMapper userMapper;
    public UserDto save(UserDto userDto) {
        User user=userMapper.mapToEntity(userDto);
      //  user.setPassword(encoder.encode(userDto.getPassword()));
        String encodedPass = encoder.encode(userDto.getPassword());
        user.setPassword(encodedPass);

        User savedUser=userRepository.save(user);
        return userMapper.mapToDto(savedUser);

    }
    public List<UserDto> findAll() {
        List<User> userList = userRepository.findAll();

        return userList.stream().map(user -> userMapper.mapToDto(user)).collect(Collectors.toList());
    }
    public UserDto findById(long userId) {
        Optional<User> foundUser = userRepository.findById(userId);
        if (foundUser.isPresent()) {
            foundUser.get();
        }else throw  new RuntimeException("User with id: "+userId+" not found");

        return userMapper.mapToDto(foundUser.get());
    }
    public UserDto update(UserDto userDto, long userId) {
        User foundUser = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User with id: "+userId+"not found"));


        foundUser.setFullName(userDto.getFullName());
        foundUser.setUsername(userDto.getUsername());
        foundUser.setEmail(userDto.getEmail());
        foundUser.setPassword(encoder.encode(userDto.getPassword()));
        foundUser.setId(userDto.getId());

        User savedUser = userRepository.save(foundUser);

        return userMapper.mapToDto(savedUser);
    }

    public void delete(long userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User with id: "+userId+"not found"));
        userRepository.delete(existingUser);
    }



}


