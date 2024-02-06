package GostCompany.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import GostCompany.vo.WorkVo;

public class WorkDao extends BaseDao {
    public List<WorkVo> selectMyWorkType(String id) {
        List<WorkVo> list = new ArrayList<>();
        String sql = "SELECT * FROM TBL_WORK WHERE WORK_ID = ?";

        try (
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new WorkVo(
                    rs.getString("WORK_ID"),
                    rs.getString("WORK_TYPE"),
                    rs.getInt("WORK_TIME")
                ));
            }
        } catch (SQLException e) {
        }

        return list;
    }

    public List<WorkVo> select(String workType) {
        List<WorkVo> list = new ArrayList<>();
        String sql = "SELECT * FROM TBL_WORK WHERE WORK_TYPE = ?";

        try (
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, workType);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new WorkVo(
                    rs.getString("WORK_ID"),
                    rs.getString("WORK_TYPE"),
                    rs.getInt("WORK_TIME")
                ));
            }
        } catch (SQLException e) {
        }

        return list;
    }

    public List<WorkVo> select(int time, String option) {
        List<WorkVo> list = new ArrayList<>();
        StringBuffer sql = new StringBuffer("SELECT * FROM TBL_WORK WHERE WORK_TIME");
        if (option.equals("up")) {
            sql.append(" >= ?");
        } else if (option.equals("down")) {
            sql.append(" <= ?");
        } else {
            sql.append(" = ?");
        }

        try (
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql.toString());
        ) {
            ps.setInt(1, time);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new WorkVo(
                    rs.getString("WORK_ID"),
                    rs.getString("WORK_TYPE"),
                    rs.getInt("WORK_TIME")
                ));
            }
        } catch (SQLException e) {
        }

        return list;
    }

    public int insert(String id, String workType, int time) {
        int result = 0;
        String sql = "insert into tbl_work values (?,?,?)";

        try (
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, id);
            ps.setString(2, workType);
            ps.setInt(3, time);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("직원의 직급 정보 추가 실패 : " + e.getMessage());
        }

        return result;
    }

    // workType만 업데이트
    public boolean update(String id, String workType) {
        boolean result = false;
        String sql = "update tbl_work set work_type = ? where work_id = ?";

        try (
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, workType);
            ps.setString(2, id);
            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("근무 조건 업데이트 실패 : " + e.getMessage());
        }

        return result;
    }


    // time만 업데이트
    public boolean update(String id, int time) {
        boolean result = false;
        String sql = "update tbl_work set time = ? where work_id = ?";

        try (
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, time);
            ps.setString(2, id);
            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("근무 조건 업데이트 실패 : " + e.getMessage());
        }

        return result;
    }

    // workType과 time 둘다 업데이트
    public boolean update(String id, String workType, int time) {
        boolean result = false;
        String sql = "update tbl_work set time = ?, time = ? where work_id = ?";

        try (
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, workType);
            ps.setInt(2, time);
            ps.setString(3, id);
            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("근무 조건 업데이트 실패 : " + e.getMessage());
        }

        return result;
    }

    public boolean delete(String id) {
        boolean result = false;
        String sql = "delete from tbl_work where work_id = ?";

        try (
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, id);
            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("근무 테이블 데이터 삭제 실패 : " + e.getMessage());
        }

        return result;
    }
}
