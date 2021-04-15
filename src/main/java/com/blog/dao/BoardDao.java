package com.blog.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.blog.dto.Board;

@Mapper
public interface BoardDao {

	Board getBoardById(@Param("id") int id);

}
