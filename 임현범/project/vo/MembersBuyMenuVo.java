package project.vo;

import java.sql.Date;

public class MembersBuyMenuVo {
    private int buyIdx;
    private String customerId;
    private String menuId;
    private int menuPrice;
    private int menuQuantity;
    private Date buyDate;

    public MembersBuyMenuVo(
        int buyIdx,
        String customerId,
        String menuId,
        int menuPrice,
        int menuQuantity,
        Date buyDate
    ) {
        this.buyIdx = buyIdx;
        this.customerId = customerId;
        this.menuId = menuId;
        this.menuPrice = menuPrice;
        this.menuQuantity = menuQuantity;
        this.buyDate = buyDate;
    }

    public int getBuyIdx() {
        return buyIdx;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getMenuId() {
        return menuId;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public int getMenuQuantity() {
        return menuQuantity;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    @Override
    public String toString() {
        return String.format("%d %s %s %d %d %s",
            buyIdx, customerId, menuId, menuPrice, menuQuantity, buyDate
        );
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + buyIdx;
        result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
        result = prime * result + ((menuId == null) ? 0 : menuId.hashCode());
        result = prime * result + menuPrice;
        result = prime * result + menuQuantity;
        result = prime * result + ((buyDate == null) ? 0 : buyDate.hashCode());
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
        MembersBuyMenuVo other = (MembersBuyMenuVo) obj;
        if (buyIdx != other.buyIdx)
            return false;
        if (customerId == null) {
            if (other.customerId != null)
                return false;
        } else if (!customerId.equals(other.customerId))
            return false;
        if (menuId == null) {
            if (other.menuId != null)
                return false;
        } else if (!menuId.equals(other.menuId))
            return false;
        if (menuPrice != other.menuPrice)
            return false;
        if (menuQuantity != other.menuQuantity)
            return false;
        if (buyDate == null) {
            if (other.buyDate != null)
                return false;
        } else if (!buyDate.equals(other.buyDate))
            return false;
        return true;
    }

    
}
