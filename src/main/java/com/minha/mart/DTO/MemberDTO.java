package com.minha.mart.DTO;

import com.minha.mart.Entity.MemberEntity;
import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class MemberDTO {
    private long idx;
    private String userid;
    private String userpw;
    private String username;
    private String address;
    private String pnum;
    private String email;
    private Date birth;

    public static MemberDTO toMemberDTO (MemberEntity memberEntity){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setIdx(memberEntity.getIdx());
        memberDTO.setUserid(memberEntity.getUserid());
        memberDTO.setUserpw(memberEntity.getUserpw());
        memberDTO.setUsername(memberEntity.getUsername());
        memberDTO.setAddress(memberEntity.getAddress());
        memberDTO.setPnum(memberEntity.getPnum());
        memberDTO.setEmail(memberEntity.getEmail());
        memberDTO.setBirth(memberEntity.getBirth());

        return memberDTO;
    }
}
