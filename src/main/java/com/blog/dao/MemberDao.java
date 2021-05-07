package com.blog.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.blog.dto.Member;

@Mapper
public interface MemberDao {

	Member getMemberLoginId(@Param("loginId") String loginId);
}
