package com.blog.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.dto.Member;
import com.blog.service.Memberservice;
import com.blog.util.Util;

@Controller
public class AdmMemberController extends BaseController {
	@Autowired
	private Memberservice memberService;

	@RequestMapping("adm/member/join")
	public String showJoin() {
		return "adm/member/join";
	}

	@RequestMapping("adm/member/doJoin")
	@ResponseBody
	public String doJoin(@RequestParam Map<String, Object> param) {
		if (param.get("loginId") == null) {
			return Util.msgAndBack("loginId(을)를 입력해주세요.");
		}

		Member existingMember = memberService.getMemberByLoginId((String) param.get("loginId"));

		if (existingMember != null) {
			return Util.msgAndBack("이미 사용중인 로그인아이디입니다.");
		}

		if (param.get("loginPw") == null) {
			return Util.msgAndBack("loginPw(을)를 입력해주세요. ");
		}

		if (param.get("name") == null) {
			return Util.msgAndBack("name(을)를 입력해주세요. ");
		}

		if (param.get("nickname") == null) {
			return Util.msgAndBack("nickname(을)를 입력해주세요. ");
		}

		if (param.get("email") == null) {
			return Util.msgAndBack("email(을)를 입력해주세요. ");
		}

		if (param.get("cellphoneNo") == null) {
			return Util.msgAndBack("cellphoneNo(을)를 입력해주세요. ");
		}

		memberService.join(param);

		String msg = String.format("%s님 환영합니다.", param.get("nickname"));
		String redirectUrl = Util.ifEmpty((String) param.get("redirectUrl"), "../member/login");
		
		return Util.msgAndReplace(msg, redirectUrl);

	}
}
