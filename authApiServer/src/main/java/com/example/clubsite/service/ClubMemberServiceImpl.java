package com.example.clubsite.service;

import com.example.clubsite.dto.ClubMemberDTO;
import com.example.clubsite.entity.ClubMember;
import com.example.clubsite.repository.ClubMemberRepository;
import com.example.clubsite.security.dto.ClubAuthMemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;




@Service
@RequiredArgsConstructor
public class ClubMemberServiceImpl implements ClubMemberService {

    private final ClubMemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public String register(ClubMemberDTO clubMemberDTO) { //done
        Optional<ClubMember> result = memberRepository.findByEmail(clubMemberDTO.getId(), false);
        if(result.isPresent())
            throw new IllegalArgumentException("Failed: Already Registered!"); //이미 해당 유저 존재.

        clubMemberDTO.setName(clubMemberDTO.getId()); //이름은 디폴트로 id 값으로
        clubMemberDTO.setFromSocial(false);// 소셜 사용자는 자동 생성된다.
        clubMemberDTO.setPw(passwordEncoder.encode(clubMemberDTO.getPw())); //암호화된 비밀번호로 변경.
        ClubMember clubMember=dtoToEntity(clubMemberDTO);
        memberRepository.save(clubMember);

        return clubMember.getEmail();


    }

    @Override
    public ClubMemberDTO get(String email) {

        Optional<ClubMember> result = memberRepository.findOnlyByEmail(email);
        ClubMember clubMember=null;
        if(result.isPresent()){
            clubMember=result.get();
        }

        ClubMemberDTO clubMemberDTO = entityToDTO(clubMember);
        return clubMemberDTO;
    }

    @Override
    public void modify(ClubMemberDTO clubMemberDTO) {
        Optional<ClubMember> result = memberRepository.findByEmail(clubMemberDTO.getId(), clubMemberDTO.isFromSocial());
        if(result.isPresent()){
            ClubMember clubMember=result.get();
            clubMember.changeName(clubMemberDTO.getName());
            clubMember.changePassword(passwordEncoder.encode(clubMemberDTO.getPw()));

            memberRepository.save(clubMember);
        }
    }

    @Override
    public void remove(String email) {
        Optional<ClubMember> result = memberRepository.findOnlyByEmail(email);
        //P.K is Email, but we have OAuthUser, So delete with Entity
        if(result.isPresent()){
            ClubMember clubMember=result.get();
            memberRepository.delete(clubMember);
        }

    }
}
