# Our DevBoard :computer:
> http://startspring.shop/ <br>
> 개발 관련 글들을 모아서 관리하는 게시판입니다. <br>
> [MyDevBoard](https://github.com/mangdo/myDevBoard) 프로젝트에서 기능을 추가해서 만들었습니다.
<br>

## 1. 개발 기간 & 참여 인원
- 2021년 7월 1일 ~ 2021년 7월 8일
- 개인 프로젝트

<br>

## 2. 사용 기술

`Back-end`
- Java 8
- SpringBoot 2.5.2
- SpringSecurity
- Junit 5
- Gradle 7.0.2
- JPA
- MySQL 8.0

`Front-end`
- JQuery 3.5.1
- Bulma 0.9.2

`deploy`
- AWS EC2(Ubuntu 18.04 LTS)
- AWS RDS(MySQL 8.0)

## 3. 주요 기능
- API 설계 : [WIKI](https://github.com/mangdo/ourDevBoard/wiki/API-%EC%84%A4%EA%B3%84-%F0%9F%94%A8)에서 확인
- 요구사항 명세 : [WIKI](https://github.com/mangdo/ourDevBoard/wiki/%EC%9A%94%EA%B5%AC%EC%82%AC%ED%95%AD-%EB%AA%85%EC%84%B8-%F0%9F%94%A8)에서 확인
#### **1. 회원가입**<br>
&nbsp; &nbsp; : 닉네임이 최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)가 아니라면 예외를 발생시킨다. <br>
&nbsp; &nbsp; : 이때 예외는 IllegalArgumentException을 상속받아 직접 만든 [SignupRequestException](https://github.com/mangdo/ourDevBoard/blob/87aaf336ec5466c95ba1a85e7159cc7700826eb1/src/main/java/com/example/ourdevboard/util/exception/SignupRequestException.java#L3)이다. <br>
&nbsp; &nbsp; : 발생한 SignupRequestException을 @ControllerAdvice를 이용해서 예외처리를 해준다. -> [해당 코드 보러가기](https://github.com/mangdo/ourDevBoard/blob/master/src/main/java/com/example/ourdevboard/util/exception/SignupExceptionHandler.java)<br>
  
#### **2. 로그인**<br>
&nbsp; &nbsp; : Spring Security(세션 방식)을 이용 <br>
  - 일반 로그인
  - 카카오 소셜로그인
    
#### **3. 댓글의 CRUD** <br>
  - 조회(Read) : 로그인 하지 않은 사용자도 댓글 목록 조회 가능하다.
    <details>
      <summary> 코드 보기 📌</summary>

      [WebSecurityConfig.java](https://github.com/mangdo/ourDevBoard/blob/master/src/main/java/com/example/ourdevboard/security/WebSecurityConfig.java)
      ```java
        .antMatchers("/", "/api/posts", "/api/posts/*", "/posts/detail*", "/user/login/forbidden",
                     "/user/signup", "/user/login", "/api/reply/post/*","/user/kakao/callback").permitAll()
      ```
    </details>

  - 작성(Create) : 로그인 한 사용자만 댓글 작성이 가능하다.
      <details>
      <summary> 코드 보기 📌</summary>

      [WebSecurityConfig.java](https://github.com/mangdo/ourDevBoard/blob/master/src/main/java/com/example/ourdevboard/security/WebSecurityConfig.java)
  
      ```java
        // 그 외 모든 요청은 인증과정 필요, 로그인 페이지로 리다이렉트된다
        .anyRequest().authenticated()
      ```
    </details>
  - 수정(Update) : 내가 작성한 댓글만 수정 가능하다.
      <details>
      <summary> 코드 보기 📌</summary>
  
      [ReplyService.java](https://github.com/mangdo/ourDevBoard/blob/master/src/main/java/com/example/ourdevboard/service/ReplyService.java)
  
      ```java
      // Update Reply By Id
      @Transactional
      public Long update (Long id, ReplyRequestDto requestDto, String userId){
          // 댓글이 있는지 확인
          Reply reply = replyRepository.findById(id).orElseThrow(
                  ()->new IllegalArgumentException("해당 댓글이 없습니다. id = "+id));

          // 댓글의 작성자와 현재 유저가 다르다면 수정 불가능
          if (!reply.getWriter().equals(userId)) {
              throw new IllegalArgumentException("자신이 쓴 댓글만 수정할 수 있습니다.");
          }
          // dirtychecking
          reply.update(requestDto);

          return id;
      }
    ```
    </details>

  - 삭제(Delete) : 내가 작성한 댓글만 삭제 가능하다.
      <details>
      <summary> 코드 보기 📌</summary>
  
      [ReplyService.java](https://github.com/mangdo/ourDevBoard/blob/master/src/main/java/com/example/ourdevboard/service/ReplyService.java)
      ```java
      // Delete Reply
      @Transactional
      public void delete(Long replyId, String userId){
          // 댓글이 있는지 확인
          Reply reply = replyRepository.findById(replyId).orElseThrow(
                  ()->new IllegalArgumentException("해당 게시물이 없습니다. id = "+replyId));

          // 댓글의 작성자와 현재 유저가 다르다면 삭제 불가능
          if (!reply.getWriter().equals(userId)) {
              throw new IllegalArgumentException("자신이 쓴 댓글만 삭제할 수 있습니다.");
          }
          replyRepository.deleteById(replyId);
      }
    ```
    </details>
