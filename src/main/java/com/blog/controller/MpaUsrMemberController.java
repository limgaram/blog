package com.blog.controller;

import com.blog.dto.Member;
import com.blog.dto.ResultData;
import com.blog.service.Memberservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class MpaUsrMemberController extends BaseController {
    @Autowired
    private Memberservice memberService;

    @RequestMapping("/mpaUsr/member/login")
    public String showLogin(HttpServletRequest ewq){
        return "mpaUsr/member/login";
    }

    @RequestMapping("mpaUsr/member/doLogin")
    public String doLogin(HttpServletRequest req, HttpSession session, String loginId, String loginPw, String redirectUrl){
        Member member = memberService.getMemberByLoginId(loginId);

        if(member == null){
            return msgAndBack(req, loginId + "(은)는 존재하지 않는 로그인아이디입니다.");
        }

        if(member.getLoginPw().equals(loginPw) == false){
            return msgAndBack(req, "비밀번호가 일치하지 않습니다.");
        }

        session.setAttribute("loginedMemberId", member.getId());

        String msg = "환영합니다.";

        return msgAndReplace(req, msg, redirectUrl);
    }

    @RequestMapping("/mpaUsr/member/join")
    public String showjoin(HttpServletRequest req) {
        return "mpaUsr/member/join";
    }

    @RequestMapping("/mpaUsr/member/doJoin")
    @ResponseBody
    public String doJoin(HttpServletRequest req, String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
        Member oldMember = memberService.getMemberByLoginId(loginId);

        if (oldMember != null) {
            return msgAndBack(req, loginId + "(은)는 이미 사용중인 로그인아이디입니다.");
        }

        ResultData joinRd = memberService.join(loginId, loginPw, name, nickname, cellphoneNo, email);

        if (joinRd.isFail()) {
            return msgAndBack(req, joinRd.getMsg());
        }

        return msgAndReplace(req, joinRd.getMsg(), "/");
    }


}

