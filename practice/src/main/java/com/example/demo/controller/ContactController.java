
//このクラスのパッケージを表しています。
package com.example.demo.controller;

//ここから必要なパッケージをインストールしています。
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.data.ContactData;
//ここまで必要なパッケージをインストールしています。

@Controller //システムに「このクラスはコントローラだよ！認識しておいてね！」と宣言します。
public class ContactController {
    @PostMapping("/contact") //HTTPのPOSTリクエストを処理するメソッドを表します。
    //@ModelAttributeアノテーションを使って、Dataクラスに定義したオブジェクトをContactControllerクラスのconfirmメソッドの引数に設定します。
    //具体的に引数は@ModelAttribute ContactData contactDataとしてあげることで、
    //confirmメソッドの中でcontactData.getLastName()のようにContactDataクラス(Lombokの@Dataアノテーション)の機能を使うことができます。
    public ModelAndView contact(@ModelAttribute ContactData contactData, ModelAndView mv) {

        mv.setViewName("confirmation");

        mv.addObject("lastName", contactData.getLastName());
        mv.addObject("firstName", contactData.getFirstName());
        mv.addObject("email", contactData.getEmail());
        mv.addObject("phone", contactData.getPhone());
        mv.addObject("zipCode", contactData.getZipCode());
        mv.addObject("address", contactData.getAddress());
        mv.addObject("buildingName", contactData.getBuildingName());
        mv.addObject("contactType", contactData.getContactType());
        mv.addObject("body", contactData.getBody());

        return mv;
    }
}
