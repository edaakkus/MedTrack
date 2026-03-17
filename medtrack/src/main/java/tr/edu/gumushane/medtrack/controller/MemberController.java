package tr.edu.gumushane.medtrack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tr.edu.gumushane.medtrack.entity.Member;
import tr.edu.gumushane.medtrack.service.MemberService;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id);
    }

    @PostMapping
    public Member createMember(@RequestBody Member member) {
        return memberService.saveMember(member);
    }

    @PutMapping("/{id}")
    public Member updateMember(@PathVariable Long id, @RequestBody Member member) {
        member.setId(id);
        return memberService.saveMember(member);
    }

    @PatchMapping("/{id}/name")
    public Member patchMemberName(@PathVariable Long id, @RequestBody String name) {
        Member existing = memberService.getMemberById(id);
        existing.setName(name);
        return memberService.saveMember(existing);
    }

    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
    }
}

