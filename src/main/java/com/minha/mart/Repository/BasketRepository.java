package com.minha.mart.Repository;

import com.minha.mart.DTO.BasketDTO;
import com.minha.mart.Entity.BasketEntity;
import com.minha.mart.Entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasketRepository extends JpaRepository<BasketEntity, Long> {
  /*  List<BasketEntity> findByUserid(String userid); // userId에 따른 장바구니 목록 조회*/

    // userid 필드 대신 Member 엔티티의 userid를 참조하는 쿼리 메서드
    List<BasketEntity> findByMember_Userid(String userid);
}
