package com.example.clubsite.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude ={ "roleSet","notes"} ) //해당 클래스의 모든 멤버 변수를 출력한다. >> Member객체인 Writer에 Lazy적용했기에 제외한다.

public class ClubMember extends BaseEntity {

    @Id
    private String email;

    private String password;

    private String name;

    private boolean fromSocial;




   // @OneToMany(mappedBy = "writer",orphanRemoval = true) //orphanRemoval = true >> classes 삭제시 관련된 takeclasses 들도 삭제된다.
    //private List<Note> notes;





    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<ClubMemberRole> roleSet = new HashSet<>();

    public void addMemberRole(ClubMemberRole clubMemberRole){
        roleSet.clear();
        roleSet.add(clubMemberRole);
    }


    public void changePassword(String password){
        this.password=password;
    }

    public void changeName(String name){
        this.name=name;
    }



}
