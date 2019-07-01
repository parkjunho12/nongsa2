package com.example.nongsa2;

import android.webkit.WebView;

public class Board {


    String title="";
    String content="";
    String date="";
    String SIDO_NM="";
    String ID="";
    String SIGUN_NM="";
    String ADDR="";
    String DEAL_AMOUNT="";
    String DEAL_BIGO="";
    String BUILDING_AREA="";
    String AREA_ETC="";
    String BUILD_YEAR="";
    String VACANT_YEAR="";
    String STRUCT_TYPE="";
    String OWNER_NM="";
    String OWNER_CONTACT="";
    String INSPECTOR="";
    String LOT_AREA="";
    String BIGO="";
    String FILE_PATH1="";
    String FILE_PATH2="";
    String FILE_PATH3="";
    String DETAIL_URL="";
    String DEAL_NEGO_YN="";
    String GUBUN="";
    String DEAL_TYPE="";
    String REG_DT="";

    public Board(String title, String content, String date, String SIDO_NM, String ID, String SIGUN_NM, String ADDR, String DEAL_AMOUNT, String DEAL_BIGO, String BUILDING_AREA, String AREA_ETC, String BUILD_YEAR, String VACANT_YEAR, String STRUCT_TYPE, String OWNER_NM, String OWNER_CONTACT, String INSPECTOR, String LOT_AREA, String BIGO, String FILE_PATH1, String FILE_PATH2, String FILE_PATH3, String DETAIL_URL, String DEAL_NEGO_YN, String GUBUN, String DEAL_TYPE, String REG_DT) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.SIDO_NM = SIDO_NM;
        this.ID = ID;
        this.SIGUN_NM = SIGUN_NM;
        this.ADDR = ADDR;
        this.DEAL_AMOUNT = DEAL_AMOUNT;
        this.DEAL_BIGO = DEAL_BIGO;
        this.BUILDING_AREA = BUILDING_AREA;
        this.AREA_ETC = AREA_ETC;
        this.BUILD_YEAR = BUILD_YEAR;
        this.VACANT_YEAR = VACANT_YEAR;
        this.STRUCT_TYPE = STRUCT_TYPE;
        this.OWNER_NM = OWNER_NM;
        this.OWNER_CONTACT = OWNER_CONTACT;
        this.INSPECTOR = INSPECTOR;
        this.LOT_AREA = LOT_AREA;
        this.BIGO = BIGO;
        this.FILE_PATH1 = FILE_PATH1;
        this.FILE_PATH2 = FILE_PATH2;
        this.FILE_PATH3 = FILE_PATH3;
        this.DETAIL_URL = DETAIL_URL;
        this.DEAL_NEGO_YN = DEAL_NEGO_YN;
        this.GUBUN = GUBUN;
        this.DEAL_TYPE = DEAL_TYPE;
        this.REG_DT = REG_DT;
    }

    public String getSIDO_NM() {
        return SIDO_NM;
    }

    public void setSIDO_NM(String SIDO_NM) {
        this.SIDO_NM = SIDO_NM;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getSIGUN_NM() {
        return SIGUN_NM;
    }

    public void setSIGUN_NM(String SIGUN_NM) {
        this.SIGUN_NM = SIGUN_NM;
    }

    public String getADDR() {
        return ADDR;
    }

    public void setADDR(String ADDR) {
        this.ADDR = ADDR;
    }

    public String getDEAL_AMOUNT() {
        return DEAL_AMOUNT;
    }

    public void setDEAL_AMOUNT(String DEAL_AMOUNT) {
        this.DEAL_AMOUNT = DEAL_AMOUNT;
    }

    public String getDEAL_BIGO() {
        return DEAL_BIGO;
    }

    public void setDEAL_BIGO(String DEAL_BIGO) {
        this.DEAL_BIGO = DEAL_BIGO;
    }

    public String getBUILDING_AREA() {
        return BUILDING_AREA;
    }

    public void setBUILDING_AREA(String BUILDING_AREA) {
        this.BUILDING_AREA = BUILDING_AREA;
    }

    public String getAREA_ETC() {
        return AREA_ETC;
    }

    public void setAREA_ETC(String AREA_ETC) {
        this.AREA_ETC = AREA_ETC;
    }

    public String getBUILD_YEAR() {
        return BUILD_YEAR;
    }

    public void setBUILD_YEAR(String BUILD_YEAR) {
        this.BUILD_YEAR = BUILD_YEAR;
    }

    public String getVACANT_YEAR() {
        return VACANT_YEAR;
    }

    public void setVACANT_YEAR(String VACANT_YEAR) {
        this.VACANT_YEAR = VACANT_YEAR;
    }

    public String getSTRUCT_TYPE() {
        return STRUCT_TYPE;
    }

    public void setSTRUCT_TYPE(String STRUCT_TYPE) {
        this.STRUCT_TYPE = STRUCT_TYPE;
    }

    public String getOWNER_NM() {
        return OWNER_NM;
    }

    public void setOWNER_NM(String OWNER_NM) {
        this.OWNER_NM = OWNER_NM;
    }

    public String getOWNER_CONTACT() {
        return OWNER_CONTACT;
    }

    public void setOWNER_CONTACT(String OWNER_CONTACT) {
        this.OWNER_CONTACT = OWNER_CONTACT;
    }

    public String getINSPECTOR() {
        return INSPECTOR;
    }

    public void setINSPECTOR(String INSPECTOR) {
        this.INSPECTOR = INSPECTOR;
    }

    public String getLOT_AREA() {
        return LOT_AREA;
    }

    public void setLOT_AREA(String LOT_AREA) {
        this.LOT_AREA = LOT_AREA;
    }

    public String getBIGO() {
        return BIGO;
    }

    public void setBIGO(String BIGO) {
        this.BIGO = BIGO;
    }

    public String getFILE_PATH1() {
        return FILE_PATH1;
    }

    public void setFILE_PATH1(String FILE_PATH1) {
        this.FILE_PATH1 = FILE_PATH1;
    }

    public String getFILE_PATH2() {
        return FILE_PATH2;
    }

    public void setFILE_PATH2(String FILE_PATH2) {
        this.FILE_PATH2 = FILE_PATH2;
    }

    public String getFILE_PATH3() {
        return FILE_PATH3;
    }

    public void setFILE_PATH3(String FILE_PATH3) {
        this.FILE_PATH3 = FILE_PATH3;
    }

    public String getDETAIL_URL() {
        return DETAIL_URL;
    }

    public void setDETAIL_URL(String DETAIL_URL) {
        this.DETAIL_URL = DETAIL_URL;
    }

    public String getDEAL_NEGO_YN() {
        return DEAL_NEGO_YN;
    }

    public void setDEAL_NEGO_YN(String DEAL_NEGO_YN) {
        this.DEAL_NEGO_YN = DEAL_NEGO_YN;
    }

    public String getGUBUN() {
        return GUBUN;
    }

    public void setGUBUN(String GUBUN) {
        this.GUBUN = GUBUN;
    }

    public String getDEAL_TYPE() {
        return DEAL_TYPE;
    }

    public void setDEAL_TYPE(String DEAL_TYPE) {
        this.DEAL_TYPE = DEAL_TYPE;
    }

    public String getREG_DT() {
        return REG_DT;
    }

    public void setREG_DT(String REG_DT) {
        this.REG_DT = REG_DT;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}