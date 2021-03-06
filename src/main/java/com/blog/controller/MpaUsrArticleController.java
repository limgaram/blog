package com.blog.controller;

import com.blog.dto.Article;
import com.blog.dto.Board;
import com.blog.dto.ResultData;
import com.blog.service.ArticleService;
import com.blog.service.BoardService;
import com.blog.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Slf4j
public class MpaUsrArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private BoardService boardService;

    @RequestMapping("/mpaUsr/article/list")
    public String showList(HttpServletRequest req, @RequestParam(defaultValue = "1") int boardId,
                           String searchKeywordType, String searchKeyword, @RequestParam(defaultValue = "1") int page) {
        Board board = boardService.getBoardById(boardId);

        if (Util.isEmpty(searchKeywordType)) {
            searchKeywordType = "titleAndBody";
        }

        if (board == null) {
            return msgAndBack(req, boardId + "번 게시판이 존재하지 않습니다.");
        }

        req.setAttribute("board", board);

        int totalItemsCount = articleService.getArticlesTotalCount(boardId, searchKeywordType, searchKeyword);

//        if (searchKeyword == null || searchKeyword.trim().length() == 0) {
//
//        }
        req.setAttribute("totalItemsCount", totalItemsCount);

        int itemsCountInAPage = 20;
        int totalPage = (int) Math.ceil(totalItemsCount / (double) itemsCountInAPage);

        req.setAttribute("page", page);
        req.setAttribute("totalPage", totalPage);

        List<Article> articles = articleService.getForPrintArticles(boardId, searchKeywordType, searchKeyword,
                itemsCountInAPage, page);

        req.setAttribute("articles", articles);

        return "mpaUsr/article/list";
    }

    @RequestMapping("/mpaUsr/article/detail")
    public String showDetail(HttpServletRequest req, int id) {
        Article article = articleService.getForPrintArticleById(id);

        if (article == null) {
            return msgAndBack(req, id + "번 게시물이 존재하지 않습니다.");
        }

        Board board = boardService.getBoardById(article.getBoardId());

        req.setAttribute("article", article);
        req.setAttribute("board", board);

        return "mpaUsr/article/detail";
    }

    @RequestMapping("/mpaUsr/article/write")
    public String showWrite(HttpServletRequest req, @RequestParam(defaultValue = "1") int boardId) {
        Board board = boardService.getBoardById(boardId);

        if (board == null) {
            return msgAndBack(req, boardId + "번 게시물이 존재하지 않습니다.");
        }

        req.setAttribute("board", board);

        return "mpaUsr/article/write";
    }

    @RequestMapping("/mpaUsr/article/doWrite")
    public String doWrite(HttpServletRequest req, int boardId, String title, String body) {
        if (Util.isEmpty(title)) {
            return msgAndBack(req, "제목을 입력해주세요.");
        }

        if (Util.isEmpty(body)) {
            return msgAndBack(req, "내용을 입력해주세요.");
        }

        int memberId = 3; //임시

        ResultData writeArticleRd = articleService.writeArticle(boardId, memberId, title, body);

        if (writeArticleRd.isFail()) {
            return msgAndBack(req, writeArticleRd.getMsg());
        }

        String replaceUrl = "detail?id=" + writeArticleRd.getBody().get("id");

        return msgAndReplace(req, writeArticleRd.getMsg(), replaceUrl);
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
    public String doDelete(HttpServletRequest req, Integer id) {
        if (Util.isEmpty(id)) {
            return msgAndBack(req, "id를 입력해주세요.");
        }

        ResultData rd = articleService.deleteArticleById(id);

        if (rd.isFail()) {
            return msgAndBack(req, rd.getMsg());
        }

        String redirectUrl = "../article/list?boardId=" + rd.getBody().get("boardId");

        return msgAndReplace(req, rd.getMsg(), redirectUrl);
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

