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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author iftekher
 */
public class SalaryDetailsUIController implements Initializable {
    @FXML
    private TableView<SalaryInfo> salaryDetailsTable;
    @FXML
    private TableColumn<SalaryInfo, Number> employeeIdColumn;
    @FXML
    private TableColumn<SalaryInfo, Number> basicSalaryColumn;
    @FXML
    private TableColumn<SalaryInfo, Number> runningBasicColumn;
    @FXML
    private TableColumn<SalaryInfo, Number> houseRentColumn;
    @FXML
    private TableColumn<SalaryInfo, Number> medicalColumn;
    @FXML
    private TableColumn<SalaryInfo, Number> otherColumn;
    @FXML
    private TableColumn<SalaryInfo, Number> conveyanceColumn;
    @FXML
    private TableColumn<SalaryInfo, Number> carAllowColumn;
    @FXML
    private TableColumn<SalaryInfo, Number> grossSalaryColumn;
    @FXML
    private TableColumn<SalaryInfo, String> bankCodeColumn;
    @FXML
    private TableColumn<SalaryInfo, String> acNoColumn;
    
    private ObservableList<SalaryInfo> salaries;
    
    private String DB_URL = "jdbc:mysql://127.0.0.1/hrmsystemdb";
    private String DB_USER = "root";
    private String DB_PASS = "123";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        salaries = FXCollections.observableArrayList();
        salaries.remove(0, salaries.size());
        
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement statement = connection.createStatement();
            
            String query = "select * from tbl_salary_info";
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()){
                int id = result.getInt("id");
                double basic_salary = result.getDouble("basic_salary");
                double gross_salary = result.getDouble("gross_salary");
                double running_basic = result.getDouble("running_basic");
                double house_rent = (running_basic * 40) / 100;
                double medical = (running_basic * 10) / 100;
                double others = (running_basic * 5) / 100;
                double conveyance = (running_basic * 5) / 100;
                double car_allow = (running_basic * 5) / 100;
                String bank_code = result.getString("bank_code");
                String ac_no = result.getString("account_no");
                
                SalaryInfo salary_info = new SalaryInfo(id, basic_salary, gross_salary, house_rent, running_basic, medical, others, conveyance, car_allow, bank_code, ac_no);
                salaries.add(salary_info);

            }
            
            salaryDetailsTable.setItems(salaries);
            employeeIdColumn.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getId()));
            basicSalaryColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getBasic_salary()));
            runningBasicColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getRunning_basic()));
            houseRentColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getHouse_rent()));
            medicalColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getMedical()));
            otherColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getOthers()));
            conveyanceColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getConveyance()));
            carAllowColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getCar_allow()));
            grossSalaryColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getGross_salary()));
            bankCodeColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getBank_code()));
            acNoColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getAc_no()));
        } catch (SQLException ex) {
            Logger.getLogger(SalaryDetailsUIController.class.getName()).log(Level.SEVERE, null, ex);
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

    @FXML
    private void handleUpdateSalaryAction(MouseEvent event) {
        if(event.isShiftDown()){
            SalaryInfo salary_info = salaryDetailsTable.getSelectionModel().getSelectedItem();
            salaries.remove(salary_info);
            int id = salary_info.getId();
            double basic_salary = salary_info.getBasic_salary();
            String join_date = "";
            try {
                Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                Statement statement = connection.createStatement();

                String query = "select * from tbl_offc_info";
                ResultSet result = statement.executeQuery(query);

                while(result.next()){
                    if(id == result.getInt("id")){
                        join_date = result.getString("joining_date");
                    }
                }
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
                int job_years = 0;
                if(join_month > pres_month){
                    job_years = pres_year - (join_year + 1);
                }
                else{
                    job_years = pres_year - join_year;
                }

                double running_basic;
                if(job_years > 0){
                    running_basic = basic_salary + (basic_salary * job_years  * (5.0 / 100));
                }
                else{
                    running_basic = basic_salary;
                }
                double house_rent = (running_basic * 40) / 100;
                double medical = (running_basic * 10) / 100;
                double others = (running_basic * 5) / 100;
                double conveyance = (running_basic * 5) / 100;
                double car_allow = (running_basic * 5) / 100;
                double gross_salary = running_basic + house_rent + medical + others + conveyance + car_allow;
                String bank_code = salary_info.getBank_code();
                String ac_no = salary_info.getAc_no();

                salary_info = new SalaryInfo(id, basic_salary, gross_salary, house_rent, running_basic, medical, others, conveyance, car_allow, bank_code, ac_no);
                String query2 = "update tbl_salary_info set basic_salary=" + salary_info.getBasic_salary() + ", running_basic=" + salary_info.getRunning_basic() + 
                        ", gross_salary=" + salary_info.getGross_salary() + ", bank_code='" + salary_info.getBank_code() + 
                        "', account_no='" + salary_info.getAc_no() + "' where id=" + salary_info.getId() + ";";
                statement.executeUpdate(query2);
                salaries.add(salary_info);

                salaryDetailsTable.setItems(salaries);
                employeeIdColumn.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getId()));
                basicSalaryColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getBasic_salary()));
                runningBasicColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getRunning_basic()));
                houseRentColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getHouse_rent()));
                medicalColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getMedical()));
                otherColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getOthers()));
                conveyanceColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getConveyance()));
                carAllowColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getCar_allow()));
                grossSalaryColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getGross_salary()));
                bankCodeColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getBank_code()));
                acNoColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getAc_no()));
            } catch (SQLException ex) {
                Logger.getLogger(SalaryDetailsUIController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
