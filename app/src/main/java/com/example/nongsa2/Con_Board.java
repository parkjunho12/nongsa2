package com.example.nongsa2;

import java.util.ArrayList;
import java.util.List;

public class Con_Board {
    String count="";
    String Title="";
    String USERNM="";
    String REGDT="";
    String NTTID="";
    String contents="";
    String answerTitle="";
    String answerContents="";
    String answerUserNm="";
    String answerOrgNm="";
    String answerDeptNm="";
    String answerEmail="";


    public Con_Board( String Title, String USERNM, String REGDT, String NTTID, String contents, String answerTitle, String answerContents, String answerUserNm, String answerOrgNm, String answerDeptNm, String answerEmail) {

        this.Title = Title;
        this.USERNM = USERNM;
        this.REGDT = REGDT;
        this.NTTID = NTTID;
        this.contents = contents;
        this.answerTitle = answerTitle;
        this.answerContents = answerContents;
        this.answerUserNm = answerUserNm;
        this.answerOrgNm = answerOrgNm;
        this.answerDeptNm = answerDeptNm;
        this.answerEmail = answerEmail;

    }

}
