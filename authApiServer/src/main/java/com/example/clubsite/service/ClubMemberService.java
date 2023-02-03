package com.example.clubsite.service;

import com.example.clubsite.dto.ClubMemberDTO;
import com.example.clubsite.entity.ClubMember;
import com.example.clubsite.entity.ClubMemberRole;
import com.example.clubsite.security.dto.ClubAuthMemberDTO;

public interface ClubMemberService {



    String register(ClubMemberDTO clubAuthMemberDTO);

    ClubMemberDTO get(String email,boolean fromSocial);

    void modify(ClubMemberDTO clubMemberDTO);

    void remove(String email,boolean fromSocial);

    default ClubMember dtoToEntity(ClubMemberDTO clubMemberDTO){
        ClubMember clubMember= ClubMember.builder()
                .email(clubMemberDTO.getEmail())
                .password(clubMemberDTO.getPassword())
                .name(clubMemberDTO.getName())
                .fromSocial(clubMemberDTO.isFromSocial())
                .roleSet(clubMemberDTO.getRoleSet())
                .build();
        clubMember.addMemberRole(ClubMemberRole.USER);// 회원가입한 멤버는 기본 유저로 역할 설정
        return clubMember;
    }


    default ClubMemberDTO entityToDTO(ClubMember clubMember){

        ClubMemberDTO clubMemberDTO=ClubMemberDTO.builder()
                .email(clubMember.getEmail())
                .name(clubMember.getName())
                .password(clubMember.getPassword())
                .fromSocial(clubMember.isFromSocial())
                .roleSet(clubMember.getRoleSet())
                .build();
        return clubMemberDTO;

    }



}
