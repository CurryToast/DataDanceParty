package project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import project.vo.MembersVo;
import project.vo.MenuVo;
import project.vo.ProductVo;

public class MenuDao {
    public static final String URL="jdbc:oracle:thin:@//localhost:1521/xe";
    public static final String USERNAME = "c##idev";
    private static final String PASSWORD = "1234";

    // public static final String URL = "jdbc:oracle:thin:@//localhost:51521/xe";
    // public static final String USERNAME = "jacob";
    // private static final String PASSWORD = "oracle";

private Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL,USERNAME,PASSWORD);
    }



    //1) 특정 Mcode에 대한 상품 목록 보여주기
    public List<MenuVo> selectByMcode(String Mcode){
        List<MenuVo> list = new ArrayList<>();
        String sql = "SELECT * FROM TBL_menu  WHERE mcode like '%'||?||'%' order by mcode";
        

        try (
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)
        ) {
            pstmt.setString(1, Mcode);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(new MenuVo(rs.getString(1), 
                                    rs.getString(2), 
                                    rs.getInt(3),
                                    rs.getString(4)));
            }
        } catch (SQLException e) {
            System.out.println("selectByMcode 예외 발생" + e.getMessage());
        }
        return list;
    }
    //2) 특정 Mname에 대한 상품 목록 보여주기 & 상품명 유사(like) 검색
    public List<MenuVo> selectByMname(String Mname){
        List<MenuVo> list = new ArrayList<>();
        String sql = "SELECT * FROM TBL_menu  WHERE mname like '%'||?||'%' order by mname";
        

        try (
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)
        ) {
            pstmt.setString(1, Mname);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(new MenuVo(rs.getString(1), 
                                    rs.getString(2), 
                                    rs.getInt(3),
                                    rs.getString(4)));
            }
        } catch (SQLException e) {
            System.out.println("selectByMname 예외 발생" + e.getMessage());
        }
        return list;
    }

    // 카테고리로 보여주기 
    public List<MenuVo> selectByCategory(String category){
        List<MenuVo> list = new ArrayList<>();
        String sql = "SELECT * FROM TBL_menu  WHERE category = ? order by category";
        
        try (
                Connection connection = getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql)
            ) {
                pstmt.setString(1,category);
                 ResultSet rs = pstmt.executeQuery();
                   while (rs.next()) {  //  조회결과는 n행 가능성 예측
                    list.add(new MenuVo(rs.getString(1), 
                                        rs.getString(2), 
                                        rs.getInt(3), category));
                }
        } catch (SQLException e) {
            System.out.println("selectByCategory 예외 발생" + e.getMessage());
        }
        return list;
    }
    public List<MenuVo> allMenuList(){
            List<MenuVo> list = new ArrayList<>();
            String sql = "select * from tbl_menu";

            try (
                Connection connection = getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql)
            ) {
                ResultSet rs = pstmt.executeQuery();
                while(rs.next()){
                       list.add(new MenuVo(rs.getString(1),
                                           rs.getString(2),
                                           rs.getInt(3),
                                           rs.getString(4)));
                }

                System.out.println(list);
            } catch (SQLException e) {
                System.out.println("모든 메뉴정보 조회 실행 예외 발생" + e.getMessage());
                e.printStackTrace();
            }
            return list;
      
    }

   
    public Map<String, Integer> getMpriceTable(){
        Map<String, Integer> map = new HashMap<>();
        String sql ="SELECT mcode,mprice from tbl_menu";

        try (
            Connection conn = getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql)
        ) {
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                map.put(rs.getString(1),rs.getInt(2));
            }
            
        } catch (SQLException e) {
            System.out.println("getMPriceTable 예외발생 : "+e.getMessage());
            e.printStackTrace();
        }
        return map;

    }
    

}
