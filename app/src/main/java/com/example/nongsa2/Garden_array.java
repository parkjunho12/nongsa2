package com.example.nongsa2;

import java.util.ArrayList;
import java.util.List;

public class Garden_array {
    static List<String> FARM_TYPE_list = new ArrayList<String>();
    static List<String> FARM_NM_list = new ArrayList<String>();
    static List<String> ADDRESS1_list = new ArrayList<String>();
    static List<String> SELL_AREA_INFO_list  = new ArrayList<String>();
    static List<String> HOMEPAGE_list  = new ArrayList<String>();
    static List<String> OFF_SITE_list  = new ArrayList<String>();
    static  List<String> APPLY_MTHD_list  = new ArrayList<String>();
    static  List<String> PRICE_list  = new ArrayList<String>();
    static  List<String> REGIST_DT_list  = new ArrayList<String>();
    static  List<String> ID_list  = new ArrayList<String>();
    static   List<String> Name_list  = new ArrayList<String>();
    static   List<String> phone_list  = new ArrayList<String>();
    public void clear() {

        FARM_TYPE_list.clear();
        FARM_NM_list.clear();
        ADDRESS1_list.clear();
        SELL_AREA_INFO_list.clear();
        HOMEPAGE_list.clear();
        OFF_SITE_list.clear();
        APPLY_MTHD_list.clear();
        PRICE_list.clear();
        REGIST_DT_list.clear();
        ID_list.clear();
        Name_list.clear();
        phone_list.clear();

    }

    public static String getFARM_TYPE(int i) {
        return FARM_TYPE_list .get(i);
    }

    public static void setFARM_TYPE(String FARM_TYPE) {
        FARM_TYPE_list.add( FARM_TYPE );
    }

    public static String getFARM_NM(int i) {
        return FARM_NM_list .get(i);
    }

    public static void setFARM_NM(String FARM_NM) {
        FARM_NM_list.add( FARM_NM );
    }

    public static String getADDRESS1(int i) {
        return ADDRESS1_list .get(i);
    }

    public static void setADDRESS1(String ADDRESS1) {
        ADDRESS1_list .add(ADDRESS1);
    }


    public static String getSELL_AREA_INFO(int i) {
        return SELL_AREA_INFO_list .get(i);
    }

    public static void setSELL_AREA_INFO(String SELL_AREA_INFO) {
        SELL_AREA_INFO_list .add(SELL_AREA_INFO);
    }


    public static String getHOMEPAGE(int i) {
        return HOMEPAGE_list.get(i);
    }

    public static void setHOMEPAGE(String HOMEPAGE) {
        HOMEPAGE_list.add(HOMEPAGE);
    }


    public static String getOFF_SITE(int i) {
        return OFF_SITE_list.get(i);
    }

    public static void setOFF_SITE(String OFF_SITE) {
        OFF_SITE_list.add(OFF_SITE);
    }


    public static String getAPPLY_MTHD(int i) {
        return APPLY_MTHD_list.get(i);
    }

    public static void setAPPLY_MTHD(String APPLY_MTHD) {
        APPLY_MTHD_list.add(APPLY_MTHD);
    }

    public static String getPRICE(int i) {
        return PRICE_list.get(i);
    }

    public static void setPRICE(String PRICE) {
        PRICE_list.add(PRICE);
    }

    public static String getREGIST(int i) {
        return REGIST_DT_list.get(i);
    }

    public static void setREGIST(String REGIST) {
        REGIST_DT_list.add(REGIST);
    }

    public static String getID(int i) {
        return ID_list.get(i);
    }

    public static void setID(String ID) {
        ID_list.add(ID);
    }

    public static String getName(int i) {
        return Name_list.get(i);
    }

    public static void setName(String Name) {
        Name_list.add(Name);
    }

    public static String getphone(int i) {
        return phone_list.get(i);
    }

    public static void setphone(String phone) {
        phone_list.add(phone);
    }


    public static int getListSize() {
        return REGIST_DT_list.size();
    }
}
