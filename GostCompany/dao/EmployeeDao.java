package GostCompany.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;

import GostCompany.vo.EmployeeVo;

public class EmployeeDao extends BaseDao {
    

    //사원 등록 
    public void join(EmployeeVo vo){
        String sql="insert into tbl_emplyee values(?,?,?,?)";
        try (   
                Connection connection = getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);
            ) {

                
                System.out.print("사원번호를 입력하세요__");
                String em_id = System.console().readLine();
                
                System.out.print("이름을 입력하세요__");
                String em_name = System.console().readLine();

                System.out.print("직급을 입력하세요__");
                String em_position = System.console().readLine();

                System.out.print("입사날짜를 입력하세요__");
                String em_join_date = System.console().readLine();


                pstmt.setString(1, em_id);
                pstmt.setString(2, em_name);
                pstmt.setString(3, em_position);
                pstmt.setString(4, em_join_date);

                pstmt.executeUpdate();


            
        } catch (SQLException e) {
            System.out.println("회원가입 실행 예외 발생"+e.getMessage());
        }
    }



    // 사원 정보 수정 (직급,,,이름정도)
    public void modify(EmployeeVo vo){
        String sql="UPDATE TBL_EMPLOYEE SET em_name =?,em_position=? WHERE em_id =?";
        try (   
                Connection connection = getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);
            ) {

                System.out.print("사원번호를 입력하세요__");
                String em_id = System.console().readLine();

                System.out.print("이름을 입력하세요__");
                String em_name = System.console().readLine();

                System.out.print("직급을 입력하세요__");
                String em_position = System.console().readLine();

                pstmt.setString(1, em_name);
                pstmt.setString(2, em_position);
                pstmt.setString(3, em_id);
            
                pstmt.executeUpdate();

        } catch (SQLException e) {
        }
    }


    //사원 삭제 
    public void delete(){
        String sql=" DELETE FROM TBL_EMPLOYEE WHERE code = ?";
        try (   
                Connection connection = getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);
            ) {
                System.out.print("삭제할 사원번호를 입력하세요__");
                String em_id = System.console().readLine();
            
                pstmt.setString(1, em_id);

                pstmt.executeUpdate();

        } catch (SQLException e) {
        }
    }
    

}

