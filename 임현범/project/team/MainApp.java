package project.team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import project.dao.TblBuyMenuDao;
import project.dao.TblMembersDao;
import project.dao.TblMenuDao;
import project.vo.BuyMenuVo;
import project.vo.MembersVo;
import project.vo.MenuVo;

public class MainApp {
    private TblMembersDao membersDao = new TblMembersDao();
    private TblMenuDao menuDao = new TblMenuDao();
    private TblBuyMenuDao buyMenuDao = new TblBuyMenuDao();
    private List<BuyMenuVo> cart = new ArrayList<BuyMenuVo>();
    private Map<String, Integer> priceMap = new HashMap<>();
    private String customerCode = null;

    public MainApp() {
        this.priceMap = menuDao.getPriceTable();
    }

    public static void main(String[] args) {
        MainApp app = new MainApp();
        app.start();
    }

    private void showMenu() {
        System.out.println(".".repeat(70));
        System.out.println("[C] 카테고리 별 검색  [P] 상품명 검색  [V] 가격대 별 검색");
        System.out.println("[B] 바로 구매  [R] 환불  [X] 프로그램 종료");
        System.out.println("::고객정보:: [I] 개인정보 조회  [N] 개인정보 변경  [M] 나의 구매내역");
        System.out.println("::장바구니:: [A] 담기  [L] 목록  [J] 수량 변경  [D] 삭제  [Y] 모두 구매 ");
        System.out.println(".".repeat(70));
    }

    public void start() {
        boot();

        boolean boo = true;
        while (boo) {
            showMenu();
            System.out.print("메뉴 입력 >>> ");
            String menu = System.console().readLine().toUpperCase();

            switch (menu) {
                case "C":
                    searchMenuByCategory();
                    break;
                case "P":
                    searchMenuByMname();
                    break;
                case "V":
                    searchMenuByPrice();
                    break;
                case "T":
                    break;
                case "B":
                    buyMenu();
                    break;
                case "R":
                    recallBuyMenu();
                    break;
                case "I":
                    showMyInfo();
                    break;
                case "N":
                    changeMyInfo();
                    break;
                case "M":
                    searchMyBuyList();
                    break;
                case "A":
                    addCart();
                    break;
                case "L":
                    showCartList();
                    break;
                case "D":
                    removeCartList();
                    break;
                case "Y":
                    buyAllCartList();
                    break;
                case "X":
                    boo = false;
                    break;
                default:
                    break;
            }
        }
    }

    public void boot() {
        System.out.println("::: 사용자 간편 로그인 (1) / 회원가입 (2) :::");
        System.out.print("\t메뉴 선택 >> ");
        int signMenu = Integer.parseInt(System.console().readLine());

        boolean run = true;
        while (run) {
            switch (signMenu) {
                case 1:
                    if (login()) {
                        run = false;
                        break;
                    } else {
                        System.out.println("가입된 고객이 아닙니다.");
                    }
                case 2:
                    if (join() == 1) {
                        run = false;
                        break;
                    }
                default:
                    break;
            }
        }
    }

    public int join() {
        System.out.println("============== 회원 가입 ==============");
        System.out.print("\t회원코드 입력");
        String newCode = System.console().readLine();
        System.out.print("\t회원이름 입력 >>> ");
        String newName = System.console().readLine();
        System.out.print("\t회원이메일 입력 >>> ");
        String newEmail = System.console().readLine();
        System.out.print("\t회원전화번호 입력 >>> ");
        String newPhoneNumber = System.console().readLine();
        System.out.print("\t회원나이 입력 >>> ");
        int newAge = Integer.parseInt(System.console().readLine());

        MembersVo vo = new MembersVo(newCode, newName, newEmail, newPhoneNumber, newAge);
        customerCode = newCode;

        return membersDao.signup(vo);
    }

    public boolean login() {
        System.out.println("============== 로그인 ==============");
        System.out.print("\t회원코드 입력 >> ");
        this.customerCode = System.console().readLine();

        return membersDao.signin(customerCode);
    }

    public void searchMenuByMname() {
        System.out.println("============== 상품명으로 검색 ==============");
        System.out.print("상품명 입력 >> ");
        String mname = System.console().readLine();
        List<MenuVo> list = menuDao.selectMenuByName(mname);
        if (list.size() > 0) {
            System.out.println(MenuVo.MENU_TITLE);
            for (MenuVo vo : list) {
                System.out.println(vo);
            }
        }
    }

    public void searchMenuByCategory() {
        System.out.println("============== 카테고리 별 검색 검색 ==============");
        List<String> categories = menuDao.selectCategories();
        System.out.print("카테고리 : ");
        for (int i = 0; i < categories.size(); i++) {
            System.out.print(String.format("%d. %s  ", i, categories.get(i)));
        }

        System.out.print("\n\t카테고리 번호 입력 >> ");
        int category = Integer.parseInt(System.console().readLine());
        List<MenuVo> list = menuDao.selectMenuByCategory(categories.get(category));
        if (list.size() > 0) {
            System.out.println(MenuVo.MENU_TITLE);
            for (MenuVo vo : list) {
                System.out.println(vo);
            }
        }
    }

