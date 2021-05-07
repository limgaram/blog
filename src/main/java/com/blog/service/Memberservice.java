package com.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dao.MemberDao;
import com.blog.dto.Member;

@Service
public class Memberservice {
	@Autowired
	private MemberDao memberDao;

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberLoginId(loginId);
	}

}
