package com.cmt.navigation.controller;
import com.example.navigationservice.navigation.api.*;
import com.example.navigationservice.navigation.core.service.NavigationTagUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/navi")
public class NavigationController {
    NavigationService navigationService;
//    Long menuId
//    String menuName : 메뉴이름
//    String menuUrl : 링크
//    String menuTarget : 새창/현재창? - enum으로 하면 좋지않을까
//    String menuClass : html 클래스
//    Long menuSort : 정렬번호
//    Long menuParentId
//    String menuRole : 권한
//    boolean menuUse : 사용여부
//    List<Menu> subMenu

//    Optional<RootMenu> findRootMenu() throws MenuNotFoundException; - Optional, throws 둘다?

//    createMenu(SaveMenu saveMenu)
//    -> DuplicateMenuSortException
//      -> 추가할 경우 생성한 메뉴 뒤에 메뉴들의 sort +1씩.

//    Menu updateMenu(SaveMenu saveMenu)
//    -> DuplicateMenuSortException
//    - 예외처리를 할거면 굳이 MenuParentId와 MenuSort는 입력받을 필요가 없다고 생각
//    - 메뉴의 이름만 바꾸고 싶을때 바꿀수 있는지? (예외처리때문에 안되는것같다)
//      -> (MenuParentId, MenuSort)기준
//          ex1) (1,2)->(2,3)
//              -> (1,x(x는 x>2))은 (1,x-1)으로 바꿔주고
//              -> (2,y(y는 y>=3))은 (2,y+1)으로 바꿔주는식으로 ..
//          ex2) (1,5)->(1,2)
//              -> (1,(2->3) (3->4) (4->5))
//          ex3) (1,2)->(1,5)
//              -> (1,(3->2) (4->3) (5->4))

//    boolean deleteMenu(Long menuId);
//    -> 삭제할 경우 삭제한 메뉴 뒤에 메뉴들의 sort를 -1씩 해야 할것같다.

    NavigationTagUtil navigationTagUtil;

    public NavigationController(NavigationService navigationService, NavigationTagUtil navigationTagUtil) {
        this.navigationService = navigationService;
        this.navigationTagUtil = navigationTagUtil;
    }

    @GetMapping("/rootMenu")
    public String test(Model model){

        model.addAttribute("rootMenus"
                ,navigationService.findRootMenu().get().getRootMenu()
        );
        String str = navigationTagUtil.getMenuTag();
        Document doc = Jsoup.parseBodyFragment(str);
        Element body = doc.body();
        model.addAttribute("tag",str);
        return "test1";
    }

    @GetMapping("/create-update-delete")
    public String createUpdateDelete(SaveMenu saveMenu){
        return "create_update_delete";
    }

    @PostMapping("/create")
    public String create(SaveMenu saveMenu){
        navigationService.createMenu(saveMenu);
        return "redirect:/navi/rootMenu";
    }

    @PutMapping("/update")
    public String update(SaveMenu saveMenu){
        navigationService.updateMenu(saveMenu);
        return "redirect:/navi/rootMenu";
    }

    @DeleteMapping("/delete")
    public String delete(Long menuId){
        navigationService.deleteMenu(menuId);
        return "redirect:/navi/rootMenu";
    }
}
