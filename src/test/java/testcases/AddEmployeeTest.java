package testcases;

import org.testng.annotations.Test;
import pages.AddEmployeePage;
import pages.DashBoardPage;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;
import utils.Constants;
import utils.ExcelReading;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeeTest extends CommonMethods {

    @Test(groups = "smoke")
    public void addEmployee(){
        //login function
        LoginPage loginpage = new LoginPage();
        loginpage.login(ConfigReader.getPropertyValue("username"), ConfigReader.getPropertyValue("password"));

        DashBoardPage dashBoardPage = new DashBoardPage();
        click(dashBoardPage.pimOption);
        click(dashBoardPage.addEmployeeButton);

        //add employee page
        AddEmployeePage addEmployeePage = new AddEmployeePage();
        sendText(addEmployeePage.firtName, "test90901");
        sendText(addEmployeePage.middleName, "9090test1");
        sendText(addEmployeePage.lastName, "test90test901");
        click(addEmployeePage.saveBtn);
    }

    @Test
    public void addMultipleEmployees(){
        //login
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigReader.getPropertyValue("username"), ConfigReader.getPropertyValue("password"));
//navigate to add employee page
        DashBoardPage dashBoardPage = new DashBoardPage();
        AddEmployeePage addEmployeePage =new AddEmployeePage();

        List<Map<String, String>> newEmployees = ExcelReading.excelIntoListMap(Constants.TESTDATA_FILEPATH, "EmployeeData");
        Iterator<Map<String, String>> it = newEmployees.iterator();
        while (it.hasNext()){
            click(dashBoardPage.pimOption);
            click(dashBoardPage.addEmployeeButton);
            Map<String, String> mapNewEmployee = it.next();
            sendText(addEmployeePage.firtName, mapNewEmployee.get("FirstName"));
            sendText(addEmployeePage.middleName, mapNewEmployee.get("MiddleName"));
            sendText(addEmployeePage.lastName, mapNewEmployee.get("LastName"));

            sendText(addEmployeePage.photograph, mapNewEmployee.get("Photograph"));
            //select checkbox

            if(!addEmployeePage.createLoginCheckBox.isSelected()){
               click(addEmployeePage.createLoginCheckBox);
            }

            //provide credentials for user
            sendText(addEmployeePage.createUsername, mapNewEmployee.get("Username"));
            sendText(addEmployeePage.createPassword, mapNewEmployee.get("Password"));
            sendText(addEmployeePage.rePassword, mapNewEmployee.get("Password"));
            click(addEmployeePage.saveBtn);
        }

    }
}
