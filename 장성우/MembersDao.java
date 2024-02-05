package project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.proxy.annotation.Pre;
//import project.vo.CustomVo;
import project.vo.MembersVo;

public class MembersDao {
                                      
    public static final String URL = "jdbc:oracle:thin:@//localhost:1521/xe";
    public static final String USERNAME = "c##idev";
    private static final String PASSWORD = "1234";


    // public static final String URL = "jdbc:oracle:thin:@//localhost:51521/xe";
    // public static final String USERNAME = "jacob";
    // private static final String PASSWORD = "oracle";

 private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }


    //회원가입
    public void join(MembersVo vo){
        String sql = "insert into tbl_members(code,name,email,phone_number,age)" +
        " values(?,?,?,?,?)"; // insert

         try ( // auto close
                Connection connection = getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);) {
                    
                    boolean run= true;
                    while(run){
                    System.out.print("아이디를 입력하세요__");
                    String code = System.console().readLine();

                        //아이디 중복검사 
                        if(!memberCheck(code)){
                            System.out.println("해당 아이디는 사용가능합니다.!");
                        
                        
                            System.out.print("이름을 입력하세요__");
                            String name = System.console().readLine();
                            
                            System.out.print("이메일을 입력하세요__");
                            String email = System.console().readLine();
                            
                            System.out.print("전화번호를 입력하세요__");
                            String pn = System.console().readLine();
        
                            System.out.print("나이를 입력하세요__");
                            int age = Integer.parseInt(System.console().readLine());
                            
                            pstmt.setString(1, code);
                            pstmt.setString(2, name);
                            pstmt.setString(3, email);
                            pstmt.setString(4, pn);
                            pstmt.setInt(5, age);
                 
                        // 매개변수 바인딩
                            pstmt.executeUpdate();
                            run=false;
                        }else{
                            System.out.println("해당 아이디는 사용이 불가합니다. 다시 입력해주세요!");
                           // run= false;                
                        }
                    }

        } catch (SQLException e) {
                System.out.println("회원가입 실행 예외발생"+e.getMessage());
        }


    }
    //회원유무 파악
    public boolean memberCheck(String customid){
        String sql = "select * from tbl_members where code = ?";
        boolean check = false;

        try (Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(sql);) 
        {
            pstmt.setString(1, customid);
            ResultSet rs= pstmt.executeQuery();
            if(rs.next()){
                check = true;
               
            };  
        } catch (SQLException e) {
            System.out.println("모든회원정보 조회 실행 예외 발생" + e.getMessage());
            e.printStackTrace();
        }
        return check;
    }




    //회원정보 수정 
    public void modify(MembersVo vo) {
        String sql = "UPDATE TBL_members SET EMAIL =?,phone_number=? WHERE code =?";

        try ( // auto close
                Connection connection = getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);) {
            pstmt.setString(1, vo.getEmail());
            pstmt.setString(2, vo.getPhoneNumber());
            pstmt.setString(3, vo.getCode());

            // 매개변수 바인딩
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("회원정보 수정 실행 예외 발생 "+e.getMessage());
            // close 는 자동
        }

    }

    // 회원 탈퇴
    // public void delete(String code) {
    //     String sql = " DELETE FROM TBL_members WHERE code = ?";

    //     try ( // auto close
    //             Connection connection = getConnection();
    //             PreparedStatement pstmt = connection.prepareStatement(sql);) {
    //         pstmt.setString(1, code);
    //         pstmt.executeUpdate();

    //     } catch (SQLException e) {
    //         // close 는 자동
    //         System.out.println("회원탈퇴 실행 예외 발생"+e.getMessage());
    //     }

    // }



    //회원 정보가져오기
    public  MembersVo getMembersVo(String code){

        MembersVo vo = null;
        
        String sql = "select * from tbl_members where code =?";

        try (
            Connection connection= getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)
        ) {
            pstmt.setString(1, code);
            ResultSet rs= pstmt.executeQuery();
            if(rs.next()){
                vo = new MembersVo(rs.getString(1),
                                   rs.getString(2),
                                   rs.getString(3),
                                   rs.getString(4), 
                                   rs.getInt(5));

                                   System.out.println(vo);
            }else{
                System.out.println("해당아이디 정보가 없습니다.");
            }
        } catch (SQLException e) {
            System.out.println("getMembersVo() 실행 예외 발생" + e.getMessage());
            e.printStackTrace();
        }
            return vo;
    }

    // 관리자를 위한 기능 : 모든 회원정보 조회
    public List<MembersVo> allMember(){

            List<MembersVo> list = new ArrayList<>();
            String sql = "select * from tbl_members";

            try (
                Connection connection = getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql)
            ) {
                ResultSet rs = pstmt.executeQuery();
                while(rs.next()){
                        list.add(new MembersVo(rs.getString(1), 
                                               rs.getString(2), 
                                               rs.getString(3), 
                                               rs.getString(4), 
                                               rs.getInt(5)));

                                            }
                                            System.out.println(list);

            } catch (SQLException e) {
                System.out.println("모든회원정보 조회 실행 예외 발생" + e.getMessage());
                e.printStackTrace();
            }
            return list;
    }



}
