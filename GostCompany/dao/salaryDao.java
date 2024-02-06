package GostCompany.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import GostCompany.vo.EmployeeVo;
import GostCompany.vo.SalaryVo;

public class salaryDao extends BaseDao {
    EmployeeVo EmployeeVo = new EmployeeVo(null, null, null, null);

    //Salary 테이블 select문 by id
    public List<SalaryVo> selectMySalaryDataByEm_id(String sl_id){
        List<SalaryVo> list = new ArrayList<>();
        String sql="SELECT * FROM TBL_Salary ts WHERE sl_id =?";
        try (
                Connection conn = this.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);

        ) {
            ps.setString(1, sl_id);
            ResultSet rs= ps.executeQuery();

                while(rs.next()){
                   
                    list.add(new SalaryVo(sl_id,
                                          rs.getString("sl_position"),
                                          rs.getInt("sl_commition"),
                                          rs.getDate("sl_date")));     
                }
        } catch (SQLException e) {
            System.out.println(" select문 실행 예외 발생 : " + e.getMessage());
        }

        return list;
    } 

      //Salary 테이블 select문 by Position
      public List<SalaryVo> selectMySalaryDataByEm_position(String sl_position){
        List<SalaryVo> list = new ArrayList<>();
        String sql="SELECT * FROM TBL_Salary ts WHERE sl_position =?";
        try (
                Connection conn = this.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);

        ) {
            ps.setString(1, sl_position);
            ResultSet rs= ps.executeQuery();

                while(rs.next()){
                   
                    list.add(new SalaryVo(rs.getString("sl_id"),
                                          sl_position,
                                          rs.getInt("sl_commition"),
                                          rs.getDate("sl_date")));     
                }
        } catch (SQLException e) {
            System.out.println(" select문 실행 예외 발생 : " + e.getMessage());
        }

        return list;
    } 

    
      //Salary 테이블 select문 by commition
      public List<SalaryVo> selectMySalaryDataByEm_commition(int sl_commition){
        List<SalaryVo> list = new ArrayList<>();
        String sql="SELECT * FROM TBL_Salary ts WHERE sl_commition =?";
        try (
                Connection conn = this.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)

        ) {
            ps.setInt(1, sl_commition);
            ResultSet rs= ps.executeQuery();

                while(rs.next()){
                   
                    list.add(new SalaryVo(rs.getString("sl_id"),
                                          rs.getString("sl_position"),
                                         sl_commition,
                                          rs.getDate("sl_date")));     
                }
        } catch (SQLException e) {
            System.out.println(" select문 실행 예외 발생 : " + e.getMessage());
        }

        return list;
    } 


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

    public void allowanceCalculate(){


    }

    
    //급여= 기본급여 + 초과근무 수당
    public void allowance(String work_id){
        String sql =" SELECT  (ROUND((SL_COMMITION)/20/8)) * (tw.WORK_TIME-8) * 1.5"
        +"FROM TBL_SALARY ts" 
        +"JOIN TBL_WORK tw "
        +"ON ts.SL_ID = tw.WORK_ID"
        +"WHERE tw.WORK_ID =?";

        try (
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
        ){
            pstmt.setString(1, work_id);
      
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("초과근무 수당계산  실행 예외 발생"+e.getMessage());
        }


    }


     //재계약(아이디로 커미션,월급날짜 조정)
     public boolean contractRenewal(String sl_commition,Date sl_date, String sl_id){
        boolean result= false;
        String sql="UPDATE TBL_SALARY SET SL_COMMITION =? sl_date =? WHERE SL_ID =?";
        try (   
                Connection connection = getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);
            ) {
                pstmt.setString(1, sl_commition);
                pstmt.setDate(2, sl_date);
                pstmt.setString(3, sl_id);
                
                if(pstmt.executeUpdate()>0){
                        result = true;
                }
            

        } catch (SQLException e) {
            System.out.println("재계약(아이디,커미션,월급날짜 조정) 회원가입 실행 예외 발생"+e.getMessage());
        }
        return result;
    }

    //재계약(아이디로 커미션 조정)
    public boolean contractRenewal(String sl_commition, String sl_id){
        boolean result= false;
        String sql="UPDATE TBL_SALARY SET SL_COMMITION =? WHERE SL_ID =?";
        try (   
                Connection connection = getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);
            ) {
                pstmt.setString(1, sl_commition);
                pstmt.setString(2, sl_id);
                
                if(pstmt.executeUpdate()>0){
                        result = true;
                }
            

        } catch (SQLException e) {
            System.out.println("재계약(아이디로 커미션 조정) 실행 예외 발생"+e.getMessage());

        }
        return result;
    }



         //재계약(아이디로 급여지급날짜 조정)
    public boolean contractRenewal(Date sl_date, String sl_id){
        boolean result= false;
        String sql="UPDATE TBL_SALARY SET SL_COMMITION =? WHERE SL_ID =?";
        try (   
                Connection connection = getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);
            ) {
                pstmt.setDate(1, sl_date);
                pstmt.setString(2, sl_id);
                
                if(pstmt.executeUpdate()>0){
                        result = true;
                }
            

        } catch (SQLException e) {
            System.out.println("재계약(아이디로 급여지급날짜 조정) 실행 예외 발생"+e.getMessage());

        }
        return result;


    }


}
