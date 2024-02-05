package GostCompany.vo;

import java.sql.Date;

public class EmployeeVo {
    private String em_id;
    private String em_name;
    private String em_position;
    private Date em_join_date;

    public EmployeeVo(String em_id, String em_name, String em_position, Date em_join_date) {
        this.em_id = em_id;
        this.em_name = em_name;
        this.em_position = em_position;
        this.em_join_date = em_join_date;
    }

    public String getEm_id() {
        return em_id;
    }

    public String getEm_name() {
        return em_name;
    }

    public String getEm_position() {
        return em_position;
    }

    public Date getEm_join_date() {
        return em_join_date;
    }

    @Override
    public String toString() {
        return "EmployeeVo [em_id=" + em_id + ", em_name=" + em_name + ", em_position=" + em_position
                + ", em_join_date=" + em_join_date + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((em_id == null) ? 0 : em_id.hashCode());
        result = prime * result + ((em_name == null) ? 0 : em_name.hashCode());
        result = prime * result + ((em_position == null) ? 0 : em_position.hashCode());
        result = prime * result + ((em_join_date == null) ? 0 : em_join_date.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EmployeeVo other = (EmployeeVo) obj;
        if (em_id == null) {
            if (other.em_id != null)
                return false;
        } else if (!em_id.equals(other.em_id))
            return false;
        if (em_name == null) {
            if (other.em_name != null)
                return false;
        } else if (!em_name.equals(other.em_name))
            return false;
        if (em_position == null) {
            if (other.em_position != null)
                return false;
        } else if (!em_position.equals(other.em_position))
            return false;
        if (em_join_date == null) {
            if (other.em_join_date != null)
                return false;
        } else if (!em_join_date.equals(other.em_join_date))
            return false;
        return true;
    }

    
}
