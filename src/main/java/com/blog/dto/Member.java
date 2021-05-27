package com.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member extends EntityDto {
    private int id;
    private String regDate;
    private String updateDate;
    private String loginId;
    private String loginPw;
    private String name;
    private String nickname;
    private String email;
    private String cellphoneNo;
    private boolean delStatus;
    private String delDate;

    private int authLevel;
}
