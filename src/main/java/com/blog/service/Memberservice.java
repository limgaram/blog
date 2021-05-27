package com.blog.service;

import com.blog.dao.MemberDao;
import com.blog.dto.Member;
import com.blog.dto.ResultData;
import com.blog.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Memberservice {
	@Autowired
	private MemberDao memberDao;

	// static st
	public static String getAuthLevelName(Member member) {
		switch (member.getAuthLevel()) {
		case 1:
			return "관리자";
		case 3:
			return "일반";
		default:
			return "유형정보없음";
		}
	}

	public static String getAuthLevelNameColor(Member member) {
		switch (member.getAuthLevel()) {
		case 1:
			return "red";
		case 3:
			return "gray";
		default:
			return "";
		}
	}
	// static end

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberLoginId(loginId);
	}

	public ResultData join(Map<String, Object> param) {
		memberDao.join(param);

		int id = Util.getAsInt(param.get("id"), 0);

		return new ResultData("S-1", String.format("%s님 환영합니다.", param.get("nickname")));

	}

	public ResultData join(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
		memberDao.join(loginId, loginPw, name, nickname, cellphoneNo, email);
		int id = memberDao.getLastInsertId();

		return new ResultData("S-1", "회원가입이 완료되었습니다.", "id", id);
	}

	public List<Member> getForPrintMembers(String searchKeywordType, String searchKeyword, int page, int itemsInAPage,
			Map<String, Object> param) {
		int limitStart = (page - 1) * itemsInAPage;
		int limitTake = itemsInAPage;

		param.put("searchKeywordType", searchKeywordType);
		param.put("searchKeyword", searchKeyword);
		param.put("limitStart", limitStart);
		param.put("limitTake", limitTake);

		return memberDao.getForPrintMembers(param);
	}

	public boolean isAdmin(Member actor) {
		return actor.getAuthLevel() == 1;
	}

	public Member getForPrintMember(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultData modifyMember(Map<String, Object> param) {
		memberDao.modifyMember(param);
		
		return new ResultData("S-1", "회원정보가 수정되었습니다.");
	}

	

}
