package com.blog.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.dto.Article;
import com.blog.dto.ResultData;
import com.blog.service.ArticleService;
import com.blog.util.Util;

@Controller
public class MpaUsrArticleController {

	@Autowired
	private ArticleService articleService;

	@RequestMapping("/mpaUsr/article/list")
	public String showList(int boardId) {
		return "mpaUsr/article/list";
	}

	@RequestMapping("/mpaUsr/article/doWrite")
	@ResponseBody
	public ResultData doWrite(String title, String body) {
		if (Util.isEmpty(title)) {
			return new ResultData("F-1", "제목을 입력해주세요.");
		}

		if (Util.isEmpty(body)) {
			return new ResultData("F-2", "내용을 입력해주세요.");
		}

		return articleService.writeArticle(title, body);
	}

	@RequestMapping("/mpaUsr/article/getArticle")
	@ResponseBody
	public ResultData getArticle(Integer id) {
		if (Util.isEmpty(id)) {
			return new ResultData("F-1", "번호를 입력해주세요.");
		}

		Article article = articleService.getArticleById(id);

		if (article == null) {
			return new ResultData("F-1", id + "번 게시글이 존재하지 않습니다.", "id", id);
		}
		return new ResultData("S-1", article.getId() + "번 글입니다.", "article", article);
	}

	@RequestMapping("/mpaUsr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(Integer id) {
		if (Util.isEmpty(id)) {
			return new ResultData("F-1", "번호를 입력해주세요.");
		}

		return articleService.deleteArticleById(id);
	}

	@RequestMapping("/mpaUsr/article/doModify")
	@ResponseBody
	public ResultData doModify(Integer id, String title, String body) {
		if (Util.isEmpty(id)) {
			return new ResultData("F-1", "번호를 입력해주세요.");
		}

		if (Util.isEmpty(title)) {
			return new ResultData("F-2", "제목을 입력해주세요.");
		}

		if (Util.isEmpty(body)) {
			return new ResultData("F-3", "내용을 입력해주세요.");
		}

		Article article = articleService.getArticleById(id);

		if (article == null) {
			return new ResultData("F-4", "존재하지 않는 게시물 번호입니다.");
		}
		return articleService.modifyArticleById(id, title, body);
	}

}
