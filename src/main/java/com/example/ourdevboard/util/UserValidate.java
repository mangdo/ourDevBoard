package com.example.ourdevboard.util;

import com.example.ourdevboard.domain.dto.SignupRequestDto;
import com.example.ourdevboard.util.exception.SignupRequestException;

import javax.transaction.Transactional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidate {

    public static void checkName(SignupRequestDto requestDto) {
        String pattern = "^(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{3,20}$"; // 영문, 숫자
        String username = requestDto.getUsername();
        if (username.isEmpty()) {
            throw new SignupRequestException("닉네임을 입력해 주세요.");
        }
        Matcher match = Pattern.compile(pattern).matcher(username);
        if (!match.find()) {
            throw new SignupRequestException("닉네임은 숫자와 영문자 조합으로 3~20자리를 사용해야 합니다.");
        }
    }


    /*
     * 	- 비밀번호는 `최소 4자 이상이며, 닉네임과 같은 값이 포함된 경우 회원가입에 실패`합니다.
     * - 비밀번호 확인은 비밀번호와 정확하게 일치해야 합니다.
     */
    @Transactional
    public static void checkPassword(SignupRequestDto requestDto) {
        //암호화 되지 않은 비밀번호로 비교
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        String passwordConfirm = requestDto.getPasswordConfirm();

        if (password.isEmpty() || passwordConfirm.isEmpty()) {
            throw new SignupRequestException("패스워드를 입력해 주세요.");
        }

        if (password.length() < 4 || password.length() > 20) {
            throw new SignupRequestException("비밀번호는  4~20자리를 사용해야 합니다.");
        }

        if (password.contains(username)) {
            throw new SignupRequestException("비밀번호에 닉네임을 포함할 수 없습니다.");
        }

        if (!password.equals(passwordConfirm)) {
            throw new SignupRequestException("패스워드가 일치하지 않습니다!");
        }
    }

}
