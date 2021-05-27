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

@Controller
@Slf4j
public class MpaUsrMemberController extends BaseController {
    @Autowired
    private Memberservice memberservice;

    @RequestMapping("/mpaUsr/member/join")
    public String showjoin(HttpServletRequest req) {
        return "mpaUsr/member/join";
    }

    @RequestMapping("/mpaUsr/member/doJoin")
    @ResponseBody
    public String doJoin(HttpServletRequest req, String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
        Member oldMember = memberservice.getMemberByLoginId(loginId);

        if (oldMember != null) {
            return msgAndBack(req, loginId + "(은)는 이미 사용중인 로그인아이디입니다.");
        }

        ResultData joinRd = memberservice.join(loginId, loginPw, name, nickname, cellphoneNo, email);

        if (joinRd.isFail()) {
            return msgAndBack(req, joinRd.getMsg());
        }

        return msgAndReplace(req, joinRd.getMsg(), "/");
    }


}

