package GostCompany.vo;

import java.sql.Date;

public class SalaryVo {
    private String sl_id;
    private String sl_position;
    private int sl_commition;
    private Date sl_date;

    public SalaryVo(String sl_id, String sl_position, int sl_commition, Date sl_date) {
        this.sl_id = sl_id;
        this.sl_position = sl_position;
        this.sl_commition = sl_commition;
        this.sl_date = sl_date;
    }

    public String getSl_id() {
        return sl_id;
    }

    public String getSl_position() {
        return sl_position;
    }

    public int getSl_commition() {
        return sl_commition;
    }

    public Date getSl_date() {
        return sl_date;
    }

    @Override
    public String toString() {
        return "SalaryVo [sl_id=" + sl_id + ", sl_position=" + sl_position + ", sl_commition=" + sl_commition
                + ", sl_date=" + sl_date + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((sl_id == null) ? 0 : sl_id.hashCode());
        result = prime * result + ((sl_position == null) ? 0 : sl_position.hashCode());
        result = prime * result + sl_commition;
        result = prime * result + ((sl_date == null) ? 0 : sl_date.hashCode());
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
        SalaryVo other = (SalaryVo) obj;
        if (sl_id == null) {
            if (other.sl_id != null)
                return false;
        } else if (!sl_id.equals(other.sl_id))
            return false;
        if (sl_position == null) {
            if (other.sl_position != null)
                return false;
        } else if (!sl_position.equals(other.sl_position))
            return false;
        if (sl_commition != other.sl_commition)
            return false;
        if (sl_date == null) {
            if (other.sl_date != null)
                return false;
        } else if (!sl_date.equals(other.sl_date))
            return false;
        return true;
    }

    
}
