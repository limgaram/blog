package com.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dao.BoardDao;
import com.blog.dto.Board;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;

	public Board getBoardById(int id) {
		return boardDao.getBoardById(id);
	}

}
