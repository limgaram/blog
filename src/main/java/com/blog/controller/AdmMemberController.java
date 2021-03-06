package com.blog.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.dto.Member;
import com.blog.dto.ResultData;
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

	@RequestMapping("/adm/member/list")
	public String showList(HttpServletRequest req, @RequestParam(defaultValue = "1") int boardId,
			String searchKeywordType, String searchKeyword, @RequestParam(defaultValue = "1") int page,
			@RequestParam Map<String, Object> param) {
		if (searchKeywordType != null) {
			searchKeywordType = searchKeywordType.trim();
		}

		if (searchKeywordType == null || searchKeywordType.length() == 0) {
			searchKeywordType = "titleAndBody";
		}

		if (searchKeyword != null && searchKeyword.length() == 0) {
			searchKeywordType = null;
		}

		if (searchKeyword == null) {
			searchKeywordType = null;
		}

		int itemsInAPage = 20;

		List<Member> members = memberService.getForPrintMembers(searchKeywordType, searchKeyword, page, itemsInAPage,
				param);

		req.setAttribute("members", members);

		return "/adm/member/list";
	}

	@RequestMapping("/adm/member/login")
	public String showLogin() {
		return "adm/member/login";
	}

	@RequestMapping("/adm/member/doLogin")
	@ResponseBody
	public String doLogin(String loginId, String loginPw, String redirectUrl, HttpSession session) {
		if (loginId == null) {
			return Util.msgAndBack("loginId(을)를 입력해주세요.");
		}

		Member existingMember = memberService.getMemberByLoginId(loginId);

		if (existingMember == null) {
			return Util.msgAndBack("존재하지 않는 로그인아이디입니다.");
		}

		if (loginPw == null) {
			return Util.msgAndBack("loginPw(을)를 입력해주세요.");
		}

		if (existingMember.getLoginId().equals(loginPw) == false) {
			return Util.msgAndBack("비밀번호가 일치하지 않습니다.");
		}

		if (memberService.isAdmin(existingMember) == false) {
			return Util.msgAndBack("관리자만 접근할 수 있는 페이지입니다.");
		}

		session.setAttribute("loginedMemberId", existingMember.getId());

		String msg = String.format("%s님 환영합니다.", existingMember.getNickname());

		redirectUrl = Util.ifEmpty(redirectUrl, "../home/main");

		return Util.msgAndReplace(msg, redirectUrl);
	}

	@RequestMapping("adm/member/modify")
	public String showModify(Integer id, HttpServletRequest req) {
		if (id == null) {
			return msgAndBack(req, "id(을)를 입력해주세요.");
		}

		Member member = memberService.getForPrintMember(id);

		req.setAttribute("member", member);

		if (member == null) {
			return msgAndBack(req, "존재하지 않는 회원번호입니다.");
		}

		return "adm/member/modify";
	}

	@RequestMapping("adm/member/doModify")
	public String doModify(@RequestParam Map<String, Object> param, HttpServletRequest req) {
		int loginedMemberId = (int) req.getAttribute("loginedMemberId");
		param.put("id", loginedMemberId);

		ResultData modifyMemberRd = memberService.modifyMember(param);
		String redirectUrl = "/adm/member/list";

		return Util.msgAndReplace(modifyMemberRd.getMsg(), redirectUrl);

	}
	
	@RequestMapping("/adm/member/doLogout")
	@ResponseBody
	public String doLogout(HttpSession session) {
		session.removeAttribute("loginedMemberId");
		
		return Util.msgAndReplace("로그아웃 되었습니다.", "../member/login");
	}
	

}
