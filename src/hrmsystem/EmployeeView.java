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
public class EmployeeView {
    private int id;
    private String name;
    private String sex;
    
    private String dept_code;
    private String branch_id;
    private String joining_date;
    private String designation;

    public EmployeeView() {
    }

    public EmployeeView(int id, String name, String sex, String designation, String dept_code, String branch_id, String joining_date) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.designation = designation;
        this.dept_code = dept_code;
        this.branch_id = branch_id;
        this.joining_date = joining_date;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getDesignation() {
        return designation;
    }

    public String getDept_code() {
        return dept_code;
    }

    public String getBranch_id() {
        return branch_id;
    }

    public String getJoining_date() {
        return joining_date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setDesignation(String Designation) {
        this.designation = designation;
    }

    public void setDept_code(String dept_code) {
        this.dept_code = dept_code;
    }

    public void setBranch_id(String branch_id) {
        this.branch_id = branch_id;
    }

    public void setJoining_date(String joining_date) {
        this.joining_date = joining_date;
    }
    
}
