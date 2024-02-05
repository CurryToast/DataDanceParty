package GostCompany.vo;

public class WorkVo {
    private String work_id;
    private String work_type;
    private int work_time;

    public WorkVo(String work_id, String work_type, int work_time) {
        this.work_id = work_id;
        this.work_type = work_type;
        this.work_time = work_time;
    }

    public String getWork_id() {
        return work_id;
    }

    public String getWork_type() {
        return work_type;
    }

    public int getWork_time() {
        return work_time;
    }

    @Override
    public String toString() {
        return "WorkVo [work_id=" + work_id + ", work_type=" + work_type + ", work_time=" + work_time + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((work_id == null) ? 0 : work_id.hashCode());
        result = prime * result + ((work_type == null) ? 0 : work_type.hashCode());
        result = prime * result + work_time;
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
        WorkVo other = (WorkVo) obj;
        if (work_id == null) {
            if (other.work_id != null)
                return false;
        } else if (!work_id.equals(other.work_id))
            return false;
        if (work_type == null) {
            if (other.work_type != null)
                return false;
        } else if (!work_type.equals(other.work_type))
            return false;
        if (work_time != other.work_time)
            return false;
        return true;
    }

    
}
