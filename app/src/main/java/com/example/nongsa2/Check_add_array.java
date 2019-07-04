package com.example.nongsa2;

import java.util.ArrayList;
import java.util.List;

public class Check_add_array {

    static List<String> roadAddr_list = new ArrayList<String>();
    static List<String> jibunAddr_list = new ArrayList<String>();
    static List<String> admCd_list = new ArrayList<String>();
    static List<String> rnMgtSn_list = new ArrayList<String>();
    static List<String> udrtYn_list = new ArrayList<String>();
    static List<String> buldMnnm_list = new ArrayList<String>();
    static List<String> buldSlno_list = new ArrayList<String>();
    static List<String> latitude_list = new ArrayList<String>();
    static List<String> longitude_list = new ArrayList<String>();

    public static String getlatitude( int i) {
        return latitude_list.get(i);
    }

    public static void setlatitude(String latitude) {
        latitude_list.add(latitude);
    }

    public static String getlongitude(int i) {
        return longitude_list.get(i);
    }

    public static void setlongitude(String longitude) {
        longitude_list.add(longitude);
    }



    public static int getsize( ) {
        return roadAddr_list.size();
    }




    public static void setroadAddr(String roadAddr) {
        roadAddr_list.add(roadAddr);
    }

    public static String getroadAddr(int i) {
        return roadAddr_list.get(i);
    }

    public static void setjibunAddr(String jibunAddr) {
        jibunAddr_list.add(jibunAddr);
    }

    public static String getjibunAddr(int i) {
        return jibunAddr_list.get(i);
    }

    public static void setadmCd(String admCd) {
        admCd_list.add(admCd);
    }

    public static String getadmCd(int i) {
        return admCd_list.get(i);
    }


    public static void setrnMgtSn(String rnMgtSn) {
        rnMgtSn_list.add(rnMgtSn);
    }

    public static String getrnMgtSn(int i) {
        return rnMgtSn_list.get(i);
    }
    public static void setudrtYn(String udrtYn) {
        udrtYn_list.add(udrtYn);
    }

    public static String getudrtYn(int i) {
        return udrtYn_list.get(i);
    }

    public static void setbuldMnnm(String buldMnnm) {
        buldMnnm_list.add(buldMnnm);
    }

    public static String getbuldMnnm(int i) {
        return buldMnnm_list.get(i);
    }



    public static void setbuldSlno(String buldSlno) {
        buldSlno_list.add(buldSlno);
    }

    public static String getbuldSlno(int i) {
        return buldSlno_list.get(i);
    }

}
