package example.demo.repository;


import example.demo.domain.Member;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findByIdx(Long idx);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
