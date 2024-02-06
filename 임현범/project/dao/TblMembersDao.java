package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project.vo.MembersVo;

public class TblMembersDao extends TeamDao {
    public Boolean signin(String code) {
        boolean isSigninSuccess = false;
        String sql = "SELECT CODE FROM TBL_MEMBERS WHERE CODE = ?";

        try (
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                isSigninSuccess = true;
            }
        } catch (SQLException e) {
            System.out.println("로그인 실패 : " + e.getMessage());
        }

        return isSigninSuccess;
    }

    public int signup(MembersVo vo) {
        int result = 0;
        String sql = "INSERT INTO tbl_members \r\n" + //
                "values(?,?,?,?,?)";

        try (
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, vo.getCode());
            ps.setString(2, vo.getName());
            ps.setString(3, vo.getEmail());
            ps.setString(4, vo.getPhoneNumber());
            ps.setInt(5, vo.getAge());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("회원가입 실패 : " + e.getMessage());
        }

        return result;
    }

    public MembersVo selectMyInfo(String customerId) {
        String sql = "SELECT tm.*, tbm.total,\r\n" + //
                "   CASE \r\n" + //
                "     WHEN tbm.total BETWEEN 1 AND 10 THEN '일반'\r\n" + //
                "     WHEN tbm.total BETWEEN 11 AND 20 THEN '우수'\r\n" + //
                "     WHEN tbm.total > 20 THEN 'VIP'\r\n" + //
                "     ELSE '기타'\r\n" + //
                "   END AS MEMBERSHIP\r\n" + //
                "FROM TBL_MEMBERS tm\r\n" + //
                "JOIN (\r\n" + //
                "   SELECT CUSTOMER_ID, sum(MENU_QUANTITY) total\r\n" + //
                "   FROM TBL_BUY_MENU\r\n" + //
                "   WHERE to_char(BUY_DATE, 'yyyy') = to_char(SYSDATE, 'yyyy')\r\n" + //
                "   GROUP BY CUSTOMER_ID\r\n" + //
                ") tbm ON tbm.CUSTOMER_ID = tm.CODE\r\n" + //
                "WHERE tm.CODE = ?";
        MembersVo vo = null;

        try (
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, customerId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            vo = new MembersVo(
                rs.getString("CODE"),
                rs.getString("NAME"),
                rs.getString("EMAIL"),
                rs.getString("PHONE_NUMBER"),
                rs.getInt("AGE"),
                rs.getString("MEMBERSHIP")
            );
        } catch (SQLException e) {
            System.out.println("개인정보 조회 실패 : " + e.getMessage());
        }

        return vo;
    }

    public int update(MembersVo vo) {
        int result = 0;
        List<String> buffer = new ArrayList<>();

        StringBuffer sql = new StringBuffer("UPDATE tbl_members SET ");
        if (vo.getName().length() > 0) {
            sql.append("NAME = ?,");
            buffer.add(vo.getName());
        }

        if (vo.getEmail().length() > 0) {
            sql.append("EMAIL = ?,");
            buffer.add(vo.getEmail());
        }

        if (vo.getPhoneNumber().length() > 0) {
            sql.append("PHONE_NUMBER = ? ");
            buffer.add(vo.getPhoneNumber());
        }

        sql.deleteCharAt(sql.length() - 1).append(" WHERE CODE = ?");


        try (
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql.toString());
        ) {
            for (int i = 0; i < buffer.size(); i++) {
                ps.setString(i + 1, buffer.get(i));
            }
            ps.setString(buffer.size() + 1, vo.getCode());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("개인정보 변경 실패 : " + e.getMessage());
        } catch (IndexOutOfBoundsException e1) {
            System.out.println("인덱스 오류 : " + e1.getMessage());
        }

        return result;
    }
}
