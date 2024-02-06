package GostCompany.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import GostCompany.vo.EmployeeVo;


public class EmployeeDao extends BaseDao {
    
    //Employee 테이블 select문 by id
    public List<EmployeeVo> selectMyEmployeeDataByEm_id(String em_id){
        List<EmployeeVo> list = new ArrayList<>();
        String sql="SELECT * FROM TBL_EMPLOYEE te WHERE EM_ID =?";
        try (
                Connection conn = this.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);

        ) {
            ps.setString(1, em_id);
            ResultSet rs= ps.executeQuery();

                while(rs.next()){
                    list.add(new EmployeeVo(rs.getString("em_name"), 
                                            rs.getString("em_position"), 
                                            em_id,
                                            rs.getDate("em_join_date")));       
                }
        } catch (SQLException e) {
            System.out.println(" select문 실행 예외 발생 : " + e.getMessage());
        }

        return list;
    } 
        //Employee 테이블 select문 by name
    public List<EmployeeVo> selectMyEmJoinDataByEm_name(String em_name){
        List<EmployeeVo> list = new ArrayList<>();
        String sql="SELECT * FROM TBL_EMPLOYEE te WHERE Em_name =?";
        try (
                Connection conn = this.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);

        ) {
            ps.setString(1, em_name);
            ResultSet rs= ps.executeQuery();

                while(rs.next()){
                    list.add(new EmployeeVo(em_name, 
                                            rs.getString("em_position"), 
                                            rs.getString("em_id"), 
                                            rs.getDate("em_join_date")));       
                }
        } catch (SQLException e) {
            System.out.println(" select문 실행 예외 발생 : " + e.getMessage());
        }

        return list;
    } 


    //Employee 테이블 select문 by position
    public List<EmployeeVo> selectMyEmJoinDataByEm_position(String em_positon){
        List<EmployeeVo> list = new ArrayList<>();
        String sql="SELECT * FROM TBL_EMPLOYEE te WHERE EM_ID =?";
        try (
                Connection conn = this.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);

        ) {
            ps.setString(1, em_positon);
            ResultSet rs= ps.executeQuery();

                while(rs.next()){
                    list.add(new EmployeeVo(rs.getString("em_name"), 
                                            em_positon, 
                                            rs.getString("em_id"),
                                            rs.getDate("em_join_date")));       
                }
        } catch (SQLException e) {
            System.out.println(" select문 실행 예외 발생 : " + e.getMessage());
        }

        return list;
    } 








    //사원 등록1 (아이디,이름,직급,입사날짜)
    public boolean join(String em_id, String em_name, String em_position, Date em_join_date){
        boolean result=false;
        String sql="insert into tbl_emplyee values(?,?,?,?)";
        try (   
                Connection connection = this.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);
            ) {
                pstmt.setString(1, em_id);
                pstmt.setString(2, em_name);
                pstmt.setString(3, em_position);
                pstmt.setDate(4, em_join_date);

                if(pstmt.executeUpdate()>0){
                    result = true;
            }
            
        } catch (SQLException e) {
            System.out.println("사원등록(아이디,이름,직급,입사날짜) 실행 예외 발생"+e.getMessage());
        }
        return result;
    }

    //사원등록2 (아이디,이름, 직급)
    public boolean join(String em_id, String em_name, String em_position){
        boolean result=false;
        String sql="insert into TBL_EMPLOYEE (em_id,em_name,em_position) values(?,?,?)";
        try (   
                Connection connection = this.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);
            ) {
                pstmt.setString(1, em_id);
                pstmt.setString(2, em_name);
                pstmt.setString(3, em_position);
  
                if(pstmt.executeUpdate()>0){
                    result = true;
            }            
        } catch (SQLException e) {
            System.out.println("사원등록(아이디,이름,직급) 회원가입 실행 예외 발생"+e.getMessage());
        }
        return result;
    }

      //사원등록2 (아이디,이름)
      public boolean join(String em_id, String em_name ){
        boolean result=false;
        String sql="insert into TBL_EMPLOYEE (em_id,em_name) values(?,?)";
        try (   
                Connection connection = this.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);
            ) {
                pstmt.setString(1, em_id);
                pstmt.setString(2, em_name);
                
                if(pstmt.executeUpdate()>0){
                        result = true;
                }
            
        } catch (SQLException e) {
            System.out.println("사원등록(아이디,이름) 회원가입 실행 예외 발생"+e.getMessage());
        }
        return result;
    }




    // 사원 정보 수정 (이름 ,직급 수정)
    public boolean modify(String em_name, String em_position,String em_id){
        boolean result= false;
        String sql="UPDATE TBL_EMPLOYEE SET em_name =?,em_position=? WHERE em_id =?";
        try (   
                Connection connection = getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);
            ) {
                pstmt.setString(1, em_name);
                pstmt.setString(2, em_position);
                pstmt.setString(3, em_id);
            
                if(pstmt.executeUpdate()>0){
                    result = true;
            }
        } catch (SQLException e) {
            System.out.println("사원 정보 수정 실행 예외 발생"+e.getMessage());
        }
        return result;
    }

     // 사원 정보 수정 (이름 수정)
     public boolean modify_em_name(String em_name,String em_id){
        boolean result= false;
        String sql="UPDATE TBL_EMPLOYEE SET em_name =? WHERE em_id =?";
        try (   
                Connection connection = getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);
            ) {
                pstmt.setString(1, em_name);
                pstmt.setString(2, em_id);
            
                if(pstmt.executeUpdate()>0){
                    result = true;
            }
        } catch (SQLException e) {
            System.out.println("사원 정보 수정 실행 예외 발생"+e.getMessage());
        }
        return result;
    }

     // 사원 정보 수정 (직급 수정)
     public boolean modify_em_position(String em_position,String em_id){
        boolean result= false;
        String sql="UPDATE TBL_EMPLOYEE SET em_position=? WHERE em_id =?";
        try (   
                Connection connection = getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);
            ) {
                pstmt.setString(1, em_position);
                pstmt.setString(2, em_id);
            
                if(pstmt.executeUpdate()>0){
                    result = true;
            }
        } catch (SQLException e) {
            System.out.println("사원 정보 수정 실행 예외 발생"+e.getMessage());
        }
        return result;
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

