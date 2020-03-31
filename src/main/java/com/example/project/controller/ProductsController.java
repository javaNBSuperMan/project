package com.example.project.controller;

import com.example.project.model.Products;
import com.example.project.model.ProductsImage;
import com.example.project.service.ProductsImageService;
import com.example.project.service.ProductsService;
import com.example.project.util.Base64Util;
import com.example.project.util.idutil.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductsController {
    @Autowired
    private ProductsService productsService;
    @Autowired
    private ProductsImageService productsImageService;

    @RequestMapping("/list")
    public String list() { return "list"; }

    @RequestMapping("/login")
    public String login() { return "login"; }

    @RequestMapping("/")
    public String index(Model model) {
        List<ProductsImage> imgList = null;
        Products products = productsService.getProductsMax();
        if(products != null) {
            imgList = productsImageService.getAllByProductsid(products.getId());
            products.setReadNum(products.getReadNum() + 1);
        }
        productsService.update(products);
        model.addAttribute("products", products);
        model.addAttribute("imgList", imgList);
        return "index";
    }

    @RequestMapping("/userLogin")
    @ResponseBody
    public Integer userLogin(HttpSession session, String user, String password) {
        if(user != null && password != null) {
            user = new String(Base64Util.decode(user));
            password = new String(Base64Util.decode(password));
            if("wangxinyu".equals(user) && "zjl425".equals(password)) {
                session.setAttribute("user", "wangxinyu");
                return 1;
            }
        }
        return 0;
    }

    @RequestMapping("/edit")
    public String edit() {
        return "edit";
    }

    @RequestMapping("/getAll")
    public String getAll(Model model) throws Exception{
        List<Products> list = productsService.getAll();
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            Products products =  list.get(i);
            List<ProductsImage> imgList = productsImageService.getAllByProductsid(products.getId());
            for (int j = 0; j < imgList.size(); j++) {
                ProductsImage image =  imgList.get(j);
                if(image.getImgNum() == 1) {
                    map.put("imgSrc", image.getImgSrc());
                    break;
                }
            }
            if(imgList.size() <= 0) {
                map.put("imgSrc", "");
            }
            map.put("products", products);
            data.add(map);
        }
        model.addAttribute("data", data);
        return "list";
    }

    @RequestMapping("/view")
    public String view(Model model, String id) throws Exception{
        Products products = null;
        List<ProductsImage> imgList = null;
        if(id != null && !"".equals(id)) {
            id = new String(Base64Util.decode(id));
            products = productsService.getProductsById(id);
            products.setReadNum(products.getReadNum() + 1);
            productsService.update(products);
            imgList = productsImageService.getAllByProductsid(products.getId());
        }
        model.addAttribute("products", products);
        model.addAttribute("imgList", imgList);
        return "index";
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public Integer saveOrUpdate(Products products, String[] imgData, String[] imgName, String[] imgSize){
        Integer num = 0;
        try {
            products.setId(IdGenerator.generate());
            products.setCreateDate(new Timestamp(System.currentTimeMillis()));
            products.setCreateAuthor("admin");
            products.setReadNum(100);
            if(imgName != null) {
                for (int i = 0; i < imgName.length; i++) {
                    String imgSrc = imgData[i];
                    String name = imgName[i];
                    String size = imgSize[i];
                    ProductsImage image = new ProductsImage();
                    image.setId(IdGenerator.generate());
                    image.setProductsId(products.getId());
                    image.setCreateDate(new Timestamp(System.currentTimeMillis()));
                    image.setCreateAuthor("admin");
                    image.setImgNum(i+1);
                    image.setImgName(name);
                    image.setImgSize(size);
                    image.setImgSrc(imgSrc);
                    if(imgSrc != null && !"".equals(imgSrc)) {
                        productsImageService.save(image);
                    }
                }
            }
            num = productsService.save(products);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Integer delete(String id) {
        Integer num = 0;
        try {
            if(id != null && !"".equals(id)) {
                id = new String(Base64Util.decode(id));
            }
            num = productsImageService.deleteByProductsid(id);
            num += productsService.delete(id);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return num;
    }
}
