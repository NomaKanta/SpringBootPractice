//このクラスのパッケージを表しています。
package com.example.demo.controller;

//ここから必要なパッケージをインストールしています。
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
//ここまで必要なパッケージをインストールしています。

@Controller //システムに「このクラスはコントローラだよ！認識しておいてね！」と宣言します。
public class ContactController {
	@PostMapping("/contact") //HTTPのPOSTリクエストを処理するメソッドを表します。
	public ModelAndView contact(@RequestParam("lastName") String lastName, //コントローラーメソッドがビューにデータを渡すために使用されるクラスです。
			//@RequestParamはHTTPリクエストのパラメータをコントローラメソッドのパラメータとしてマッピングするために使用されます。
			//今回の場合、@RequestParamで渡されるパラメーターは、contact.htmlファイルで実装したフォームのinputタグやselectタグの値を受け取っています。
			@RequestParam("firstName") String firstName,
			@RequestParam("email") String email,
			@RequestParam("phone") String phone,
			@RequestParam("zipCode") String zipCode,
			@RequestParam("address") String address,
			@RequestParam("buildingName") String buildingName,
			@RequestParam("contactType") String contactType,
			@RequestParam("body") String body,
			ModelAndView mv) { // 引数にModelAndViewを設定

		// 使用するViewのtemplate名を指定
		mv.setViewName("confirmation");

		// templateで表示する要素のキーと値を指定
		mv.addObject("lastName", lastName);
		mv.addObject("firstName", firstName);
		mv.addObject("email", email);
		mv.addObject("phone", phone);
		mv.addObject("zipCode", zipCode);
		mv.addObject("address", address);
		mv.addObject("buildingName", buildingName);
		mv.addObject("contactType", contactType);
		mv.addObject("body", body);

		// ModelAndViewクラスをreturn
		return mv;
	}
}
