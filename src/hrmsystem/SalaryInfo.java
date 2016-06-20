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
public class SalaryInfo {
    private int id;
    private double basic_salary;
    private double running_basic;
    private double house_rent;
    private double medical;
    private double others;
    private double conveyance;
    private double car_allow;
    private double gross_salary;
    private String bank_code;
    private String ac_no;

    public SalaryInfo() {
    }

    public SalaryInfo(int id, double basic_salary, double gross_salary, double running_basic, String bank_code, String ac_no) {
        this.id = id;
        this.basic_salary = basic_salary;
        this.gross_salary = gross_salary;
        this.running_basic = running_basic;
        this.bank_code = bank_code;
        this.ac_no = ac_no;
    }

    public SalaryInfo(int id, double basic_salary, double gross_salary, double house_rent, double running_basic, double medical, double others, double conveyance, double car_allow, String bank_code, String ac_no) {
        this.id = id;
        this.basic_salary = basic_salary;
        this.gross_salary = gross_salary;
        this.house_rent = house_rent;
        this.running_basic = running_basic;
        this.medical = medical;
        this.others = others;
        this.conveyance = conveyance;
        this.car_allow = car_allow;
        this.bank_code = bank_code;
        this.ac_no = ac_no;
    }

    public int getId() {
        return id;
    }

    public double getBasic_salary() {
        return basic_salary;
    }

    public double getGross_salary() {
        return gross_salary;
    }

    public double getHouse_rent() {
        return house_rent;
    }

    public double getRunning_basic() {
        return running_basic;
    }

    public double getMedical() {
        return medical;
    }

    public double getOthers() {
        return others;
    }

    public double getConveyance() {
        return conveyance;
    }

    public double getCar_allow() {
        return car_allow;
    }

    public String getBank_code() {
        return bank_code;
    }

    public String getAc_no() {
        return ac_no;
    }

    public void setBasic_salary(double basic_salary) {
        this.basic_salary = basic_salary;
    }

    public void setGross_salary(double gross_salary) {
        this.gross_salary = gross_salary;
    }

    public void setHouse_rent(double house_rent) {
        this.house_rent = house_rent;
    }

    public void setRunning_basic(double running_basic) {
        this.running_basic = running_basic;
    }

    public void setMedical(double medical) {
        this.medical = medical;
    }

    public void setOthers(double others) {
        this.others = others;
    }

    public void setConveyance(double conveyance) {
        this.conveyance = conveyance;
    }

    public void setCar_allow(double car_allow) {
        this.car_allow = car_allow;
    }

    public void setBank_code(String bank_code) {
        this.bank_code = bank_code;
    }

    public void setAc_no(String ac_no) {
        this.ac_no = ac_no;
    }
    
}
