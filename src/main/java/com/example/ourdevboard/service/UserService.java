package com.example.ourdevboard.service;

import com.example.ourdevboard.domain.dto.SignupRequestDto;
import com.example.ourdevboard.domain.user.User;
import com.example.ourdevboard.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public void registerUser(SignupRequestDto requestDto) {
        User user = new User(requestDto);
        userRepository.save(user);
    }

}
