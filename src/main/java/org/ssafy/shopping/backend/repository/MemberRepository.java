package org.ssafy.shopping.backend.repository;

import org.ssafy.shopping.backend.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findByMemberMailAndPassword(String email, String password);

    Member save(Member member);

    Member findByMemberMail(String email);
}