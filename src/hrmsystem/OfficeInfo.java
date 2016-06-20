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
public class OfficeInfo {
    private int id;
    private String name;
    private int company_no;
    private String designation;
    private String confirmation_date;
    private String dept_code;
    private String joining_date;
    private String gender;
    private String branch_id;

    public OfficeInfo() {
    }

    public OfficeInfo(int id, String name, int company_no, String designation, String confirmation_date, String dept_code, String joining_date, String gender, String branch_id) {
        this.id = id;
        this.name = name;
        this.company_no = company_no;
        this.designation = designation;
        this.confirmation_date = confirmation_date;
        this.dept_code = dept_code;
        this.joining_date = joining_date;
        this.gender = gender;
        this.branch_id = branch_id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCompany_no() {
        return company_no;
    }

    public String getDesignation() {
        return designation;
    }

    public String getConfirmation_date() {
        return confirmation_date;
    }

    public String getDept_code() {
        return dept_code;
    }

    public String getJoining_date() {
        return joining_date;
    }

    public String getGender() {
        return gender;
    }

    public String getBranch_id() {
        return branch_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompany_no(int company_no) {
        this.company_no = company_no;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setConfirmation_date(String confirmation_date) {
        this.confirmation_date = confirmation_date;
    }

    public void setDept_code(String dept_code) {
        this.dept_code = dept_code;
    }

    public void setJoining_date(String joining_date) {
        this.joining_date = joining_date;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBranch_id(String branch_id) {
        this.branch_id = branch_id;
    }
    
    
}
