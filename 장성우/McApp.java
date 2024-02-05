package jdbc.day5;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.sound.midi.MidiDeviceTransmitter;

import project.dao.BuyMenuDao;
import project.dao.MembersDao;
import project.dao.MenuDao;
import project.vo.BuyMenuVo;
import project.vo.BuyVo;
import project.vo.MemberBuyVo;
import project.vo.MembersVo;
import project.vo.MenuVo;

public class McApp {
    private MembersDao membersDao = new MembersDao();
    private BuyMenuDao buymenudao = new BuyMenuDao();
    private MenuDao menudao = new MenuDao();
    private List<BuyMenuVo> cart = new ArrayList<>();
    private Map<String, Integer> priceMap = null;

    public McApp() {
        this.priceMap = menudao.getMpriceTable();
        //System.out.println(priceMap);
    }

    // 선택 메뉴보기
    private void showMenu() {
        System.out.println("-".repeat(50));
        System.out.println("[A]장바구니 담기  [B]장바구니 빼기  [C]장바구니 보기  [D]결제하기 ");
        System.out.println("[E]구매내역 보기  [F]회원정보 수정  [G]구매목록 수정  [H]카테고리 검색");
        System.out.println("[I]상품이름 검색  [J]전체회원 정보  [K]회원정보 검색  [X]GoodBye ");
        System.out.println("-".repeat(50));
    }

    // 장바구니에 담기
    private void addCartMenu(String customer_id) {
       
        //모든 메뉴정보 보여주기 
        menudao.allMenuList();

        System.out.print("구매할 상품코드를 입력하세요__");
        String menu_id = System.console().readLine();
        System.out.print("구매할 갯수를 입력하세요__ ");
        int menu_quantity = Integer.parseInt(System.console().readLine());

        cart.add(new BuyMenuVo(0, customer_id, menu_id, menu_quantity, null));
    }
    // 장바구니 빼기
    private void removeCartMenu() {
        showCartList();
        System.out.println("장바구니 비우기");

        System.out.print("삭제할 번호를 입력하세요__");
        int index = Integer.parseInt(System.console().readLine());
        if(index <0 || index >=cart.size()){
            System.out.println("장바구니에 없는 잘못된 번호 입니다.");
            return;
        }
        cart.remove(index);
        System.out.println("제품을 삭제했습니다.");

    }
    // 구매내역보기
    private void showMyPage(String customerid) {
        List<MemberBuyVo> result = buymenudao.selectMemberBuy(customerid);
        for (MemberBuyVo vo : result)
            System.out.println(vo);
    }

    // 메뉴 검색
    private void searchMenuByMname() {
        System.out.print("상품명을 입력하세요___");
        String Mname = System.console().readLine();
        List<MenuVo> menulist = menudao.selectByMname(Mname);
        for (MenuVo vo : menulist)
            System.out.println(vo);
    }

    private void searchMenuByCategory() {

        System.out.print(" 카테고리를 입력하세요___");
        String category = System.console().readLine();
        List<MenuVo> menulist = menudao.selectByCategory(category);
        for (MenuVo vo : menulist)
            System.out.println(vo);
    }


    // 장바구니보기2
    private void showCartList() {
        long totalMoney = 0;
        System.out.println(".................. 장바구니 :: 목록 보기...................");
        for (int i=0; i<cart.size(); i++) {
            int price = cart.get(i).getMenuQuantity() * priceMap.get(cart.get(i).getMenuId());
            System.out.println("번호 :" + i + " 물품 :" + cart.get(i)); // 리스트에서 인덱스 i번쨰 가져오기

            totalMoney += price;
            
        }
        System.out.println("전체 금액 : "+totalMoney);
    }


