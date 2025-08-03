package com.example.likelion13th.repository;

import com.example.likelion13th.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByName(String name);

    Optional<Member> findByEmail(String email);

    Page<Member> findByAgeGreaterThanEqual(int age, Pageable pageable);

    Page<Member> findByNameStartingWith(String prefix, Pageable pageable);
}
