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
public class AdmMemberController extends BaseController{
	@Autowired
	private Memberservice memberService;
	
	@RequestMapping("adm/member/join")
	public String showJoin() {
		return "adm/member/join";
	}
	
	@RequestMapping("adm/member/doJoin")
	@ResponseBody
	public String doJoin(@RequestParam Map<String, Object> param) {
		if(param.get("loginId") == null) {
			return Util.msgAndBack("loginId를 입력해주세요.");
		}
		
		Member exstingMember = memberService.getMemberByLoginId((String) param.get("loginId"));
		
		
		String msg = String.format(null, null);
		String redirectUrl = Util.ifEmpty(null, null);
		
	
	}
}
