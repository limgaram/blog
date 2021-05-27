package com.blog.dao;

import com.blog.dto.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemberDao {

    Member getMemberLoginId(@Param("loginId") String loginId);

    void join(Map<String, Object> param);

    void join(@Param("loginId") String loginId, @Param("loginPw") String loginPw, @Param("name") String name, @Param("nickname") String nickname, @Param("cellphoneNo") String cellphoneNo, @Param("email") String email);

    int getLastInsertId();

    List<Member> getForPrintMembers(Map<String, Object> param);

    void modifyMember(Map<String, Object> param);
}
