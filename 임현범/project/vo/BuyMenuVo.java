package project.vo;

import java.sql.Date;

public class BuyMenuVo {
    public static String BUY_MENU_TITLE = String.format(
        "%4s %5s %5s %5s %-20s",
        "구매번호", "고객코드", "메뉴코드", "수량", "구매날짜"
    );
    public static String CART_MENU_TITLE = String.format(
        "%4s %5s %5s %4s",
        "구매번호", "고객코드", "메뉴코드", "수량"
    );

    private int buyIdx;
    private String customerId;
    private String menuId;
    private int menuQuantity;
    private Date buyDate;

    public BuyMenuVo(String customerId, String menuId, int menuQuantity) {
        this.customerId = customerId;
        this.menuId = menuId;
        this.menuQuantity = menuQuantity;
    }

    public BuyMenuVo(int buyIdx, String customerId, String menuId, int menuQuantity, Date buyDate) {
        this.buyIdx = buyIdx;
        this.customerId = customerId;
        this.menuId = menuId;
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

    public int getMenuQuantity() {
        return menuQuantity;
    }

    public Date getBuyDate() {
        return buyDate;
    }


    public String toCartString() {
        return String.format("%7d %9s %9s %5d",
            buyIdx, customerId, menuId, menuQuantity
        );
    }

    @Override
    public String toString() {
        return String.format("%7d %9s %9s %8d %-20s",
            buyIdx, customerId, menuId, menuQuantity, buyDate
        );
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + buyIdx;
        result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
        result = prime * result + ((menuId == null) ? 0 : menuId.hashCode());
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
        BuyMenuVo other = (BuyMenuVo) obj;
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
