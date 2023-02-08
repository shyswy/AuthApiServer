package com.example.clubsite.controller;

import com.example.clubsite.dto.ClubMemberDTO;
import com.example.clubsite.dto.NoteDTO;
import com.example.clubsite.entity.ClubMemberRole;
import com.example.clubsite.security.dto.ClubAuthMemberDTO;
import com.example.clubsite.service.ClubMemberService;
import com.example.clubsite.service.NoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("/user/")
@RequiredArgsConstructor
public class UserController {




    private final ClubMemberService clubMemberService;

    //@PreAuthorize("hasRole('USER')") //유저 "본인" 인 경우만

    //소셜 사용자는 로그인시 자동으로 db에 유저 데이터 생성 ( 비밀번호 1111 으로)



    @PostMapping(value = "/signup",produces = MediaType.APPLICATION_JSON_VALUE)  //따라서 여기서는 "로컬 로그인 유저" 만 처리한다.
    public ResponseEntity<String> register(@RequestBody ClubMemberDTO clubMemberDTO){ //Post방식으로 새로운 노트를 등록할 수 있는 기능
        //해당 정보를 기반으로 유저 생성.
        //id, pw 정보 들어온다 ->  ClubMemberDTO의 id, pw 값이 채워진다.
        log.info("--------------register--------------");

        //현재 비밀번호 암호화 x 다!!
        String status="";
        try {
            clubMemberService.register(clubMemberDTO); //register에서 dtoToEntity 작업으로 Role이 디폴트 User로 들어간다.

        }catch (IllegalArgumentException e){ //이미 존재하는 유저일시, 에러 메세지 Response
            status = "{\"status\":\"duplicate user\"}"; //json 타입 문자열
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);

        }
        status = "{\"status\":\"ok\"}"; //json 타입 문자열
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

   // @PreAuthorize("hasRole('USER')")

    //apiCHeckFIlter에서 id?= x    path로 온 x 와 토큰속 id 비교해서 같아야만 조회 성공
    @GetMapping(value="/read",produces = MediaType.APPLICATION_JSON_VALUE)
    //public ResponseEntity<ClubMemberDTO> read(@PathVariable("id") String id){
    public ResponseEntity<ClubMemberDTO> read(@RequestParam("id") String id){ //   notes/id : 종로 3가
        log.info("-------------read-----------------");
        log.info("id: "+id);

        ClubMemberDTO clubMemberDTO = clubMemberService.get(id);


        return new ResponseEntity<>(clubMemberDTO, HttpStatus.OK);
    }
    //@PreAuthorize("hasRole('USER')")

    //필터에서 유저 본인이 요청한 것인지 확인해야한다. signin 으로 로그인시 받은 토큰 속 id를 통해 제거.
    // or 확장성을 위해,   admin 권한 사용자 or 유저 본인이 요청할 시 제거
    @DeleteMapping(value="/remove",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> remove(@RequestParam("id") String id){
        log.info("-------------remove-----------------");
        log.info("id: "+id);
        clubMemberService.remove(id);
        String status="";

        status = "{\"status\":\"ok\"}"; //json 타입 문자열
        return new ResponseEntity<>(status, HttpStatus.OK);
    }


    /*
    PutMapping은 멱등성을 가지고, 여러 번 호출할 경우, 클라이언트가 받는 응답은 동일하다.

대상 리소스를 나타내는 데이터가 있는지 없는지 체크하여 없을 경우 Created(201) 응답을 보내고
대상 리소스를 나타내는 데이터가 있을 경우 OK(200), No Content(204) 응답을 통해 성공적으로
처리되었음을 알린다.
     */
    @PutMapping(value="/modify",produces = MediaType.TEXT_PLAIN_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> modify(@RequestParam("id") String id, @RequestBody ClubMemberDTO clubMemberDTO){
        log.info("modify Controller!!!");
        log.info(clubMemberDTO);
        String status="";
        //body속 DTO의 username과  uri 속의 username이 다른지 확인

        //토큰속 username과 DTO 속 username 비교는 Filter영역에서 수행함.
        if(!id.equals(clubMemberDTO.getId()))
            return new ResponseEntity<>("cannot modify other user's information",HttpStatus.BAD_REQUEST);

        clubMemberService.modify(clubMemberDTO);

        status = "{\"status\":\"ok\"}"; //json 타입 문자열
        return new ResponseEntity<>(status, HttpStatus.OK);

    }







}
