package com.example.project;

import com.example.project.model.Products;
import com.example.project.service.ProductsService;
import com.vdurmont.emoji.EmojiParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProjectApplicationTests {

    @Autowired
    private ProductsService productsService;

    @Test
    void contextLoads() {
        List<Products> list = productsService.getAll();
    }

    @Test
    void show(){
        String str = "Here is a boy: :boy|type_6:!";
        System.out.println("原始字符为：\n" + str);

        System.out.println("to html：");
        String s = EmojiParser.parseToHtmlDecimal(str);
        System.out.println(s);

        System.out.println("还原：");
        System.out.println(EmojiParser.parseToUnicode(s));
    }

}
