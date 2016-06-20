/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrmsystem;

/**
 *
 * @author iftekher
 */
public class Address {
    private int id;
    private String pres_house_no;
    private String pres_road_no;
    private String pres_village;
    private String pres_po;
    private String pres_ps;
    private String pres_dist;
    private String pres_phn;
    private String perm_house_no;
    private String perm_road_no;
    private String perm_village;
    private String perm_po;
    private String perm_ps;
    private String perm_dist;
    private String perm_phn; 

    public Address() {
    }
  

    public Address(int id, String pres_house_no, String pres_road_no, String pres_village, String pres_po, String pres_ps, String pres_dist, String pres_phn, String perm_house_no, String perm_road_no, String perm_village, String perm_po, String perm_ps, String perm_dist, String perm_phn) {
        this.id = id;
        this.pres_house_no = pres_house_no;
        this.pres_road_no = pres_road_no;
        this.pres_village = pres_village;
        this.pres_po = pres_po;
        this.pres_ps = pres_ps;
        this.pres_dist = pres_dist;
        this.pres_phn = pres_phn;
        this.perm_house_no = perm_house_no;
        this.perm_road_no = perm_road_no;
        this.perm_village = perm_village;
        this.perm_po = perm_po;
        this.perm_ps = perm_ps;
        this.perm_dist = perm_dist;
        this.perm_phn = perm_phn;
    }

    public int getId() {
        return id;
    }

    public String getPres_house_no() {
        return pres_house_no;
    }

    public String getPres_road_no() {
        return pres_road_no;
    }

    public String getPres_village() {
        return pres_village;
    }

    public String getPres_po() {
        return pres_po;
    }

    public String getPres_ps() {
        return pres_ps;
    }

    public String getPres_dist() {
        return pres_dist;
    }

    public String getPres_phn() {
        return pres_phn;
    }

    public String getPerm_house_no() {
        return perm_house_no;
    }

    public String getPerm_road_no() {
        return perm_road_no;
    }

    public String getPerm_village() {
        return perm_village;
    }

    public String getPerm_po() {
        return perm_po;
    }

    public String getPerm_ps() {
        return perm_ps;
    }

    public String getPerm_dist() {
        return perm_dist;
    }

    public String getPerm_phn() {
        return perm_phn;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPres_house_no(String pres_house_no) {
        this.pres_house_no = pres_house_no;
    }

    public void setPres_road_no(String pres_road_no) {
        this.pres_road_no = pres_road_no;
    }

    public void setPres_village(String pres_village) {
        this.pres_village = pres_village;
    }

    public void setPres_po(String pres_po) {
        this.pres_po = pres_po;
    }

    public void setPres_ps(String pres_ps) {
        this.pres_ps = pres_ps;
    }

    public void setPres_dist(String pres_dist) {
        this.pres_dist = pres_dist;
    }

    public void setPres_phn(String pres_phn) {
        this.pres_phn = pres_phn;
    }

    public void setPerm_house_no(String perm_house_no) {
        this.perm_house_no = perm_house_no;
    }

    public void setPerm_road_no(String perm_road_no) {
        this.perm_road_no = perm_road_no;
    }

    public void setPerm_village(String perm_village) {
        this.perm_village = perm_village;
    }

    public void setPerm_po(String perm_po) {
        this.perm_po = perm_po;
    }

    public void setPerm_ps(String perm_ps) {
        this.perm_ps = perm_ps;
    }

    public void setPerm_dist(String perm_dist) {
        this.perm_dist = perm_dist;
    }

    public void setPerm_phn(String perm_phn) {
        this.perm_phn = perm_phn;
    }
    
}
