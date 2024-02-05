package GostCompany.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import GostCompany.vo.EmployeeVo;

public class salaryDao extends BaseDao {
    EmployeeVo EmployeeVo = new EmployeeVo(null, null, null, null);


    // 사원 번호로 월급 정보 출력
    public void salaryDay(String em_id){
        String sql="SELECT SL_ID,SL_COMMITION FROM TBL_SALARY WHERE SL_ID = ?";
        try (   
                Connection connection = getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);
            ) {
                pstmt.setString(1, em_id);
                ResultSet rs = pstmt.executeQuery();
                if(rs.next()){
                        EmployeeVo=new EmployeeVo(rs.getString(1),
                                          rs.getString(2),
                                          rs.getString(3),
                                          rs.getDate(4));
                }
                System.out.println(EmployeeVo);
            
        } catch (SQLException e) {
        }

       
    }


    
    //급여= 근무시간 + 수당
    public void allowance(){
        String sql="";
        try (   
                Connection connection = getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);
            ) {
                
            
        } catch (SQLException e) {
        }
    }


    //재계약(급여 조정)
    public void contractRenewal(){
        String sql="";
        try (   
                Connection connection = getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);
            ) {
                
            
        } catch (SQLException e) {
        }

    }


}
