package com.example.ourdevboard.domain.user;

import com.example.ourdevboard.domain.Timestamped;
import com.example.ourdevboard.domain.dto.SignupRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class User extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String passwordEncode;

    //@Column(nullable = false)
    //private String password;


    public User(SignupRequestDto signupRequestDto){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); //암호화

        this.username = signupRequestDto.getUsername();
        this.passwordEncode = encoder.encode(signupRequestDto.getPassword());
        // this.password = memberRequestDto.getPassword();
    }
}
