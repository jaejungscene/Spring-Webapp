package com.example.fastcampusmysql.application.controller;

import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import com.example.fastcampusmysql.domain.member.dto.MemberNicknameHistoryDto;
import com.example.fastcampusmysql.domain.member.dto.RegisterMemberCommand;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.service.MemberReadService;
import com.example.fastcampusmysql.domain.member.service.MemberWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemberController {
    final private MemberWriteService memberWriteService;
    final private MemberReadService memberReadService;


    @GetMapping("/members")
    public List<MemberDto> getAllMembers(){
        return memberReadService.getAllMembers();
    }
    @PostMapping("/members")
    public MemberDto register(@RequestBody RegisterMemberCommand command) {
        var member = memberWriteService.register(command);
        return memberReadService.toDto(member);
    }
    @GetMapping("/members/{id}")
    public MemberDto getMember(@PathVariable Long id) {

        return memberReadService.getMember(id);
    }
    @PostMapping("members/{id}/name")
    public MemberDto changeNickname(@PathVariable Long id, @RequestBody String nickname){
        memberWriteService.changeNickname(id, nickname);
        return memberReadService.getMember(id);
    }


    @GetMapping("nickname-histories")
    public List<MemberNicknameHistoryDto> getAllNickNameHistories(){
        return memberReadService.getAllNicknameHistories();
    }
    @GetMapping("/nickname-histories/{memberId}")
    public List<MemberNicknameHistoryDto> getNickNameHistories(@PathVariable Long memberId){
        return memberReadService.getNicknameHistories(memberId);
    }



    /**
     * Prac for comparsion between @PathVariable and @RequestParam
     */
//    @GetMapping("/member/{id}")
//    public String getNameOfMember(@PathVariable("id") Long id, @RequestParam(value = "attr", required = true) String attr){
//        MemberDto member = memberReadService.getMember(id);
//        switch(attr){
//            case "nickname":
//                attr = member.nickname();
//            case "email":
//                attr = member.email();
//        }
//        return attr;
//    }


}