    public void searchMenuByPrice() {
        System.out.println("============== 가격대 별 검색 검색 ==============");
        System.out.print("검색할 가격대 입력 (시작값,끝값) >> ");
        String[] priceString = System.console().readLine().split(",");
        List<MenuVo> list = menuDao.selectMenuByPrice(
            Integer.parseInt(priceString[0]),
            Integer.parseInt(priceString[1])
        );

        if (list.size() > 0) {
            System.out.println(MenuVo.MENU_TITLE);
            for (MenuVo vo : list) {
                System.out.println(vo);
            }
        }
    }

    public void searchMyBuyList() {
        System.out.println("============================ 구매내역 조회 ============================");
        List<BuyMenuVo> list = buyMenuDao.selectMyBuyList(this.customerCode);
        if (list.size() > 0) {
            System.out.println(BuyMenuVo.BUY_MENU_TITLE);
            for (BuyMenuVo vo : list) {
                System.out.print(vo);
                System.out.println(" 결제 금액 : " + vo.getMenuQuantity() * priceMap.get(vo.getMenuId()));
            }
        }
    }

    public BuyMenuVo addMenu() {
        System.out.print("\t구매할 메뉴코드 입력 >> ");
        String mcode = System.console().readLine().toUpperCase();
        System.out.print("\t구매할 수량 입력 >> ");
        int mquantity = Integer.parseInt(System.console().readLine());

        return new BuyMenuVo(customerCode, mcode, mquantity);
    }

    public void buyMenu() {
        System.out.println("============== 바로 구매 ==============");
        buyMenuDao.insert(addMenu());
    }

    public void recallBuyMenu() {
        System.out.println("============== 환불 ==============");
        System.out.print("\t취소할 구매번호 입력 >> ");
        int buyIndex = Integer.parseInt(System.console().readLine());
        buyMenuDao.delete(buyIndex);
    }

    public void addCart() {
        System.out.println("============== 장바구니 담기 ==============");
        cart.add(addMenu());
    }

    public void showCartList() {
        System.out.println("============================ 장바구니 목록 ============================");
        if (cart.size() > 0) {
            int sum = 0;
            System.out.println(String.format("%2s %s %s", "번호", BuyMenuVo.CART_MENU_TITLE, "금액"));
            for (int i = 0; i < cart.size(); i++) {
                int money = cart.get(i).getMenuQuantity() * priceMap.get(cart.get(i).getMenuId());
                System.out.print(String.format("%3d  %s", i, cart.get(i).toCartString()));
                System.out.println("\t" + money);
                sum += money;
            }

            System.out.println("==========================================");
            System.out.println("총 결제 금액 : " + sum);
        } else {
            System.out.println("장바구니에 상품이 없습니다.");
        }
    }

    public void removeCartList() {
        System.out.println("============== 장바구니 삭제 ==============");
        System.out.print("\t삭제할 장바구니 번호 입력 >> ");
        int cartIdx = Integer.parseInt(System.console().readLine());
        if (cartIdx >= 0 && cartIdx < cart.size()) {
            cart.remove(cartIdx);
        } else {
            System.out.println("잘못된 장바구니 번호입니다.");
        }
    }

    public void buyAllCartList() {
        System.out.println("============== 장바구니 전부 구매 ==============");
        if (cart.size() > 0) {
            buyMenuDao.insertMany(cart);
        }
    }

    public void showMyInfo() {
        System.out.println("============== 개인정보 조회 ==============");
        System.out.println(MembersVo.MEMBERS_TITLE);
        System.out.println(membersDao.selectMyInfo(customerCode));
    }

    public void changeMyInfo() {
        System.out.println("============== 개인정보 변경 ==============");
        System.out.print("\t변경할 이름 입력 (enter : 변경안함) >> ");
        String newName = System.console().readLine();
        System.out.print("\t변경할 이메일 입력 (enter : 변경안함) >> ");
        String newEmail = System.console().readLine();
        System.out.print("\t변경할 전화번호 입력 (enter : 변경안함) >> ");
        String newPhoneNumber = System.console().readLine();
        if (
            newName.equals("") &&
            newEmail.equals("") &&
            newPhoneNumber.equals("")
        ) {
            System.out.println("변경사항 없음");
            return;
        }

        MembersVo vo = new MembersVo(customerCode, newName, newEmail, newPhoneNumber);
        if (membersDao.update(vo) > 0) {
            System.out.println("개인정보 변경 완료");
        }
    }
}
