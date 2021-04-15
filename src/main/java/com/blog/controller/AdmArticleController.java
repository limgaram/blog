package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.service.ArticleService;
import com.blog.service.GenFileService;

@Controller
public class AdmArticleController extends BaseController {
	@Autowired
	private ArticleService articleService;

	@RequestMapping("/adm/article/list")
	public String showList() {
		return "adm/article/list";
	}
	
	@RequestMapping("/adm/article/doAdd")
	public String doAdd() {
		return "adm/article/doAdd";
	}

}
