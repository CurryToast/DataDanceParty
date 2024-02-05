package project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project.vo.BuyMenuVo;
import project.vo.MemberBuyVo;
import project.vo.MenuVo;

public class BuyMenuDao {
    
    public static final String URL = "jdbc:oracle:thin:@//localhost:1521/xe";
    public static final String USERNAME = "C##idev";
    private static final String PASSWORD = "1234";


    // public static final String URL = "jdbc:oracle:thin:@//localhost:51521/xe";
    // public static final String USERNAME = "jacob";
    // private static final String PASSWORD = "oracle";

    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    // 메뉴받기 구매하기 
    public int buy(BuyMenuVo vo){
        String sql ="insert into tbl_buy_menu(buy_Idx,customer_id,menu_id,menu_quantity,buy_date)" 
        +" values(BURGER_SEQ.nextval,?,?,?,sysdate)";

        int result=0;
        try (
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);

        ) {
            pstmt.setString(1, vo.getCustomerId());
            pstmt.setString(2, vo.getMenuId());
            pstmt.setInt(3, vo.getMenuQuantity());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("구매하기 실행 예외 발생"+e.getMessage());
        }
        return result;

    }

    
    // 구매취소
    public int delete(int buy_Idx){
        String sql=" DELETE FROM TBL_BUY_menu  WHERE BUY_IDX = ?";
        int result =0;
        
        try (
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ) {
                pstmt.setInt(1, buy_Idx);
                result = pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("구매취소 실행 예외발생"+e.getMessage());
        }
        
        return result;
        
    }
    
    //메뉴 수정하기 
    public int modify(BuyMenuVo vo){
        String sql="UPDATE TBL_BUY_menu SET menu_QUANTITY =? WHERE BUY_Index =?";
        int result =0;
        
        try (
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ) {
                pstmt.setInt(1, vo.getMenuQuantity());
                pstmt.setInt(2, vo.getBuyIdx());

                result =pstmt.executeUpdate();
                
            } catch (SQLException e) {
                System.out.println("구매변경 실행 예외발생"+e.getMessage());
            }
            return result;
        }
        // 구매목록
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
        
        //mypage 기능
        public List<MemberBuyVo> selectMemberBuy(String customer_id) {
            List<MemberBuyVo> list = new ArrayList<>();
            String sql = 
            "SELECT BUY_INDEX ,CUSTOMER_ID ,tm.MNAME ,tm.MCODE ,TBM.MENU_QUANTITY ,tm.mprice,TBM.BUY_DATE" +
            " FROM TBL_BUY_MENU tbm" +
            " JOIN TBL_MENU tm" +
            " ON tbm.MENU_ID =tm.MCODE" + 
            " WHERE TBM.CUSTOMER_ID =?";
            
            try (
                Connection connection = getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql)) {
                    pstmt.setString(1, customer_id);
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        list.add(new MemberBuyVo(rs.getInt(1),
                                                rs.getString(2) , 
                                                rs.getString(3), 
                                                rs.getString(4), 
                                                rs.getInt(5), 
                                                rs.getInt(6),
                                                rs.getTimestamp(7)));
                    }
                    
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return list;
                
            }
            //장바구니 한번에 insert 실행
            public int allAtOnes(List<BuyMenuVo> cart){
                String sql = "insert into tbl_buy_menu values(burger_seq.nextval,?,?,?,sysdate)";
                Connection connection =null;
                PreparedStatement pstmt = null;
                int count=0;
                try {
                    connection= getConnection();
                    pstmt = connection.prepareStatement(sql);
                    connection.setAutoCommit(false);    
                    for(BuyMenuVo vo: cart){
                            
                            pstmt.setString(1, vo.getCustomerId());
                            pstmt.setString(2, vo.getMenuId());
                            pstmt.setInt(3, vo.getMenuQuantity());
                            pstmt.addBatch();
                            count++;
                    }
                    pstmt.executeBatch();
                    connection.commit();
        
                } catch (SQLException e) {
                    try {
                        connection.rollback();
                    } catch (SQLException e1) {
                    }
                    count = -1;
                    System.out.println("구매불가능한 상품이 있습니다.");
                    System.out.println("장바구니 구매 실행 예외 발생"+e.getMessage());
                }finally{
                    try {
                        pstmt.close();
                        connection.close();
                    } catch (Exception e) {
                    }
                }
                return count; 
        
        
            }
        }
        