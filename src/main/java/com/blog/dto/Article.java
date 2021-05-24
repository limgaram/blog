package com.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
	private int id;
	private String regDate;
	private String updateDate;
	private int boardId;
	private int memberId;
	private String title;
	private String body;
	private boolean blindStatus;
	private String blindDate;
	private boolean delStatus;
	private String delDate;
	private int hitCount;
	private int repliesCount;
	private int likeCount;
	private int dislikeCount;

	private String extra__writerName;

	public String getBodyForPrint(){
		String bodyForPrint = body.replaceAll("\r\n", "<br>");
		bodyForPrint = body.replaceAll("\r", "<br>");
		bodyForPrint = body.replaceAll("\n", "<br>");

		return bodyForPrint;
	}
}
