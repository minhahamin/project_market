package com.minha.mart.Service;


import com.minha.mart.DTO.MemberDTO;
import com.minha.mart.Repository.MemberRepository;
import com.minha.mart.Entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    public final MemberRepository memberRepository;

    public void join (MemberDTO memberDTO) throws ParseException {

        // 1. dto -> entity 변환
        // 2. repository 의 save 메소드 호출

        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);

        // repository 의 join 메소드 호출(조건 . entity 객체를 넘겨줘야 함)
    }

    public MemberDTO login(MemberDTO memberDTO){
                /*
                1. 회원이 입력한 이메일로 DB에서 조회를 함
                2. DB 에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단 */

        Optional<MemberEntity> byMemberId = memberRepository.findByUserid(memberDTO.getUserid());
        if(byMemberId.isPresent()){
            // 조회 결과가 있다(해당 아이디 정보를 가진 회원정보가 있다)
            MemberEntity memberEntity = byMemberId.get();
            if(memberEntity.getUserpw().equals(memberDTO.getUserpw())){
                // 비밀번호 일치
                // entity -> dto 변환 후 리턴
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;
            }
            else {
                // 비밀번호 불일치 (로그인 실패)
                return null;
            }
        } else {
            // 조회가 결과가 없다(해당 아이디를 가진 회원이 없다)
            return null;
        }

    }

    public MemberDTO findById(Long idx){
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(idx);
        return optionalMemberEntity.map(MemberDTO::toMemberDTO).orElse(null);
    }
    // 회원 ID로 회원 정보 조회
    public MemberEntity findByIdx(Long idx) {
        return memberRepository.findByIdx(idx);
    }

    public MemberDTO infoupdateForm(String userId){
        return memberRepository.findByUserid(userId)
                .map(MemberDTO::toMemberDTO)
                .orElse(null); // Return null or throw an exception if not found
    }


    public MemberEntity infoupdate (Long idx, MemberDTO memberDTO){
        MemberEntity memberEntity = memberRepository.findById(idx).orElseThrow();
        memberEntity.setUserid(memberDTO.getUserid());
        memberEntity.setUserpw(memberDTO.getUserpw());
        memberEntity.setUsername(memberDTO.getUsername());
        memberEntity.setAddress(memberDTO.getAddress());
        memberEntity.setPnum(memberDTO.getPnum());
        memberEntity.setEmail(memberDTO.getEmail());
        memberEntity.setBirth(memberDTO.getBirth());

        return memberRepository.save(memberEntity);
    }

    public MemberDTO findByUserid(String userid) {
        // 데이터베이스에서 사용자 ID로 MemberEntity 조회
        Optional<MemberEntity> memberEntityOptional = memberRepository.findByUserid(userid);

        if (memberEntityOptional.isPresent()) {
            // MemberEntity를 MemberDTO로 변환
            return MemberDTO.toMemberDTO(memberEntityOptional.get());
        } else {
            // 사용자를 찾을 수 없는 경우, null 반환 또는 예외 처리
            return null;
        }
    }

    public MemberEntity findMemberByUserId(String userid) {
        // userId를 사용하여 MemberEntity를 조회
        return memberRepository.findByUserid(userid)
                .orElseThrow(() -> new EntityNotFoundException("Member not found with userid: " + userid));
    }

}
