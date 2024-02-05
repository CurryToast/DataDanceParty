package project.vo;

import java.sql.Date;

public class BuyVo {
    private int buy_idx;
    private String customid;
    private String pcode;
    private int quantity;
    private Date buy_date;

    public BuyVo(String customid, String pcode, int quantity) {
        this.customid = customid;
        this.pcode = pcode;
        this.quantity = quantity;
    }

    public BuyVo(String customid, String pcode, int quantity, Date buy_date) {
        this.customid = customid;
        this.pcode = pcode;
        this.quantity = quantity;
        this.buy_date = buy_date;
    }

    public BuyVo(int buy_idx, String customid, String pcode, int quantity, Date buy_date) {
        this.buy_idx = buy_idx;
        this.customid = customid;
        this.pcode = pcode;
        this.quantity = quantity;
        this.buy_date = buy_date;
    }

    public int getBuy_idx() {
        return buy_idx;
    }

    public String getCustomid() {
        return customid;
    }

    public String getPcode() {
        return pcode;
    }

    public int getQuantity() {
        return quantity;
    }

    public Date getBuy_date() {
        return buy_date;
    }

    @Override
    public String toString() {
        return "BuyVo [buy_idx=" + buy_idx + ", customid=" + customid + ", pcode=" + pcode + ", quantity=" + quantity
                + ", buy_date=" + buy_date + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + buy_idx;
        result = prime * result + ((customid == null) ? 0 : customid.hashCode());
        result = prime * result + ((pcode == null) ? 0 : pcode.hashCode());
        result = prime * result + quantity;
        result = prime * result + ((buy_date == null) ? 0 : buy_date.hashCode());
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
        BuyVo other = (BuyVo) obj;
        if (buy_idx != other.buy_idx)
            return false;
        if (customid == null) {
            if (other.customid != null)
                return false;
        } else if (!customid.equals(other.customid))
            return false;
        if (pcode == null) {
            if (other.pcode != null)
                return false;
        } else if (!pcode.equals(other.pcode))
            return false;
        if (quantity != other.quantity)
            return false;
        if (buy_date == null) {
            if (other.buy_date != null)
                return false;
        } else if (!buy_date.equals(other.buy_date))
            return false;
        return true;
    }
    
}
