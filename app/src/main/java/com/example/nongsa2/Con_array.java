package com.example.nongsa2;

import java.util.ArrayList;
import java.util.List;

public class Con_array {
    static List<String> count_list = new ArrayList<>();
    static List<String> Title_list = new ArrayList<String>();
    static List<String> USERNM_list = new ArrayList<String>();
    static List<String> REGDT_list  = new ArrayList<String>();
    static List<String> NTTID_list  = new ArrayList<String>();
    static List<String> contents_list  = new ArrayList<String>();
    static  List<String> answerTitle_list  = new ArrayList<String>();
    static  List<String> answerContents_list  = new ArrayList<String>();
    static  List<String> answerUserNm_list  = new ArrayList<String>();
    static  List<String> answerOrgNm_list  = new ArrayList<String>();
    static   List<String> answerDeptNm_list  = new ArrayList<String>();
    static   List<String> answerEmail_list  = new ArrayList<String>();


    public static String getcount(int i) {
        return count_list .get(i);
    }

    public static void setcount(String count) {
        count_list.add( count );
    }

    public static String gettitle(int i) {
        return Title_list .get(i);
    }

    public static void settitle(String Title) {
        Title_list.add( Title );
    }

    public static String getuserNm(int i) {
        return USERNM_list .get(i);
    }

    public static void setuserNm(String USERNM) {
        USERNM_list .add(USERNM);
    }


    public static String getregDt(int i) {
        return REGDT_list .get(i);
    }

    public static void setregDt(String REGDT) {
        REGDT_list .add(REGDT);
    }


    public static String getnttId(int i) {
        return NTTID_list.get(i);
    }

    public static void setnttId(String NTTID) {
        NTTID_list.add(NTTID);
    }


    public static String getcontents(int i) {
        return contents_list.get(i);
    }

    public static void setcontents(String contents) {
        contents_list.add(contents);
    }


    public static String getanswerTitle(int i) {
        return answerTitle_list.get(i);
    }

    public static void setanswerTitle(String answerTitle) {
        answerTitle_list.add(answerTitle);
    }

    public static String getanswerContents(int i) {
        return answerContents_list.get(i);
    }

    public static void setanswerContents(String answerContents) {
        answerContents_list.add(answerContents);
    }

    public static String getanswerUserNm(int i) {
        return answerUserNm_list.get(i);
    }

    public static void setanswerUserNm(String answerUserNm) {
        answerUserNm_list.add(answerUserNm);
    }

    public static String getanswerOrgNm(int i) {
        return answerOrgNm_list.get(i);
    }

    public static void setanswerOrgNm(String answerOrgNm) {
        answerOrgNm_list.add(answerOrgNm);
    }

    public static String getanswerDeptNm(int i) {
        return answerDeptNm_list.get(i);
    }

    public static void setanswerDeptNm(String answerDeptNm) {
        answerDeptNm_list.add(answerDeptNm);
    }

    public static String getanswerEmail(int i) {
        return answerEmail_list.get(i);
    }

    public static void setanswerEmail(String answerEmail) {
        answerEmail_list.add(answerEmail);
    }


    public static int getListSize() {
        return answerEmail_list.size();
    }
}
