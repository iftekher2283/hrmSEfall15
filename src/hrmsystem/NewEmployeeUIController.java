/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrmsystem;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author iftekher
 */
public class NewEmployeeUIController implements Initializable {
    @FXML
    private ComboBox<Integer> companyNoBox;
    @FXML
    private ComboBox<Designation> designationBox;
    @FXML
    private ComboBox<DepartmentCode> deptCodeBox;
    @FXML
    private ComboBox<Gender> genderBox;
    @FXML
    private ComboBox<BranchID> branchIDBox;
    @FXML
    private DatePicker confirmationDatePicker;
    @FXML
    private DatePicker joiningDatePicker;
    @FXML
    private TextField employeeNameField;
    @FXML
    private TextField idNoField;
    @FXML
    private TextField grossSalaryField;
    @FXML
    private TextField basicSalaryField;
    @FXML
    private TextField houseRentField;
    @FXML
    private TextField runningBasicField;
    @FXML
    private TextField medicalAllowField;
    @FXML
    private TextField othersAllowField;
    @FXML
    private TextField conveyanceField;
    @FXML
    private TextField carAllowField;
    @FXML
    private TextField accountNoField;
    @FXML
    private ComboBox<BankCode> bankCodeBox;
    @FXML
    private TextField fathersNameField;
    @FXML
    private TextField mothersNameField;
    @FXML
    private TextField presHouseNoField;
    @FXML
    private TextField presRoadNoField;
    @FXML
    private TextField presVillageField;
    @FXML
    private TextField presPOField;
    @FXML
    private TextField presPSField;
    @FXML
    private TextField presDistField;
    @FXML
    private TextField presPhnField;
    @FXML
    private TextField educationalQualificationField;
    @FXML
    private TextField technicalQualificationField;
    @FXML
    private TextField nationalIdNoField;
    @FXML
    private TextField mobileNoField;
    @FXML
    private TextField birthPlaceField;
    @FXML
    private TextField tinNoField;
    @FXML
    private TextField emailAddressField;
    @FXML
    private TextField passportNoField;
    @FXML
    private TextField emergencyContNo;
    @FXML
    private TextField permHouseNoField;
    @FXML
    private TextField permRoadNoField;
    @FXML
    private TextField permVillageField;
    @FXML
    private TextField permPOField;
    @FXML
    private TextField permPSField;
    @FXML
    private TextField permDistField;
    @FXML
    private ComboBox<Religion> religionBox;
    @FXML
    private ComboBox<MaritalStatus> maritalStatusBox;
    @FXML
    private ComboBox<BloodGroup> bloodGroupBox;
    @FXML
    private TextField ageField;
    @FXML
    private TextField spouseField;
    @FXML
    private TextField nationalityField;
    @FXML
    private TextField heightField;
    @FXML
    private TextField weightField;
    @FXML
    private TextField childrenField;
    @FXML
    private TextField birthDateField;
    @FXML
    private TextField birthMonthField;
    @FXML
    private TextField birthYearField;
    @FXML
    private TableView<EmployeeView> employeeViewTable;
    @FXML
    private TableColumn<EmployeeView, Number> employeeIdColumn;
    @FXML
    private TableColumn<EmployeeView, String> employeeNameColumn;
    @FXML
    private TableColumn<EmployeeView, String> employeeSexColumn;
    @FXML
    private TableColumn<EmployeeView, String> deptCodeColumn;
    @FXML
    private TableColumn<EmployeeView, String> branchIdColumn;
    @FXML
    private TableColumn<EmployeeView, String> joiningDateColumn;
    @FXML
    private TableColumn<EmployeeView, String> employeeDesignationColumn;
    
    private ObservableList<Integer> companyNo;
    private ObservableList<EmployeeView> employeeView; 
    @FXML
    private TextField permPhnField;
    
    private String DB_URL = "jdbc:mysql://127.0.0.1/hrmsystemdb";
    private String DB_USER = "root";
    private String DB_PASS = "123";
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        companyNo = FXCollections.observableArrayList();
        companyNo.remove(0, companyNo.size());
        for (int i = 1; i <= 5; i++){
            companyNo.add(i);
        }
        companyNoBox.setItems(companyNo);
        designationBox.getItems().addAll(Designation.values());
        deptCodeBox.getItems().addAll(DepartmentCode.values());
        genderBox.getItems().addAll(Gender.values());
        branchIDBox.getItems().addAll(BranchID.values());
        bankCodeBox.getItems().addAll(BankCode.values());
        religionBox.getItems().addAll(Religion.values());
        maritalStatusBox.getItems().addAll(MaritalStatus.values());
        bloodGroupBox.getItems().addAll(BloodGroup.values());
        
        employeeView = FXCollections.observableArrayList();
        employeeView.remove(0, employeeView.size());
        
        int id = 0;
        String name = null;
        String sex = null;
        String dept_code = null;
        String branch_id = null;
        String joining_date = null;
        String designation = null;
        
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement statement = connection.createStatement();
            
            String query1 = "select * from tbl_offc_info";
            ResultSet result_office_info = statement.executeQuery(query1);
            
            while(result_office_info.next()){
                id = result_office_info.getInt("id");
                name = result_office_info.getString("name");
                sex = result_office_info.getString("gender");                
                dept_code = result_office_info.getString("dept_code");
                branch_id = result_office_info.getString("branch_id");
                joining_date = result_office_info.getString("joining_date");
                designation = result_office_info.getString("designation");
                EmployeeView employee = new EmployeeView(id, name, sex, designation, dept_code, branch_id, joining_date);
                
                employeeView.add(employee); 
            }
            
