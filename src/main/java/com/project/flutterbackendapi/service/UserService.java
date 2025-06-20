package com.project.flutterbackendapi.service;

import com.project.flutterbackendapi.common.exception.NotFoundException;
import com.project.flutterbackendapi.entity.User;
import com.project.flutterbackendapi.model.request.UserRequestDTO;
import com.project.flutterbackendapi.model.response.UserResponseDTO;
import com.project.flutterbackendapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public User getUserByUserAccount(String userAccount){
        return userRepository.findByUserAccount(userAccount).orElseThrow(() -> new NotFoundException("User not found with id: " + userAccount));
    }

    public UserResponseDTO registerUser(UserRequestDTO userRequestDTO) {

        log.info("Registering user with account: {}", userRequestDTO.getUserPassword());

        String encodedPassword = passwordEncoder.encode(userRequestDTO.getUserPassword());

        User user = User.createUser(userRequestDTO, encodedPassword);

        User savedUser = userRepository.save(user);

        return UserResponseDTO.builder()
                .id(savedUser.getId())
                .userName(savedUser.getUserName())
                .userAccount(savedUser.getUserAccount())
                .userType(savedUser.getUserType())
                .build();
    }
}
