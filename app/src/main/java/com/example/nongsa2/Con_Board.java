package com.example.nongsa2;

public class Con_Board {


    String TITLE="";
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


    public Con_Board( String TITLE,String USERNM,String REGDT,String NTTID,String contents,String answerTitle,String answerContents,String answerUserNm,String answerOrgNm,String answerDeptNm,String answerEmail) {

        this.TITLE = TITLE;
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
        public String getTITLE() {
        return TITLE;
    }

        public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

        public String getUSERNM() {
        return USERNM;
    }

        public void setUSERNM(String USERNM) {
        this.USERNM = USERNM;
    }

        public String getREGDT() {
        return REGDT;
    }

        public void setREGDT(String REGDT) {
        this.REGDT = REGDT;
    }

        public String getNTTID() {
        return NTTID;
    }

        public void setNTTID(String NTTID) {
        this.NTTID = NTTID;
    }

        public String getContents() {
        return contents;
    }

        public void setContents(String contents) {
        this.contents = contents;
    }

        public String getAnswerTitle() {
        return answerTitle;
    }

        public void setAnswerTitle(String answerTitle) {
        this.answerTitle = answerTitle;
    }

        public String getAnswerContents() {
        return answerContents;
    }

        public void setAnswerContents(String answerContents) {
        this.answerContents = answerContents;
    }

        public String getAnswerUserNm() {
        return answerUserNm;
    }

        public void setAnswerUserNm(String answerUserNm) {
        this.answerUserNm = answerUserNm;
    }

        public String getAnswerOrgNm() {
        return answerOrgNm;
    }

        public void setAnswerOrgNm(String answerOrgNm) {
        this.answerOrgNm = answerOrgNm;
    }

        public String getAnswerDeptNm() {
        return answerDeptNm;
    }

        public void setAnswerDeptNm(String answerDeptNm) {
        this.answerDeptNm = answerDeptNm;
    }

        public String getAnswerEmail() {
        return answerEmail;
    }

        public void setAnswerEmail(String answerEmail) {
        this.answerEmail = answerEmail;
    }


}
