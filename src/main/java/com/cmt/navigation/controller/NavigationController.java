package com.cmt.navigation.controller;
import com.example.navigationservice.navigation.api.*;
import com.example.navigationservice.navigation.core.service.NavigationTagUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/navi")
public class NavigationController {
    NavigationService navigationService;
    NavigationTagUtil navigationTagUtil;

    public NavigationController(NavigationService navigationService, NavigationTagUtil navigationTagUtil) {
        this.navigationService = navigationService;
        this.navigationTagUtil = navigationTagUtil;
    }

    @GetMapping("/rootMenu")
    public String test(Model model){

        model.addAttribute("rootMenus"
                ,navigationService.findRootMenu().get().getRootMenu()
        );///Optional 해야하나
        String str = navigationTagUtil.getMenuTag();
        Document doc = Jsoup.parseBodyFragment(str);
        Element body = doc.body();
        model.addAttribute("tag",str);
        return "test1";
    }

    @GetMapping("/create")
    public String create(){
//        SaveMenu saveMenu = new SaveMenu();
////        #{menuName}, #{menuUrl}, #{menuTarget}, #{menuClass}, #{menuSort}, #{menuParentId, jdbcType=VARCHAR}, #{menuRole}, #{menuUse}
//        saveMenu.setMenuName("메뉴이름");
//        saveMenu.setMenuUrl("url");
//        saveMenu.setMenuTarget("target");
//        saveMenu.setMenuClass("클래스");
//        saveMenu.setMenuSort(5l);
//        saveMenu.setMenuParentId(null);
//        saveMenu.setMenuRole("role");

//        navigationService.createMenu(saveMenu);
        return "create_update_delete";
    }

    @PostMapping("/create")
    public String create(Menu menu){
        return "redirect:/navi/";
    }
}
