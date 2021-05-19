package com.blog.controller;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
	protected String msgAndBack(HttpServletRequest req, String msg) {
		req.setAttribute("historyBack", true);
		req.setAttribute("msg", msg);
		return "common/redirect";
	}

	protected String msgAndReplace(HttpServletRequest req, String msg, String replaceUrl) {
		req.setAttribute("msg", msg);
		req.setAttribute("replaceUrl", replaceUrl);
		return "common/redirect";
	}
}
