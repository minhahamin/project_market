package com.minha.mart.Entity;

import com.minha.mart.DTO.MemberDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


@Getter
@Setter
@Entity
@Table(name = "member")
public class MemberEntity {
    @Id     // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // 자동 idx 지정
    private Long idx;

    @Column(unique = true)      // unique 제약조건 추가
    private String userid;

    @Column
    private String userpw;
    private String username;
    private String address;
    private String pnum;
    private String email;
    private Date birth;


    public static MemberEntity toMemberEntity(MemberDTO memberDTO) throws ParseException{
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setUserid(memberDTO.getUserid());
        memberEntity.setUserpw(memberDTO.getUserpw());
        memberEntity.setUsername(memberDTO.getUsername());
        memberEntity.setAddress(memberDTO.getAddress());
        memberEntity.setPnum(memberDTO.getPnum());
        memberEntity.setEmail(memberDTO.getEmail());
        memberEntity.setBirth(memberDTO.getBirth());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date parsedDate = formatter.parse(memberDTO.getBirth().toString());
            java.sql.Date birthDate = new java.sql.Date(parsedDate.getTime());
            memberEntity.setBirth(birthDate);
        } catch (ParseException e) {
            e.printStackTrace();
            // 날짜 형식 파싱 오류 처리
        }


        return memberEntity;
    }
    public static MemberEntity toUpdateMemberEntity(MemberDTO memberDTO) throws ParseException{
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setIdx(memberDTO.getIdx()); // 이 부분 추가
        memberEntity.setUserid(memberDTO.getUserid());
        memberEntity.setUserpw(memberDTO.getUserpw());
        memberEntity.setUsername(memberDTO.getUsername());
        memberEntity.setAddress(memberDTO.getAddress());
        memberEntity.setPnum(memberDTO.getPnum());
        memberEntity.setEmail(memberDTO.getEmail());
        memberEntity.setBirth(memberDTO.getBirth());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date parsedDate = formatter.parse(memberDTO.getBirth().toString());
            java.sql.Date birthDate = new java.sql.Date(parsedDate.getTime());
            memberEntity.setBirth(birthDate);
        } catch (ParseException e) {
            e.printStackTrace();
            // 날짜 형식 파싱 오류 처리
        }


        return memberEntity;
    }
}
