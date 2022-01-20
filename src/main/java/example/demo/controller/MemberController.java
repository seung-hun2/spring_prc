package example.demo.controller;

import example.demo.domain.Member;
import example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping(value = "/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping(value = "/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());
        member.setId(form.getId());
        member.setPassword(form.getPassword());

        memberService.join(member);

        return "redirect:/";
    }
    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
    @GetMapping(value = "/login")
    public String login(Model model) {
        return "login";
    }
    @PostMapping(value = "/login")
    public String afterlogin(MemberForm form){
        Member member = new Member();
        member.setId(form.getId());
        member.setPassword(form.getPassword());

        memberService.login(member);
        // 리턴부분 바꿔줘야함
        // 로그인되면 -> 홈 화면 후 ""님 환영합니다, 홈화면으로 이동 후 로그아웃 페이지가 로그인 대체
        // 로그인이 되지 않으면 -> 아이디 or 비밀번호가 틀렸습니다 출력 후 재실행
//        if(로그인되지않으면)
//            return "/login";
//        else
//            return "logged_in";
        return "redirect:/";
    }
}
