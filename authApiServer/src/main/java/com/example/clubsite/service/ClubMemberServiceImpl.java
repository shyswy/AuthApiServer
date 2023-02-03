package com.example.clubsite.service;

import com.example.clubsite.dto.ClubMemberDTO;
import com.example.clubsite.entity.ClubMember;
import com.example.clubsite.repository.ClubMemberRepository;
import com.example.clubsite.security.dto.ClubAuthMemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ClubMemberServiceImpl implements ClubMemberService {

    private final ClubMemberRepository memberRepository;
    @Override
    public String register(ClubMemberDTO clubMemberDTO) {


        ClubMember clubMember=dtoToEntity(clubMemberDTO);
        memberRepository.save(clubMember);

        return clubMember.getEmail();


    }

    @Override
    public ClubMemberDTO get(String email,boolean fromSocial) {

        Optional<ClubMember> result = memberRepository.findByEmail(email,fromSocial);
        ClubMember clubMember=null;
        if(result.isPresent()){
            clubMember=result.get();
        }

        ClubMemberDTO clubMemberDTO = entityToDTO(clubMember);
        return clubMemberDTO;
    }

    @Override
    public void modify(ClubMemberDTO clubMemberDTO) {
        Optional<ClubMember> result = memberRepository.findByEmail(clubMemberDTO.getEmail(), clubMemberDTO.isFromSocial());
        if(result.isPresent()){
            ClubMember clubMember=result.get();
            clubMember.changeName(clubMemberDTO.getName());
            clubMember.changePassword(clubMemberDTO.getPassword());
            memberRepository.save(clubMember);
        }
    }

    @Override
    public void remove(String email,boolean fromSocial) {
        Optional<ClubMember> result = memberRepository.findByEmail(email, fromSocial);
        //P.K is Email, but we have OAuthUser, So delete with Entity
        if(result.isPresent()){
            ClubMember clubMember=result.get();
            memberRepository.delete(clubMember);
        }

    }
}