    // 장바구니로 구매
    public void buyCartMenu() {
        if (cart.size() == 0) {
            System.out.println("장바구니가 비었습니다.");
            return;
        }
        long totalMoney = 0;
        System.out.println(" 장바구니 물품 모두 구매");
        int result = buymenudao.allAtOnes(cart);
        if (result > 0) {
            for(int i=0; i<cart.size();i++){
                int price = cart.get(i).getMenuQuantity() * priceMap.get(cart.get(i).getMenuId());
                totalMoney+= price;
            }
            System.out.println("금액 : "+ totalMoney);
            System.out.println("장바구니 물품구매가 정상적으로 완료되었습니다.");
            cart.clear();
        } else {
            System.out.println("장바구니 물품 구매 실패 하였습니다.");
        }
    }

    public void changeMemberdata(String code){

        System.out.println("회원정보 수정");
        System.out.print(" 이메일을 입력하세요. __ ");
        String email = System.console().readLine();

        System.out.print(" 전화번호를 입력하세요. __ ");
        String ph = System.console().readLine();

        MembersVo vo = new MembersVo(code, null, email, ph, 0);
        MembersDao dao= new MembersDao();
        dao.modify(vo);

    }

    public void changeMenuQuntity(){
        System.out.println("구매 수량 변경하기 ");
        BuyMenuVo vo = new BuyMenuVo(0, null, null, 0, null);
                    System.out.println("변경할 상품의 buy_idx 입력___");
                    int buy_idx = Integer.parseInt(System.console().readLine());
                    System.out.println("변경할 상품의 코드 입력___");
                    String menuid = System.console().readLine();

                    // Now we need to get the new quantity from the user
                    System.out.println("변경할 수량 입력___ ");
                    int meun_quantity = Integer.parseInt(System.console().readLine());

                    // Call the modify method with the new quantity
                    vo = new BuyMenuVo(buy_idx,null, menuid, meun_quantity, null);
                  

                    if(buymenudao.modify(vo)==1)
                        System.out.println("정상적으로 수정되었습니다.");
                    else
                        System.out.println(" 없는 구매 번호 입니다. ");    

    }
    public void totalMembers(){
        System.out.println("=====전체회원 리스트=====");
            membersDao.allMember();
    }

    public void searchMembers(){
        System.out.println("회원 정보를 가져옵니다.");
        System.out.print("검색 할 회원 아이디___");
        String code = System.console().readLine();
             membersDao.getMembersVo(code);

    }



    // public void newMembers(){
    //     System.out.println("회원가입을 시작합니다.");
    //     MembersVo newmember = new MembersVo(null, null, null, null, 0);    
        
    // }





    public static void main(String[] args) {
        McApp app = new McApp();
        app.start();
    }

   




    public void start() {
        System.out.println("사용자 아이디를 입력하세요");
        System.out.print("아이디 ____");
        String customerid = System.console().readLine();
        System.out.println();

        // 회원유무파악
        if(membersDao.memberCheck(customerid)){
        System.out.println(customerid+"님 환영합니다.OOO");
        }
        else{
         System.out.println(customerid+"은 회원이 아닙니다.XXX");
         System.out.println("회원가입을 진행합니다. ");
         membersDao.join(null);
        }    


        boolean run = true;
        while (run) {
            showMenu();
            System.out.print("선택>>>");

            String select = System.console().readLine();
            switch (select) {
                case "A","a":
                    addCartMenu(customerid);                
                    break;
                case "B","b":
                    removeCartMenu();
                    break;
                case "C","c":
                      showCartList();
                    break;
                case "D","d":
                    buyCartMenu();
                    break;
                case "E","e":
                    showMyPage(customerid);
                    break;
                case "F","f":
                    changeMemberdata(customerid);
                    break;
                case "G","g":
                     changeMenuQuntity();
                    break;
                case "h","H":
                    searchMenuByCategory();
                    break;
                case "i","I":
                    searchMenuByMname();
                    break;

                case "j","J":
                   totalMembers();
                    break;
                case "k","K":
                    searchMembers();
                    break;
                case "l","L":
                    break;
                case "x","X":
                    run = false;
                    break;
                default:
                    break;
            }
        }
    }

}
