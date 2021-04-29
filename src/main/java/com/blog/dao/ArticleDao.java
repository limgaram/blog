package com.blog.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.blog.dto.Article;

@Mapper
public interface ArticleDao {
	void writeArticle(@Param("boardId") int boardId, @Param("memberId") int memberId, @Param("title") String title,
			@Param("body") String body);

	Article getArticleById(@Param("id") int id);

	void deleteArticleById(@Param("id") int id);

	boolean modifyArticle(@Param("id") int id, @Param("title") String title, @Param("body") String body);

	int getLastInsertId();

	void addArticle(Map<String, Object> param);

	int getArticlesTotalCount(@Param("boardId") int boardId);

	List<Article> getForPrintArticles(@Param("boardId") int boardId, @Param("limitFrom") int limitFrom,
			@Param("limitTake") int limitTake);
}
