package com.example.clubsite.controller;


import com.example.clubsite.dto.NoteDTO;
import com.example.clubsite.repository.ClubMemberRepository;
import com.example.clubsite.security.dto.ClubAuthMemberDTO;
import com.example.clubsite.security.service.ClubOAuth2UserDetailsService;
import com.example.clubsite.security.service.ClubUserDetailsService;
import com.example.clubsite.service.NoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequestMapping("/auth/")
@RequiredArgsConstructor
public class AuthController {



/*
필터단에서 처리
기존: 해당 유저가 실제로 존재하는지 여부를 확인해야 하기에, 컨트롤러에서 리포지토리 뒤져보게 컨트롤러영역까지온것.

    // @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE) -> JSON 타입만 받아들인다.
    @GetMapping(value = "/verification",produces = MediaType.APPLICATION_JSON_VALUE) //JSON 타입으로 응답
   public ResponseEntity<String> read(@PathVariable("id") String id){
        log.info("-------------verification-----------------------");

        log.info("-------------read-----------------------");
        log.info(num);
        return new ResponseEntity<>(noteService.get(num),HttpStatus.OK); //해당하는 번호의 NoteDTO 객체를 리턴해준다.


        String status = "{\"status\":\"ok\"}"; //json 타입 문자열


        return new ResponseEntity<>(status, HttpStatus.OK); //해당하는 번호의 NoteDTO 객체를 리턴해준다.
    }

 */





}
