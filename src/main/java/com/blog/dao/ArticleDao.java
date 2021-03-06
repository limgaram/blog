package com.blog.dao;

import com.blog.dto.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ArticleDao {
	void writeArticle(@Param("boardId") int boardId, @Param("memberId") int memberId, @Param("title") String title,
			@Param("body") String body);

	Article getArticleById(@Param("id") int id);

	void deleteArticleById(@Param("id") int id);

	boolean modifyArticle(@Param("id") int id, @Param("title") String title, @Param("body") String body);

	int getLastInsertId();

	void addArticle(Map<String, Object> param);

	int getArticlesTotalCount(@Param("boardId") int boardId,
			@Param("searchKeywordType") String searchKeywordType, @Param("searchKeyword") String searchKeyword);

	List<Article> getForPrintArticles(@Param("boardId") int boardId,
			@Param("searchKeywordType") String searchKeywordType, @Param("searchKeyword") String searchKeyword,
			@Param("limitFrom") int limitFrom, @Param("limitTake") int limitTake);

    Article getForPrintArticleById(@Param("id") int id);
}
