package GostCompany.main;

import GostCompany.dao.BaseDao;
import GostCompany.dao.EmployeeDao;
import GostCompany.dao.WorkDao;

public class GhostCompanyApp {
    private BaseDao baseDao = new BaseDao();
    private EmployeeDao employeeDao = new EmployeeDao();
    private WorkDao workDao = new WorkDao();

    public static void main(String[] args) {
        GhostCompanyApp app = new GhostCompanyApp();
        app.start();
    }


    private void showMenu() {
        System.out.println(".".repeat(70));
        System.out.println("");
        System.out.println("");
        System.out.println(".".repeat(70));
    }

    private void start() {
        showMenu();

        System.out.print("메뉴 입력 >> ");
        String menu = System.console().readLine().toUpperCase();

        boolean run = true;
        while (run) {
            switch (menu) {
                case "A":
                    break;
                case "X":
                    run = false;
                    break;
                default:
                    break;
            }
        }
    }
}