            employeeViewTable.setItems(employeeView);
            employeeIdColumn.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getId()));
            employeeNameColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getName()));
            employeeSexColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getSex()));
            deptCodeColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getDept_code()));
            branchIdColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getBranch_id()));
            joiningDateColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getJoining_date()));
            employeeDesignationColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getDesignation()));
        } catch (SQLException ex) {
            Logger.getLogger(NewEmployeeUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

    @FXML
    private void handleIDAction(ActionEvent event) {
        int id = Integer.parseInt(idNoField.getText());
        
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement statement = connection.createStatement();
            
            String query1 = "SELECT * from tbl_offc_info;";
            ResultSet result_office_info = statement.executeQuery(query1);
            while(result_office_info.next()){
                int result_id = result_office_info.getInt("id");
                if(result_id == id){
                    String name = result_office_info.getString("name");
                    int company_no = result_office_info.getInt("company_no");
                    String designation = result_office_info.getString("designation");
                    String confirmation_date = result_office_info.getString("confirmation_date");
                    String dept_code = result_office_info.getString("dept_code");
                    String joining_date = result_office_info.getString("joining_date");
                    String gender = result_office_info.getString("gender");
                    String branch_id = result_office_info.getString("branch_id");

                    OfficeInfo office_info = new OfficeInfo(id, name, company_no, designation, confirmation_date, dept_code, joining_date, gender, branch_id);

                    idNoField.setText("" + office_info.getId());
                    employeeNameField.setText(office_info.getName());
                    companyNoBox.getSelectionModel().select(office_info.getCompany_no());
                    designationBox.getSelectionModel().select(Designation.valueOf(office_info.getDesignation()));
                    confirmationDatePicker.getEditor().setText(office_info.getConfirmation_date());
                    deptCodeBox.getSelectionModel().select(DepartmentCode.valueOf(office_info.getDept_code()));
                    joiningDatePicker.getEditor().setText(office_info.getJoining_date());
                    genderBox.getSelectionModel().select(Gender.valueOf(office_info.getGender()));
                    branchIDBox.getSelectionModel().select(BranchID.valueOf(office_info.getBranch_id()));
                    
                    break;
                }
            }
            
            String query2 = "select * from tbl_salary_info";
            ResultSet result_salary_info = statement.executeQuery(query2);
            
            while(result_salary_info.next()){
                int result_id = result_salary_info.getInt("id");
                if(result_id == id){
                    double basic_salary = result_salary_info.getDouble("basic_salary");
//                    String query = "select * from tbl_offc_info where id=" + id + ";";
//                    ResultSet result_join = statement.executeQuery(query);
//                    String joining_date = result_join.getString("joining_date");
                    double running_basic = result_salary_info.getDouble("running_basic");
                    double house_rent = (running_basic * 40) / 100;
                    double medical = (running_basic * 10) / 100;
                    double others = (running_basic * 5) / 100;
                    double conveyance = (running_basic * 5) / 100;
                    double car_allow = (running_basic * 5) / 100;
                    double gross_salary = result_salary_info.getDouble("gross_salary");
                    String bank_code = result_salary_info.getString("bank_code");
                    String ac_no = result_salary_info.getString("account_no");
                    
                    SalaryInfo salary_info = new SalaryInfo(id, basic_salary, gross_salary, house_rent, running_basic, medical, others, conveyance, car_allow, bank_code, ac_no);
                    
                    basicSalaryField.setText("" + salary_info.getBasic_salary());
                    runningBasicField.setText("" + salary_info.getRunning_basic());
                    houseRentField.setText("" + salary_info.getHouse_rent());
                    medicalAllowField.setText("" + salary_info.getMedical());
                    othersAllowField.setText("" + salary_info.getOthers());
                    conveyanceField.setText("" + salary_info.getConveyance());
                    carAllowField.setText("" + salary_info.getCar_allow());
                    grossSalaryField.setText("" + salary_info.getGross_salary());
                    bankCodeBox.getSelectionModel().select(BankCode.valueOf(salary_info.getBank_code()));
                    accountNoField.setText("" + salary_info.getAc_no());
                    
                    break;
                }
            }
            
            String query3 = "select * from tbl_personal_info";
            ResultSet result_per_info = statement.executeQuery(query3);
            
            while(result_per_info.next()){
                int result_id = result_per_info.getInt("id");
                if(result_id == id){
                    String fathers_name = result_per_info.getString("fathers_name");
                    String mothers_name = result_per_info.getString("mothers_name");
                    String religion = result_per_info.getString("religion");
                    String marital_status = result_per_info.getString("marital_status");
                    String date_of_birth = result_per_info.getString("date_of_birth");
                    String spouse = result_per_info.getString("spouse");
                    String blood_group = result_per_info.getString("blood_group");
                    String nationality = result_per_info.getString("nationality");
                    double height = result_per_info.getDouble("height");
                    double weight = result_per_info.getDouble("weight");
                    int children = result_per_info.getInt("children");
                    String edu_quali = result_per_info.getString("edu_quali");
                    String tech_quali = result_per_info.getString("tech_quali");
                    String nid_no = result_per_info.getString("national_id_no");
                    String mobile_no = result_per_info.getString("mobile_no");
                    String birth_place = result_per_info.getString("birth_place");
                    String tin_no = result_per_info.getString("tin_no");
                    String email = result_per_info.getString("email");
                    String passport_no = result_per_info.getString("passport_no");
                    String emer_cont_no = result_per_info.getString("emergency_cont");

                    PersonalInfo personal_info = new PersonalInfo(id, fathers_name, mothers_name, religion, marital_status, date_of_birth, 
                                                     spouse, blood_group, nationality, height, weight, children, edu_quali, tech_quali, 
                                                     nid_no, mobile_no, birth_place, tin_no, email, passport_no, emer_cont_no);
                    
                    fathersNameField.setText(personal_info.getFathers_name());
                    mothersNameField.setText(personal_info.getMothers_name());
                    religionBox.getSelectionModel().select(Religion.valueOf(personal_info.getReligion()));
                    maritalStatusBox.getSelectionModel().select(MaritalStatus.valueOf(personal_info.getMarital_status()));
                    String birth_date_tokens[] = date_of_birth.split("-");
                    birthDateField.setText(birth_date_tokens[0]);
                    birthMonthField.setText(birth_date_tokens[1]);
                    birthYearField.setText(birth_date_tokens[2]);
                    int birth_month = Integer.parseInt(birth_date_tokens[1]);
                    int birth_year = Integer.parseInt(birth_date_tokens[2]);
                    Date months = new Date();
                    SimpleDateFormat month = new SimpleDateFormat("MM");
                    int pres_month = Integer.parseInt(month.format(months));
                 //   System.out.printf("%d\n", pres_month);
                    Date years = new Date();
                    SimpleDateFormat year = new SimpleDateFormat("yyyy");
                    int pres_year = Integer.parseInt(year.format(years));
                    int age;
                    if(birth_month > pres_month){
                        age = pres_year - (birth_year + 1);
                    }
                    else{
                        age = pres_year - birth_year;
                    }
                    ageField.setText("" + age);
                    spouseField.setText(personal_info.getSpouse());
                    bloodGroupBox.getSelectionModel().select(BloodGroup.valueOf(personal_info.getBlood_group()));
                    nationalityField.setText(personal_info.getNationality());
                    heightField.setText("" + personal_info.getHeight());
                    weightField.setText("" + personal_info.getWeight());
                    childrenField.setText("" + personal_info.getChildren());
                    educationalQualificationField.setText(personal_info.getEdu_quali());
                    technicalQualificationField.setText(personal_info.getTech_quali());
                    nationalIdNoField.setText(personal_info.getNid_no());
                    mobileNoField.setText(personal_info.getMobile_no());
                    birthPlaceField.setText(personal_info.getBirth_place());
                    tinNoField.setText(personal_info.getTin_no());
                    emailAddressField.setText(personal_info.getEmail());
                    passportNoField.setText(personal_info.getPassport_no());
                    emergencyContNo.setText(personal_info.getEmer_cont_no());

                    break;
                }
            }
            
            String query4 = "select * from tbl_address";
            ResultSet result_address = statement.executeQuery(query4);
            
            while(result_address.next()){
                int result_id = result_address.getInt("id");
                if(result_id == id){
                    String pres_house_no = result_address.getString("pres_house_no");
                    String pres_road_no = result_address.getString("pres_road_no");
                    String pres_village = result_address.getString("pres_vill");
                    String pres_po = result_address.getString("pres_PO");
                    String pres_ps = result_address.getString("pres_PS");
                    String pres_dist = result_address.getString("pres_dist");
                    String pres_phn = result_address.getString("pres_phn_no");
                    String perm_house_no = result_address.getString("perm_house_no");
                    String perm_road_no = result_address.getString("perm_road_no");
                    String perm_village = result_address.getString("perm_vill");
                    String perm_po = result_address.getString("perm_PO");
                    String perm_ps = result_address.getString("perm_PS");
                    String perm_dist = result_address.getString("perm_dist");
                    String perm_phn = result_address.getString("perm_phn_no");

                    Address address = new Address(id, pres_house_no, pres_road_no, pres_village, pres_po, pres_ps, pres_dist, pres_phn,
                                          perm_house_no, perm_road_no, perm_village, perm_po, perm_ps, perm_dist, perm_phn);
                    
                    presHouseNoField.setText(address.getPres_house_no());
                    presRoadNoField.setText(address.getPres_road_no());
                    presVillageField.setText(address.getPres_village());
                    presPOField.setText(address.getPres_po());
                    presPSField.setText(address.getPres_ps());
                    presDistField.setText(address.getPres_dist());
                    presPhnField.setText(address.getPres_phn());
                    permHouseNoField.setText(address.getPerm_house_no());
                    permRoadNoField.setText(address.getPerm_road_no());
                    permVillageField.setText(address.getPerm_village());
                    permPOField.setText(address.getPerm_po());
                    permPSField.setText(address.getPerm_ps());
                    permDistField.setText(address.getPres_dist());
                    permPhnField.setText(address.getPerm_phn());
                    
                    break;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewEmployeeUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleAddAction(ActionEvent event) {
        int id = Integer.parseInt(idNoField.getText());
        String name = employeeNameField.getText();
        int company_no = Integer.parseInt(companyNoBox.getSelectionModel().getSelectedItem() + "");
        String designation = designationBox.getSelectionModel().getSelectedItem() + "";
        String confirmation_date = confirmationDatePicker.getValue() + "";
        String dept_code = deptCodeBox.getSelectionModel().getSelectedItem() + "";
        String joining_date = joiningDatePicker.getValue() + "";
        String gender = genderBox.getSelectionModel().getSelectedItem() + "";
        String branch_id = branchIDBox.getSelectionModel().getSelectedItem() + "";
        
//        String join_date[] = confirmation_date.split("-");
//        int join_year = Integer.parseInt(join_date[2]);
//        LocalDate date = LocalDate.now();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
//        int pres_year = Integer.parseInt(formatter.format(date));
        
        OfficeInfo office_info = new OfficeInfo(id, name, company_no, designation, confirmation_date, dept_code, joining_date, gender, branch_id);
        
        double basic_salary = Double.parseDouble(basicSalaryField.getText());
        double running_basic = Double.parseDouble(runningBasicField.getText());
        double house_rent = Double.parseDouble(houseRentField.getText());
        double medical = Double.parseDouble(medicalAllowField.getText());
        double others = Double.parseDouble(othersAllowField.getText());
        double conveyance = Double.parseDouble(conveyanceField.getText());
        double car_allow = Double.parseDouble(carAllowField.getText());
        double gross_salary = Double.parseDouble(grossSalaryField.getText());
        String bank_code = bankCodeBox.getSelectionModel().getSelectedItem() + "";
        String ac_no = accountNoField.getText();
        
        SalaryInfo salary_info = new SalaryInfo(id, basic_salary, gross_salary, house_rent, running_basic, medical, others, conveyance, car_allow, bank_code, ac_no);
        
        String fathers_name = fathersNameField.getText();
        String mothers_name = mothersNameField.getText();
        String religion = religionBox.getSelectionModel().getSelectedItem() + "";
        String marital_status = maritalStatusBox.getSelectionModel().getSelectedItem() + "";
        String date_of_birth = birthDateField.getText() + "-" + birthMonthField.getText() + "-" + birthYearField.getText();
        String spouse = spouseField.getText();
        String blood_group = bloodGroupBox.getSelectionModel().getSelectedItem() + "";
        String nationality = nationalityField.getText();
        double height = Double.parseDouble(heightField.getText());
        double weight = Double.parseDouble(weightField.getText());
        int children = Integer.parseInt(childrenField.getText());
        String edu_quali = educationalQualificationField.getText();
        String tech_quali = technicalQualificationField.getText();
        String nid_no = nationalIdNoField.getText();
        String mobile_no = mobileNoField.getText();
        String birth_place = birthPlaceField.getText();
        String tin_no = tinNoField.getText();
        String email = emailAddressField.getText();
        String passport_no = passportNoField.getText();
        String emer_cont_no = emergencyContNo.getText();
        
        PersonalInfo personal_info = new PersonalInfo(id, fathers_name, mothers_name, religion, marital_status, date_of_birth, 
                                         spouse, blood_group, nationality, height, weight, children, edu_quali, tech_quali, 
                                         nid_no, mobile_no, birth_place, tin_no, email, passport_no, emer_cont_no);
        
        String pres_house_no = presHouseNoField.getText();
        String pres_road_no = presRoadNoField.getText();
        String pres_village = presVillageField.getText();
        String pres_po = presPOField.getText();
        String pres_ps = presPSField.getText();
        String pres_dist = presDistField.getText();
        String pres_phn = presPhnField.getText();
        String perm_house_no = permHouseNoField.getText();
        String perm_road_no = permRoadNoField.getText();
        String perm_village = permVillageField.getText();
        String perm_po = permPOField.getText();
        String perm_ps = permPSField.getText();
        String perm_dist = permDistField.getText();
        String perm_phn = permPhnField.getText();
        
        Address address = new Address(id, pres_house_no, pres_road_no, pres_village, pres_po, pres_ps, pres_dist, pres_phn,
                              perm_house_no, perm_road_no, perm_village, perm_po, perm_ps, perm_dist, perm_phn);
        
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement statement = connection.createStatement();
            
        //    String query1 = "insert into tbl_offc_info values(" + office_info.getId() + ", '" + office_info.getName() + "', " + office_info.getCompany_no() + ", '" + office_info.getDesignation() + "', '" + office_info.getConfirmation_date() + "', '" + office_info,getDept_code() + "', '" + office_info.getJoining_date() + "', '" + office_info.getGender() + "', '" + office_info.getBranch_id() + "');";
            String query1 = "insert into tbl_offc_info values(" + office_info.getId() + ", '" + office_info.getName() + "', " 
                            + office_info.getCompany_no() + ", '" + office_info.getDesignation() + "', '" + office_info.getConfirmation_date() + "', '" 
                            + office_info.getDept_code() + "', '" + office_info.getJoining_date() + "', '" + office_info.getGender() + "', '" 
                            + office_info.getBranch_id() + "');";
            String query2 = "insert into tbl_salary_info values(" + salary_info.getId() + ", " + salary_info.getBasic_salary() + ", " 
                            + salary_info.getRunning_basic() + ", " + salary_info.getGross_salary() + ", '" + salary_info.getBank_code() + "', '" 
                            + salary_info.getAc_no() + "');";
            String query3 = "insert into tbl_personal_info values(" + personal_info.getId() + ", '" + personal_info.getFathers_name() + "', '" 
                            + personal_info.getMothers_name() + "', '" + personal_info.getReligion() + "', '" + personal_info.getMarital_status() + "', '" 
                            + personal_info.getDate_of_birth() + "', '" + personal_info.getSpouse() + "', '" + personal_info.getBlood_group() + "', '" 
                            + personal_info.getNationality() + "', " + personal_info.getHeight() + ", " + personal_info.getWeight() + ", " + personal_info.getChildren() + ", '" 
                            + personal_info.getEdu_quali() + "', '" + personal_info.getTech_quali() + "', '" + personal_info.getNid_no() + "', '" 
                            + personal_info.getMobile_no() + "', '" + personal_info.getBirth_place() + "', '" + personal_info.getTin_no() + "', '" 
                            + personal_info.getEmail() + "', '" + personal_info.getPassport_no() + "', '" + personal_info.getEmer_cont_no() + "');";
            String query4 = "insert into tbl_address values(" + address.getId() + ", '" + address.getPres_house_no() + "', '" + address.getPres_road_no() + "', '" 
                            + address.getPres_village() + "', '" + address.getPres_po() + "', '" + address.getPres_ps() + "', '" 
                            + address.getPres_dist() + "', '" + address.getPres_phn() + "', '" + address.getPerm_house_no() + "', '" 
                            + address.getPerm_road_no() + "', '" + address.getPerm_village() + "', '" + address.getPerm_po() + "', '" 
                            + address.getPerm_ps() + "', '" + address.getPerm_dist() + "', '" + address.getPerm_phn() + "');";
            
            statement.executeUpdate(query1);
            statement.executeUpdate(query2);
            statement.executeUpdate(query3);
            statement.executeUpdate(query4);
        } catch (SQLException ex) {
            Logger.getLogger(NewEmployeeUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        EmployeeView employee = new EmployeeView(id, name, gender, designation, dept_code, branch_id, joining_date);
                
        employeeView.add(employee);
        
        idNoField.setText("");
        employeeNameField.setText("");
        companyNoBox.getSelectionModel().clearSelection();
        designationBox.getSelectionModel().clearSelection();
        confirmationDatePicker.getEditor().setText("");
        deptCodeBox.getSelectionModel().clearSelection();
        joiningDatePicker.getEditor().setText("");
        genderBox.getSelectionModel().clearSelection();
        branchIDBox.getSelectionModel().clearSelection();
        
        basicSalaryField.setText("");
        runningBasicField.setText("");
        houseRentField.setText("");
        medicalAllowField.setText("");
        othersAllowField.setText("");
        conveyanceField.setText("");
        carAllowField.setText("");
        grossSalaryField.setText("");
        bankCodeBox.getSelectionModel().clearSelection();
        accountNoField.setText("");
       
        fathersNameField.setText("");
        mothersNameField.setText("");
        religionBox.getSelectionModel().clearSelection();
        maritalStatusBox.getSelectionModel().clearSelection();
        birthDateField.setText("");
        birthMonthField.setText("");
        birthYearField.setText("");
        ageField.setText("");
        spouseField.setText("");
        bloodGroupBox.getSelectionModel().clearSelection();
        nationalityField.setText("");
        heightField.setText("");
        weightField.setText("");
        childrenField.setText("");
        educationalQualificationField.setText("");
        technicalQualificationField.setText("");
        nationalIdNoField.setText("");
        mobileNoField.setText("");
        birthPlaceField.setText("");
        tinNoField.setText("");
        emailAddressField.setText("");
        passportNoField.setText("");
        emergencyContNo.setText("");
               
        presHouseNoField.setText("");
        presRoadNoField.setText("");
        presVillageField.setText("");
        presPOField.setText("");
        presPSField.setText("");
        presDistField.setText("");
        presPhnField.setText("");
        permHouseNoField.setText("");
        permRoadNoField.setText("");
        permVillageField.setText("");
        permPOField.setText("");
        permPSField.setText("");
        permDistField.setText("");
        permPhnField.setText("");
    }   
   
    @FXML
    private void handleUpdateAction(ActionEvent event) {
        int id = Integer.parseInt(idNoField.getText());
        String name = employeeNameField.getText();
        int company_no = Integer.parseInt(companyNoBox.getSelectionModel().getSelectedItem() + "");
        String designation = designationBox.getSelectionModel().getSelectedItem() + "";
        String confirmation_date = confirmationDatePicker.getEditor().getText();
        String dept_code = deptCodeBox.getSelectionModel().getSelectedItem() + "";
        String joining_date = joiningDatePicker.getEditor().getText();
        String gender = genderBox.getSelectionModel().getSelectedItem() + "";
        String branch_id = branchIDBox.getSelectionModel().getSelectedItem() + "";
        
//        String join_date[] = confirmation_date.split("-");
//        int join_year = Integer.parseInt(join_date[2]);
//        LocalDate date = LocalDate.now();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
//        int pres_year = Integer.parseInt(formatter.format(date));
        
        OfficeInfo office_info = new OfficeInfo(id, name, company_no, designation, confirmation_date, dept_code, joining_date, gender, branch_id);
        
        double basic_salary = Double.parseDouble(basicSalaryField.getText());
        double running_basic = Double.parseDouble(runningBasicField.getText());
        double house_rent = Double.parseDouble(houseRentField.getText());
        double medical = Double.parseDouble(medicalAllowField.getText());
        double others = Double.parseDouble(othersAllowField.getText());
        double conveyance = Double.parseDouble(conveyanceField.getText());
        double car_allow = Double.parseDouble(carAllowField.getText());
        double gross_salary = Double.parseDouble(grossSalaryField.getText());
        String bank_code = bankCodeBox.getSelectionModel().getSelectedItem() + "";
        String ac_no = accountNoField.getText();
        
        SalaryInfo salary_info = new SalaryInfo(id, basic_salary, gross_salary, house_rent, running_basic, medical, others, conveyance, car_allow,  bank_code, ac_no);
        
        String fathers_name = fathersNameField.getText();
        String mothers_name = mothersNameField.getText();
        String religion = religionBox.getSelectionModel().getSelectedItem() + "";
        String marital_status = maritalStatusBox.getSelectionModel().getSelectedItem() + "";
        String date_of_birth = birthDateField.getText() + "-" + birthMonthField.getText() + "-" + birthYearField.getText();
        String spouse = spouseField.getText();
        String blood_group = bloodGroupBox.getSelectionModel().getSelectedItem() + "";
        String nationality = nationalityField.getText();
        double height = Double.parseDouble(heightField.getText());
        double weight = Double.parseDouble(weightField.getText());
        int children = Integer.parseInt(childrenField.getText());
        String edu_quali = educationalQualificationField.getText();
        String tech_quali = technicalQualificationField.getText();
        String nid_no = nationalIdNoField.getText();
        String mobile_no = mobileNoField.getText();
        String birth_place = birthPlaceField.getText();
        String tin_no = tinNoField.getText();
        String email = emailAddressField.getText();
        String passport_no = passportNoField.getText();
        String emer_cont_no = emergencyContNo.getText();
        
        PersonalInfo personal_info = new PersonalInfo(id, fathers_name, mothers_name, religion, marital_status, date_of_birth, 
                                         spouse, blood_group, nationality, height, weight, children, edu_quali, tech_quali, 
                                         nid_no, mobile_no, birth_place, tin_no, email, passport_no, emer_cont_no);
        
        String pres_house_no = presHouseNoField.getText();
        String pres_road_no = presRoadNoField.getText();
        String pres_village = presVillageField.getText();
        String pres_po = presPOField.getText();
        String pres_ps = presPSField.getText();
        String pres_dist = presDistField.getText();
        String pres_phn = presPhnField.getText();
        String perm_house_no = permHouseNoField.getText();
        String perm_road_no = permRoadNoField.getText();
        String perm_village = permVillageField.getText();
        String perm_po = permPOField.getText();
        String perm_ps = permPSField.getText();
        String perm_dist = permDistField.getText();
        String perm_phn = permPhnField.getText();
        
        Address address = new Address(id, pres_house_no, pres_road_no, pres_village, pres_po, pres_ps, pres_dist, pres_phn,
                              perm_house_no, perm_road_no, perm_village, perm_po, perm_ps, perm_dist, perm_phn);
        
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement statement = connection.createStatement();
            
        //    String query1 = "insert into tbl_offc_info values(" + office_info.getId() + ", '" + office_info.getName() + "', " + office_info.getCompany_no() + ", '" + office_info.getDesignation() + "', '" + office_info.getConfirmation_date() + "', '" + office_info,getDept_code() + "', '" + office_info.getJoining_date() + "', '" + office_info.getGender() + "', '" + office_info.getBranch_id() + "');";
            String query1 = "update tbl_offc_info set name='" + office_info.getName() + "', company_no=" + office_info.getCompany_no() + ", designation='" + office_info.getDesignation() + 
                            "', confirmation_date='" + office_info.getConfirmation_date() + "', dept_code='" + office_info.getDept_code() + "', joining_date='" + office_info.getJoining_date() + 
                            "', gender='" + office_info.getGender() + "', branch_id='" + office_info.getBranch_id() + "' where id=" + office_info.getId() + ";";
            String query2 = "update tbl_salary_info set basic_salary=" + salary_info.getBasic_salary() + ", running_basic=" + salary_info.getRunning_basic() + 
                            ", gross_salary=" + salary_info.getGross_salary() + ", bank_code='" + salary_info.getBank_code() + 
                            "', account_no='" + salary_info.getAc_no() + "' where id=" + salary_info.getId() + ";";
            String query3 = "update tbl_personal_info set fathers_name='" + personal_info.getFathers_name() + 
                            "', mothers_name='"  + personal_info.getMothers_name() + "', religion='" + personal_info.getReligion() + "', marital_status='" + personal_info.getMarital_status() + 
                            "', date_of_birth='"  + personal_info.getDate_of_birth() + "', spouse='" + personal_info.getSpouse() + "', blood_group='" + personal_info.getBlood_group() + 
                            "', nationality='"  + personal_info.getNationality() + "', height=" + personal_info.getHeight() + ", weight=" + personal_info.getWeight() + 
                            ", children=" + personal_info.getChildren() + ", edu_quali='" + personal_info.getEdu_quali() + "', tech_quali='" + personal_info.getTech_quali() + 
                            "', national_id_no='" + personal_info.getNid_no() + "', mobile_no='" + personal_info.getMobile_no() + "', birth_place='" + personal_info.getBirth_place() + 
                            "', tin_no='" + personal_info.getTin_no() + "', email='"   + personal_info.getEmail() + "', passport_no='" + personal_info.getPassport_no() + 
                            "', emergency_cont='" + personal_info.getEmer_cont_no() + "' where id=" + personal_info.getId() + ";";
            String query4 = "update tbl_address set pres_house_no='" + address.getPres_house_no() + "', pres_road_no='" + address.getPres_road_no() + "', pres_vill='" + address.getPres_village() + 
                            "', pres_PO='" + address.getPres_po() + "', pres_PS='" + address.getPres_ps() + "', pres_dist='" + address.getPres_dist() + "', pres_phn_no='" + address.getPres_phn() + 
                            "', perm_house_no='" + address.getPerm_house_no() + "', perm_road_no='" + address.getPerm_road_no() + "', perm_vill='" + address.getPerm_village() + "', perm_PO='" + address.getPerm_po() + 
                            "', perm_PS='" + address.getPerm_ps() + "', perm_dist='" + address.getPerm_dist() + "', perm_phn_no='" + address.getPerm_phn() + "' where id=" + address.getId() +";";
            
            statement.executeUpdate(query1);
            statement.executeUpdate(query2);
            statement.executeUpdate(query3);
            statement.executeUpdate(query4);
        } catch (SQLException ex) {
            Logger.getLogger(NewEmployeeUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        idNoField.setText("");
        employeeNameField.setText("");
        companyNoBox.getSelectionModel().clearSelection();
        designationBox.getSelectionModel().clearSelection();
        confirmationDatePicker.getEditor().setText("");
        deptCodeBox.getSelectionModel().clearSelection();
        joiningDatePicker.getEditor().setText("");
        genderBox.getSelectionModel().clearSelection();
        branchIDBox.getSelectionModel().clearSelection();
        
        basicSalaryField.setText("");
        runningBasicField.setText("");
        houseRentField.setText("");
        medicalAllowField.setText("");
        othersAllowField.setText("");
        conveyanceField.setText("");
        carAllowField.setText("");
        grossSalaryField.setText("");
        bankCodeBox.getSelectionModel().clearSelection();
        accountNoField.setText("");
       
        fathersNameField.setText("");
        mothersNameField.setText("");
        religionBox.getSelectionModel().clearSelection();
        maritalStatusBox.getSelectionModel().clearSelection();
        birthDateField.setText("");
        birthMonthField.setText("");
        birthYearField.setText("");
        ageField.setText("");
        spouseField.setText("");
        bloodGroupBox.getSelectionModel().clearSelection();
        nationalityField.setText("");
        heightField.setText("");
        weightField.setText("");
        childrenField.setText("");
        educationalQualificationField.setText("");
        technicalQualificationField.setText("");
        nationalIdNoField.setText("");
        mobileNoField.setText("");
        birthPlaceField.setText("");
        tinNoField.setText("");
        emailAddressField.setText("");
        passportNoField.setText("");
        emergencyContNo.setText("");
               
        presHouseNoField.setText("");
        presRoadNoField.setText("");
        presVillageField.setText("");
        presPOField.setText("");
        presPSField.setText("");
        presDistField.setText("");
        presPhnField.setText("");
        permHouseNoField.setText("");
        permRoadNoField.setText("");
        permVillageField.setText("");
        permPOField.setText("");
        permPSField.setText("");
        permDistField.setText("");
        permPhnField.setText("");
    }

    @FXML
    private void handleRemoveAction(ActionEvent event) {
        int id = Integer.parseInt(idNoField.getText());
        
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement statement = connection.createStatement();
            
            String query1 = "delete from tbl_offc_info where id=" + id + ";";
            String query2 = "delete from tbl_salary_info where id=" + id + ";";
            String query3 = "delete from tbl_personal_info where id=" + id + ";";
            String query4 = "delete from tbl_address where id=" + id + ";";
            
            statement.executeUpdate(query1);
            statement.executeUpdate(query2);
            statement.executeUpdate(query3);
            statement.executeUpdate(query4);
        } catch (SQLException ex) {
            Logger.getLogger(NewEmployeeUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        idNoField.setText("");
        employeeNameField.setText("");
        companyNoBox.getSelectionModel().clearSelection();
        designationBox.getSelectionModel().clearSelection();
        confirmationDatePicker.getEditor().setText("");
        deptCodeBox.getSelectionModel().clearSelection();
        joiningDatePicker.getEditor().setText("");
        genderBox.getSelectionModel().clearSelection();
        branchIDBox.getSelectionModel().clearSelection();
        
        basicSalaryField.setText("");
        runningBasicField.setText("");
        houseRentField.setText("");
        medicalAllowField.setText("");
        othersAllowField.setText("");
        conveyanceField.setText("");
        carAllowField.setText("");
        grossSalaryField.setText("");
        bankCodeBox.getSelectionModel().clearSelection();
        accountNoField.setText("");
       
        fathersNameField.setText("");
        mothersNameField.setText("");
        religionBox.getSelectionModel().clearSelection();
        maritalStatusBox.getSelectionModel().clearSelection();
        birthDateField.setText("");
        birthMonthField.setText("");
        birthYearField.setText("");
        ageField.setText("");
        spouseField.setText("");
        bloodGroupBox.getSelectionModel().clearSelection();
        nationalityField.setText("");
        heightField.setText("");
        weightField.setText("");
        childrenField.setText("");
        educationalQualificationField.setText("");
        technicalQualificationField.setText("");
        nationalIdNoField.setText("");
        mobileNoField.setText("");
        birthPlaceField.setText("");
        tinNoField.setText("");
        emailAddressField.setText("");
        passportNoField.setText("");
        emergencyContNo.setText("");
               
        presHouseNoField.setText("");
        presRoadNoField.setText("");
        presVillageField.setText("");
        presPOField.setText("");
        presPSField.setText("");
        presDistField.setText("");
        presPhnField.setText("");
        permHouseNoField.setText("");
        permRoadNoField.setText("");
        permVillageField.setText("");
        permPOField.setText("");
        permPSField.setText("");
        permDistField.setText("");
        permPhnField.setText("");
    }

    @FXML
    private void handleNewAction(ActionEvent event) {
        idNoField.setText("");
        employeeNameField.setText("");
        companyNoBox.getSelectionModel().clearSelection();
        designationBox.getSelectionModel().clearSelection();
        confirmationDatePicker.getEditor().setText("");
        deptCodeBox.getSelectionModel().clearSelection();
        joiningDatePicker.getEditor().setText("");
        genderBox.getSelectionModel().clearSelection();
        branchIDBox.getSelectionModel().clearSelection();
        
        basicSalaryField.setText("");
        runningBasicField.setText("");
        houseRentField.setText("");
        medicalAllowField.setText("");
        othersAllowField.setText("");
        conveyanceField.setText("");
        carAllowField.setText("");
        grossSalaryField.setText("");
        bankCodeBox.getSelectionModel().clearSelection();
        accountNoField.setText("");
       
        fathersNameField.setText("");
        mothersNameField.setText("");
        religionBox.getSelectionModel().clearSelection();
        maritalStatusBox.getSelectionModel().clearSelection();
        birthDateField.setText("");
        birthMonthField.setText("");
        birthYearField.setText("");
        ageField.setText("");
        spouseField.setText("");
        bloodGroupBox.getSelectionModel().clearSelection();
        nationalityField.setText("");
        heightField.setText("");
        weightField.setText("");
        childrenField.setText("");
        educationalQualificationField.setText("");
        technicalQualificationField.setText("");
        nationalIdNoField.setText("");
        mobileNoField.setText("");
        birthPlaceField.setText("");
        tinNoField.setText("");
        emailAddressField.setText("");
        passportNoField.setText("");
        emergencyContNo.setText("");
               
        presHouseNoField.setText("");
        presRoadNoField.setText("");
        presVillageField.setText("");
        presPOField.setText("");
        presPSField.setText("");
        presDistField.setText("");
        presPhnField.setText("");
        permHouseNoField.setText("");
        permRoadNoField.setText("");
        permVillageField.setText("");
        permPOField.setText("");
        permPSField.setText("");
        permDistField.setText("");
        permPhnField.setText("");
    }

    @FXML
    private void handleSalaryAction(ActionEvent event) {
        double basic_salary = Double.parseDouble(basicSalaryField.getText());
        runningBasicField.setText("");
        houseRentField.setText("");
        medicalAllowField.setText("");
        othersAllowField.setText("");
        conveyanceField.setText("");
        carAllowField.setText("");
        grossSalaryField.setText("");
        
        String join_date = joiningDatePicker.getEditor().getText();
        System.out.printf("%s\n", join_date);
        String join_date_tokens[] = join_date.split("/");
        int join_year = Integer.parseInt(join_date_tokens[2]);
        int join_month = Integer.parseInt(join_date_tokens[1]);
      //  System.out.printf("%d", join_year);
        Date get_year = new Date();
        SimpleDateFormat years = new SimpleDateFormat("yyyy");
        int pres_year = Integer.parseInt(years.format(get_year));
        Date get_months = new Date();
        SimpleDateFormat months = new SimpleDateFormat("MM");
        int pres_month = Integer.parseInt(months.format(get_months));
      //  System.out.printf("%d", pres_year);
        int job_years = 0;
        if(join_month > pres_month){
            job_years = pres_year - (join_year + 1);
        }
        else{
            job_years = pres_year - join_year;
        }
        
        double running_basic = 0.0;
        if(job_years > 0){
        running_basic = basic_salary + (basic_salary * job_years  * (5.0 / 100));
        }
        else{
            running_basic = basic_salary;
        }
        runningBasicField.setText("" + running_basic);
      //  running_basic = Double.parseDouble(runningBasicField.getText());
        double house_rent = (running_basic * 40) / 100;
        houseRentField.setText("" + house_rent);
        double medical = (running_basic * 10) / 100;
        medicalAllowField.setText("" + medical);
        double others = (running_basic * 5) / 100;
        othersAllowField.setText("" + others);
        double conveyance = (running_basic * 5) / 100;
        conveyanceField.setText("" + conveyance);
        double car_allow = (running_basic * 5) / 100;
        carAllowField.setText("" + car_allow);
        double gross_salary = running_basic + house_rent + medical + others + conveyance + car_allow;
        grossSalaryField.setText("" + gross_salary);
    }

    @FXML
    private void handleAgeAction(ActionEvent event) {
        int birth_year = Integer.parseInt(birthYearField.getText());
        int birth_month = Integer.parseInt(birthMonthField.getText());
        Date months = new Date();
        SimpleDateFormat month = new SimpleDateFormat("MM");
        int pres_month = Integer.parseInt(month.format(months));
     //   System.out.printf("%d\n", pres_month);
        Date years = new Date();
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        int pres_year = Integer.parseInt(year.format(years));
        int age;
        if(birth_month > pres_month){
            age = pres_year - (birth_year + 1);
        }
        else{
            age = pres_year - birth_year;
        }
        ageField.setText("" + age);
    }

    @FXML
    private void handleSelectEmployeeAction(MouseEvent event) {
        EmployeeView employee = employeeViewTable.getSelectionModel().getSelectedItem();
        int id = employee.getId();
      //  double basic_salary;
        String join_date = employee.getJoining_date();
        String join_date_tokens[] = join_date.split("-");
        int join_year = Integer.parseInt(join_date_tokens[0]);
        int join_month = Integer.parseInt(join_date_tokens[1]);
      //  System.out.printf("%d", join_year);
        Date get_year = new Date();
        SimpleDateFormat years = new SimpleDateFormat("yyyy");
        int pres_year = Integer.parseInt(years.format(get_year));
        Date get_months = new Date();
        SimpleDateFormat months = new SimpleDateFormat("MM");
        int pres_month = Integer.parseInt(months.format(get_months));
      //  System.out.printf("%d", pres_year);
        
        
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement statement = connection.createStatement();
            
            String query1 = "SELECT * from tbl_offc_info;";
            ResultSet result_office_info = statement.executeQuery(query1);
            while(result_office_info.next()){
                int result_id = result_office_info.getInt("id");
                if(result_id == id){
                    String name = result_office_info.getString("name");
                    int company_no = result_office_info.getInt("company_no");
                    String designation = result_office_info.getString("designation");
                    String confirmation_date = result_office_info.getString("confirmation_date");
                    String dept_code = result_office_info.getString("dept_code");
                    String joining_date = result_office_info.getString("joining_date");
                    String gender = result_office_info.getString("gender");
                    String branch_id = result_office_info.getString("branch_id");

                    OfficeInfo office_info = new OfficeInfo(id, name, company_no, designation, confirmation_date, dept_code, joining_date, gender, branch_id);

                    idNoField.setText("" + office_info.getId());
                    employeeNameField.setText(office_info.getName());
                    companyNoBox.getSelectionModel().select(office_info.getCompany_no());
                    designationBox.getSelectionModel().select(Designation.valueOf(office_info.getDesignation()));
                    confirmationDatePicker.getEditor().setText(office_info.getConfirmation_date());
                    deptCodeBox.getSelectionModel().select(DepartmentCode.valueOf(office_info.getDept_code()));
                    joiningDatePicker.getEditor().setText(office_info.getJoining_date());
                    genderBox.getSelectionModel().select(Gender.valueOf(office_info.getGender()));
                    branchIDBox.getSelectionModel().select(BranchID.valueOf(office_info.getBranch_id()));
                    
                    
                    break;
                }
            }
            
            String query2 = "select * from tbl_salary_info";
            ResultSet result_salary_info = statement.executeQuery(query2);
            
            while(result_salary_info.next()){
                int result_id = result_salary_info.getInt("id");
                if(result_id == id){
                 //   double basic_salary = 0;
                    double basic_salary = result_salary_info.getDouble("basic_salary");
                 //   double running_basic = 0;
                    double running_basic = result_salary_info.getDouble("running_basic");
                    double house_rent = (running_basic * 40) / 100;
                    double medical = (running_basic * 10) / 100;
                    double others = (running_basic * 5) / 100;
                    double conveyance = (running_basic * 5) / 100;
                    double car_allow = (running_basic * 5) / 100;
                    double gross_salary = running_basic + house_rent + medical + others + conveyance + car_allow;
                    String bank_code = result_salary_info.getString("bank_code");
                    String ac_no = result_salary_info.getString("account_no");
                    
                    SalaryInfo salary_info = new SalaryInfo(id, basic_salary, gross_salary, house_rent, running_basic, medical, others, conveyance, car_allow, bank_code, ac_no);
                    
                    basicSalaryField.setText("" + salary_info.getBasic_salary());
                    runningBasicField.setText("" + salary_info.getRunning_basic());
                    houseRentField.setText("" + salary_info.getHouse_rent());
                    medicalAllowField.setText("" + salary_info.getMedical());
                    othersAllowField.setText("" + salary_info.getOthers());
                    conveyanceField.setText("" + salary_info.getConveyance());
                    carAllowField.setText("" + salary_info.getCar_allow());
                    grossSalaryField.setText("" + salary_info.getGross_salary());
                    bankCodeBox.getSelectionModel().select(BankCode.valueOf(salary_info.getBank_code()));
                    accountNoField.setText("" + salary_info.getAc_no());
                    
                    break;
                }
            }
            
            String query3 = "select * from tbl_personal_info";
            ResultSet result_per_info = statement.executeQuery(query3);
            
            while(result_per_info.next()){
                int result_id = result_per_info.getInt("id");
                if(result_id == id){
                    String fathers_name = result_per_info.getString("fathers_name");
                    String mothers_name = result_per_info.getString("mothers_name");
                    String religion = result_per_info.getString("religion");
                    String marital_status = result_per_info.getString("marital_status");
                    String date_of_birth = result_per_info.getString("date_of_birth");
                    String spouse = result_per_info.getString("spouse");
                    String blood_group = result_per_info.getString("blood_group");
                    String nationality = result_per_info.getString("nationality");
                    double height = result_per_info.getDouble("height");
                    double weight = result_per_info.getDouble("weight");
                    int children = result_per_info.getInt("children");
                    String edu_quali = result_per_info.getString("edu_quali");
                    String tech_quali = result_per_info.getString("tech_quali");
                    String nid_no = result_per_info.getString("national_id_no");
                    String mobile_no = result_per_info.getString("mobile_no");
                    String birth_place = result_per_info.getString("birth_place");
                    String tin_no = result_per_info.getString("tin_no");
                    String email = result_per_info.getString("email");
                    String passport_no = result_per_info.getString("passport_no");
                    String emer_cont_no = result_per_info.getString("emergency_cont");

                    PersonalInfo personal_info = new PersonalInfo(id, fathers_name, mothers_name, religion, marital_status, date_of_birth, 
                                                     spouse, blood_group, nationality, height, weight, children, edu_quali, tech_quali, 
                                                     nid_no, mobile_no, birth_place, tin_no, email, passport_no, emer_cont_no);
                    
                    fathersNameField.setText(personal_info.getFathers_name());
                    mothersNameField.setText(personal_info.getMothers_name());
                    religionBox.getSelectionModel().select(Religion.valueOf(personal_info.getReligion()));
                    maritalStatusBox.getSelectionModel().select(MaritalStatus.valueOf(personal_info.getMarital_status()));
                    String birth_date_tokens[] = date_of_birth.split("-");
                    birthDateField.setText(birth_date_tokens[0]);
                    birthMonthField.setText(birth_date_tokens[1]);
                    birthYearField.setText(birth_date_tokens[2]);
                    int birth_month = Integer.parseInt(birth_date_tokens[1]);
                    int birth_year = Integer.parseInt(birth_date_tokens[2]);
//                    Date months = new Date();
//                    SimpleDateFormat month = new SimpleDateFormat("MM");
//                    int pres_month = Integer.parseInt(month.format(months));
//                 //   System.out.printf("%d\n", pres_month);
//                    Date years = new Date();
//                    SimpleDateFormat year = new SimpleDateFormat("yyyy");
//                    int pres_year = Integer.parseInt(year.format(years));
                    int age;
                    if(birth_month > pres_month){
                        age = pres_year - (birth_year + 1);
                    }
                    else{
                        age = pres_year - birth_year;
                    }
                    ageField.setText("" + age);
                    spouseField.setText(personal_info.getSpouse());
                    bloodGroupBox.getSelectionModel().select(BloodGroup.valueOf(personal_info.getBlood_group()));
                    nationalityField.setText(personal_info.getNationality());
                    heightField.setText("" + personal_info.getHeight());
                    weightField.setText("" + personal_info.getWeight());
                    childrenField.setText("" + personal_info.getChildren());
                    educationalQualificationField.setText(personal_info.getEdu_quali());
                    technicalQualificationField.setText(personal_info.getTech_quali());
                    nationalIdNoField.setText(personal_info.getNid_no());
                    mobileNoField.setText(personal_info.getMobile_no());
                    birthPlaceField.setText(personal_info.getBirth_place());
                    tinNoField.setText(personal_info.getTin_no());
                    emailAddressField.setText(personal_info.getEmail());
                    passportNoField.setText(personal_info.getPassport_no());
                    emergencyContNo.setText(personal_info.getEmer_cont_no());

                    break;
                }
            }
            
            String query4 = "select * from tbl_address";
            ResultSet result_address = statement.executeQuery(query4);
            
            while(result_address.next()){
                int result_id = result_address.getInt("id");
                if(result_id == id){
                    String pres_house_no = result_address.getString("pres_house_no");
                    String pres_road_no = result_address.getString("pres_road_no");
                    String pres_village = result_address.getString("pres_vill");
                    String pres_po = result_address.getString("pres_PO");
                    String pres_ps = result_address.getString("pres_PS");
                    String pres_dist = result_address.getString("pres_dist");
                    String pres_phn = result_address.getString("pres_phn_no");
                    String perm_house_no = result_address.getString("perm_house_no");
                    String perm_road_no = result_address.getString("perm_road_no");
                    String perm_village = result_address.getString("perm_vill");
                    String perm_po = result_address.getString("perm_PO");
                    String perm_ps = result_address.getString("perm_PS");
                    String perm_dist = result_address.getString("perm_dist");
                    String perm_phn = result_address.getString("perm_phn_no");

                    Address address = new Address(id, pres_house_no, pres_road_no, pres_village, pres_po, pres_ps, pres_dist, pres_phn,
                                          perm_house_no, perm_road_no, perm_village, perm_po, perm_ps, perm_dist, perm_phn);
                    
                    presHouseNoField.setText(address.getPres_house_no());
                    presRoadNoField.setText(address.getPres_road_no());
                    presVillageField.setText(address.getPres_village());
                    presPOField.setText(address.getPres_po());
                    presPSField.setText(address.getPres_ps());
                    presDistField.setText(address.getPres_dist());
                    presPhnField.setText(address.getPres_phn());
                    permHouseNoField.setText(address.getPerm_house_no());
                    permRoadNoField.setText(address.getPerm_road_no());
                    permVillageField.setText(address.getPerm_village());
                    permPOField.setText(address.getPerm_po());
                    permPSField.setText(address.getPerm_ps());
                    permDistField.setText(address.getPres_dist());
                    permPhnField.setText(address.getPerm_phn());
                    
                    break;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewEmployeeUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleBackAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("MenuPanelUI.fxml"));
            
            Scene scene = new Scene(root);
            
            HRMSystem.getMainStage().setScene(scene);
            HRMSystem.getMainStage().show();
        } catch (IOException ex) {
            Logger.getLogger(MenuPanelUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
