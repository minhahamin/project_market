package com.minha.mart.Repository;

import com.minha.mart.Entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    // 아이디로 정보 조회 (select * from member where userid = ?);
    Optional<MemberEntity> findByUserid(String userid);
    MemberEntity findByIdx (Long idx);


}
