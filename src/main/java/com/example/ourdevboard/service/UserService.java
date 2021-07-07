package com.example.ourdevboard.service;

import com.example.ourdevboard.domain.dto.KakaoSignupRequestDto;
import com.example.ourdevboard.domain.dto.SignupRequestDto;
import com.example.ourdevboard.domain.user.User;
import com.example.ourdevboard.domain.user.UserRepository;
import com.example.ourdevboard.security.kakao.KakaoOAuth2;
import com.example.ourdevboard.security.kakao.KakaoUserInfo;
import com.example.ourdevboard.util.exception.SignupRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final KakaoOAuth2 kakaoOAuth2;
    private final AuthenticationManager authenticationManager;

    private static final String RANDOM_TOKEN = "AAABnv/xRVklrnYxKZ0aHgfdfiddygoC";

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
    public void kakaoLogin(String authorizedCode) {
        // 카카오 OAuth2 를 통해 카카오 사용자 정보 조회
        KakaoUserInfo userInfo = kakaoOAuth2.getUserInfo(authorizedCode);
        Long kakaoId = userInfo.getId();
        String username = userInfo.getNickname();

        // DB 에 중복된 Kakao Id 가 있는지 확인
        // 이미 카카오 아이디를 가진 회원이있다면 그 회원으로 바로 로그인
        User kakaoUser = userRepository.findByKakaoId(kakaoId).orElse(null);

        // 사용자가 로그인을 일반로그인을 통해서 카카오회원으로 로그인이 되지 않도록하기위해서
        // 임의의 쓰레기 값(RANDOM_TOKEN)을 추가해준것이다.
        String password = kakaoId + RANDOM_TOKEN;

        // 카카오 아이디가 없다면, 일단 카카오 정보로 회원가입
        if (kakaoUser == null) {
            // password = 카카오 Id + ADMIN TOKEN
            KakaoSignupRequestDto requestDto = new KakaoSignupRequestDto(username, password, kakaoId);
            User user = new User(requestDto);
            userRepository.save(user);
        }

        Authentication kakaoUsernamePassword = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(kakaoUsernamePassword);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
