package com.blog.service;

import com.blog.dao.ArticleDao;
import com.blog.dto.Article;
import com.blog.dto.Board;
import com.blog.dto.Member;
import com.blog.dto.ResultData;
import com.blog.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ArticleService {

	@Autowired
	private ArticleDao articleDao;

	public ResultData writeArticle(int boardId, int memberId, String title, String body) {
		articleDao.writeArticle(boardId, memberId, title, body);
		int id = articleDao.getLastInsertId();

		return new ResultData("S-1", "게시물이 작성되었습니다.", "id", id);
	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}

	public ResultData deleteArticleById(int id) {
		Article article = getArticleById(id);

		if (isEmpty(article)) {
			return new ResultData("F-1", "존재하지 않는 게시물 번호입니다.", "id", id);
		}

		articleDao.deleteArticleById(id);

		return new ResultData("S-1", id + "번 게시물이 삭제되었습니다.", "id", id, "boardId", article.getBoardId());

	}

	public ResultData modifyArticleById(int id, String title, String body) {
		Article article = getArticleById(id);

		if (isEmpty(article)) {
			return new ResultData("F-1", "존재하지 않는 게시물 번호입니다.", "id", id);
		}

		articleDao.modifyArticle(id, title, body);

		return new ResultData("S-1", "게시물이 수정되었습니다.", "id", id);
	}

	private boolean isEmpty(Article article) {
		if (article == null) {
			return true;
		} else if (article.isDelStatus()) {
			return true;
		}
		return false;
	}

	public ResultData addArticle(Map<String, Object> param) {
		articleDao.addArticle(param);

		int id = Util.getAsInt(param.get("id"), 0);

		return new ResultData("S-1", "성공하였습니다.", "id", id);
	}

	public Article getForPrintArticle(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Article getArticle(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultData getActorCanModifyRd(Article article, Member loginedMember) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultData modifyArticle(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return null;
	}

	public Board getBoardById(int boardId) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getArticlesTotalCount(int boardId, String searchKeywordType, String searchKeyword) {
		if (searchKeyword != null && searchKeyword.length() == 0) {
			searchKeyword = null;
		}

		return articleDao.getArticlesTotalCount(boardId, searchKeywordType, searchKeyword);
	}

	public List<Article> getForPrintArticles(int boardId, String searchKeywordType, String searchKeyword,
			int itemsCountInAPage, int page) {
		if (searchKeyword != null && searchKeyword.length() == 0) {
			searchKeyword = null;
		}

		int limitFrom = (page - 1) * itemsCountInAPage;
		int limitTake = itemsCountInAPage;

		return articleDao.getForPrintArticles(boardId, searchKeywordType, searchKeyword, limitFrom, limitTake);
	}

	public Article getForPrintArticleById(int id) {
		return articleDao.getForPrintArticleById(id);
	}

}
