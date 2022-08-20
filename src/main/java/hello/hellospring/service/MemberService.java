package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;   // List 사용하려고
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /*
     *  회원가입
     */
    public Long join(Member member){
        validateDuplicateMember(member); // 중복 회원 검증  // Optional로 감싸기....?
        memberRepository.save(member);
        return member.getId();    // getId() 쓰려면 domain에 있는 Member 클래스 가져와야한다
    }

    private void validateDuplicateMember(Member member){
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("already here.");
                });
    }
    /*
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
