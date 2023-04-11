package org.ssafy.shopping.backend.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {
    private String memberId;
    private String memberPw;
    private String memberName;
    private String memberMail;
    private String memberPhone;
    private int regDate;

}
