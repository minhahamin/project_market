package com.minha.mart.Repository;

import com.minha.mart.DTO.BasketDTO;
import com.minha.mart.Entity.BasketEntity;
import com.minha.mart.Entity.MemberEntity;
import com.minha.mart.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface BasketRepository extends JpaRepository<BasketEntity, Long> {
    List<BasketEntity> findByMemberUserid(String userid);
    Set<BasketEntity> findByMember_Userid(String userid);
}
