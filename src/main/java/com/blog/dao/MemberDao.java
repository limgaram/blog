package com.blog.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.blog.dto.Member;

@Mapper
public interface MemberDao {

	Member getMemberLoginId(@Param("loginId") String loginId);

	void join(Map<String, Object> param);
}
