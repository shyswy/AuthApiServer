package com.example.clubsite.controller;

import com.example.clubsite.repository.ClubMemberRepository;
import com.example.clubsite.security.dto.ClubAuthMemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequestMapping("/data/")
@RequiredArgsConstructor
public class DataController {
    private final ClubMemberRepository clubMemberRepository;

    /*
    @GetMapping(value = "/name",produces = MediaType.APPLICATION_JSON_VALUE) //Note 번호를 PathVariable로 받으면
    public ResponseEntity<ClubAuthMemberDTO> giveName(@RequestParam()){

        log.info("-------------read-----------------------");
        log.info(num);
        clubMemberRepository.findByEmail()
        return new ResponseEntity<>(, HttpStatus.OK); //해당하는 번호의 NoteDTO 객체를 리턴해준다.
    }

     */



}
