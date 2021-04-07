package com.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.dto.Article;
import com.blog.util.Util;

@Controller
public class AdmArticleController {

	private int articleLastId = 0;

	@RequestMapping("/adm/article/doWrite")
	@ResponseBody
	public Article doWrite(String title, String body) {
		int id = articleLastId + 1;
		String regDate = Util.getNowDateStr();
		String updateDate = Util.getNowDateStr();

		Article article = new Article(id, regDate, updateDate, title, body);

		articleLastId = id;

		return article;
	}
}
