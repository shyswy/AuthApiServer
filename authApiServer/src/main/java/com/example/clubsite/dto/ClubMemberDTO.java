package com.example.clubsite.dto;

import com.example.clubsite.entity.ClubMemberRole;
import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClubMemberDTO {

    private String email;

    private String password;

    private String name;

    private boolean fromSocial;

    private Set<ClubMemberRole> roleSet;

}
