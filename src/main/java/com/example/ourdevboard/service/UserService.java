package com.example.ourdevboard.service;

import com.example.ourdevboard.domain.dto.SignupRequestDto;
import com.example.ourdevboard.domain.user.User;
import com.example.ourdevboard.domain.user.UserRepository;
import com.example.ourdevboard.util.exception.SignupRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void registerUser(SignupRequestDto requestDto) {
        checkNameDuplication(requestDto.getUsername());
        User user = new User(requestDto);
        userRepository.save(user);
    }

    public void checkNameDuplication(String userName){
        Optional<User> oldUser = userRepository.findByUsername(userName);
        if(oldUser.isPresent()) {
            throw new SignupRequestException("중복된 닉네임 입니다.");
        }

    }
}
