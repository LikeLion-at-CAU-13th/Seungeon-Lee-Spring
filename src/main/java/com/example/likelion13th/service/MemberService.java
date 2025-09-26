package com.example.likelion13th.service;

import com.example.likelion13th.domain.Member;
import com.example.likelion13th.dto.request.JoinRequestDto;
import com.example.likelion13th.exception.MemberAlreadyExistsException;
import com.example.likelion13th.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    // 비밀번호 인코더 DI(생성자 주입)
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void join(JoinRequestDto joinRequestDto) {
        // 해당 name이 이미 존재하는 경우
        if (memberRepository.existsByName(joinRequestDto.getName())){
            System.out.println("들어왔다!!!!!!!!!");
            throw new MemberAlreadyExistsException("이미 존재하는 사용자 입니다.");
        }

        // 유저 객체 생성
        Member member = joinRequestDto.toEntity(bCryptPasswordEncoder);

        // 유저 정보 저장
        memberRepository.save(member);
    }
}
