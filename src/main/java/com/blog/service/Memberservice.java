package com.blog.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dao.MemberDao;
import com.blog.dto.Member;
import com.blog.dto.ResultData;
import com.blog.util.Util;

@Service
public class Memberservice {
	@Autowired
	private MemberDao memberDao;

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberLoginId(loginId);
	}

	public ResultData join(Map<String, Object> param) {
		memberDao.join(param);

		int id = Util.getAsInt(param.get("id"), 0);

		return new ResultData("S-1", String.format("%s님 환영합니다.", param.get("nickname")));

	}

}
