package com.example.demo.form;
//Serializableを実装するだけで、そのクラスのオブジェクトが直列化可能であることを示すことができます。
//難しいので今はFormクラスにはSerializableをimplementsすると覚えておけばOK
import java.io.Serializable;

//バリデーションの設定…データの妥当性や適合性を確認するプロセスです。
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class ContactForm implements Serializable {
    @NotBlank //Nullでないかつ空文字でないこと
    private String lastName;

    @NotBlank
    private String firstName;

    @NotBlank
      @Email //正しいメールアドレス形式であること
    private String email;

    @NotBlank
    @Size(min = 10, max = 11) //文字数または配列の要素数が指定の範囲内(上限と下限)であること
    private String phone;

    @NotBlank
    @Pattern(regexp = "[0-9]{3}[-]{0,1}[0-9]{4}") //正規表現にマッチすること
    private String zipCode;

    @NotBlank
    private String address;

    @NotBlank
    private String buildingName;

    @NotEmpty //文字数または配列の要素数が0でないこと
    private String contactType;

    @NotBlank
    private String body;
}