package project.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import project.vo.MenuVo;

public class TblMenuDao extends TeamDao {
    public List<MenuVo> selectMenuByName(String menuName) {
        List<MenuVo> list = new ArrayList<>();
        String sql = "SELECT * FROM TBL_MENU WHERE MNAME LIKE '%' || ? || '%'";

        try (
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, menuName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new MenuVo(
                    rs.getString("mcode"),
                    rs.getString("mname"),
                    rs.getInt("mprice"),
                    rs.getString("category")
                ));
            }
        } catch (SQLException e) {
            System.out.println("메뉴 조회 실패 : " + e.getMessage());
        }

        return list;
    }

    public List<String> selectCategories() {
        List<String> categories = new ArrayList<>();
        String sql = "SELECT DISTINCT CATEGORY FROM TBL_MENU";

        try (
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                categories.add(rs.getString("CATEGORY"));
            }
        } catch (SQLException e) {
            System.out.println("카테고리 목록 조회 실패 : " + e.getMessage());
        }

        return categories;
    }

    public List<MenuVo> selectMenuByCategory(String category) {
        List<MenuVo> list = new ArrayList<>();
        String sql = "SELECT * FROM TBL_MENU WHERE CATEGORY = ?";

        try (
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, category);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new MenuVo(
                    rs.getString("mcode"),
                    rs.getString("mname"),
                    rs.getInt("mprice"),
                    rs.getString("category")
                ));
            }
        } catch (SQLException e) {
            System.out.println("메뉴 조회 실패 : " + e.getMessage());
        }

        return list;
    }

    public List<MenuVo> selectMenuByPrice(int priceStart, int priceEnd) {
        List<MenuVo> list = new ArrayList<>();
        String sql = "select * from tbl_menu where mprice between ? and ?";

        try (
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, priceStart);
            ps.setInt(2, priceEnd);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new MenuVo(
                    rs.getString("mcode"),
                    rs.getString("mname"),
                    rs.getInt("mprice"),
                    rs.getString("category")
                ));
            }
        } catch (SQLException e) {
            System.out.println("메뉴 조회 실패 : " + e.getMessage());
        }

        return list;
    }

    public Map<String, Integer> getPriceTable() {
        String sql = "SELECT MCODE, MPRICE FROM TBL_MENU";
        Map<String, Integer> priceMap = new HashMap<String, Integer>();

        try (
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                priceMap.put(
                    rs.getString("mcode"),
                    Integer.valueOf(rs.getString("mprice"))
                );
            }
        } catch (SQLException e) {
            System.out.println("가격표 조회 실패 : " + e.getMessage());
        }

        return priceMap;
    } 
}
