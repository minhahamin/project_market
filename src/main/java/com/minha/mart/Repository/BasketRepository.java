package com.minha.mart.Repository;

import com.minha.mart.DTO.BasketDTO;
import com.minha.mart.Entity.BasketEntity;
import com.minha.mart.Entity.MemberEntity;
import com.minha.mart.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface BasketRepository extends JpaRepository<BasketEntity, Long> {
    List<BasketEntity> findByMemberUserid(String userid);
    Set<BasketEntity> findByMember_Userid(String userid);

    Optional<BasketEntity> findByMemberAndProduct(MemberEntity member, ProductEntity product);

    // 추가: 특정 idx에 해당하는 장바구니 항목 삭제
    void deleteById(Long idx);


    // 추가: 여러 idx에 해당하는 장바구니 항목 삭제
    @Modifying
    @Transactional
    @Query("DELETE FROM BasketEntity b WHERE b.idx IN :itemIndices")
    void deleteByIdIn(@Param("itemIndices") List<Long> itemIndices);

}
