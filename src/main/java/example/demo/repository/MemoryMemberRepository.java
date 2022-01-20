package example.demo.repository;

import example.demo.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member){
        member.setIdx(++sequence);
        store.put(member.getIdx(), member);
        return member;
    }

    @Override
    public Optional<Member> findByIdx(Long idx) {
        return Optional.ofNullable(store.get(idx));
    }

    @Override
    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Member> findByName(String name){
        return store.values().stream().
                filter(member -> member.getName().equals(name))
                .findAny();
    }

    public void clearStore(){
        store.clear();
    }


}
