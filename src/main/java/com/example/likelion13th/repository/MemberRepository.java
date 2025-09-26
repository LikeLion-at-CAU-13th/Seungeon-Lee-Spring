package com.example.likelion13th.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.likelion13th.domain.Member;
import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long>
{
    Optional<Member> findByName(String name);
    Optional<Member> findByEmail(String email);
    Page<Member> findByAgeGreaterThanEqualOrderByNameAsc(Integer age, Pageable pageable);
    Page<Member> findByNameStartingWithOrderByNameAsc(String prefix, Pageable pageable);
}
