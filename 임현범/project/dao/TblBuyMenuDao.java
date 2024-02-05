package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project.vo.BuyMenuVo;

public class TblBuyMenuDao extends TeamDao {
    public List<BuyMenuVo> selectMyBuyList(String customerid) {
        List<BuyMenuVo> list = new ArrayList<BuyMenuVo>();
        String sql = "SELECT * FROM TBL_BUY_MENU WHERE CUSTOMER_ID = ?";

        try (
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, customerid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new BuyMenuVo(
                    rs.getInt("buy_index"),
                    rs.getString("customer_id"),
                    rs.getString("menu_id"),
                    rs.getInt("menu_quantity"),
                    rs.getDate("buy_date")
                ));
            }
        } catch (SQLException e) {
            System.out.println("구매내역 조회 실패 : " + e.getMessage());
        }

        return list;
    }

    public void insert(BuyMenuVo vo) {
        String sql = "INSERT INTO TBL_BUY_MENU\r\n" + //
                "VALUES (burger_seq.nextval, ?, ?, ?, sysdate)";
        
        try (
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, vo.getCustomerId());
            ps.setString(2, vo.getMenuId());
            ps.setInt(3, vo.getMenuQuantity());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("구매 실패 : " + e.getMessage());
        }
    }

    public int insertMany(List<BuyMenuVo> cart) {
        int count = 0;
        String sql = "INSERT INTO TBL_BUY_MENU\r\n" + //
            "VALUES (burger_seq.nextval, ?, ?, ?, ? sysdate)";
        
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = this.getConnection();
            ps = conn.prepareStatement(sql);
            conn.setAutoCommit(false);

            for (BuyMenuVo vo : cart) {
                ps.setString(1, vo.getCustomerId());
                ps.setString(2, vo.getMenuId());
                ps.setInt(3, vo.getMenuQuantity());
                ps.addBatch();
                count++;
            }

            ps.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            try { conn.rollback(); } catch (Exception e1) {}
            count = -1;
            System.out.println("구매 불가능한 상품이 존재합니다! " + e.getMessage());
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException e2) {}
        }

        return count;
    }

    public void delete(int idx) {
        String sql = "DELETE FROM TBL_BUY_MENU WHERE buy_index = ?";

        try (
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, idx);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("구매 취소 실패 : " + e.getMessage());
        }
    }

    public void update(int idx, int quantity) {
        String sql = "UPDATE TBL_BUY_MENU SET QUANTITY = ? WHERE buy_index = ?";

        try (
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, quantity);
            ps.setInt(2, idx);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("수량 변경 실패 : " + e.getMessage());
        }
    }
}
