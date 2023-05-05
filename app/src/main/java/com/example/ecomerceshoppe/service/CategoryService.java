package com.example.ecomerceshoppe.service;

import com.example.ecomerceshoppe.API.ProductAPI;
import com.example.ecomerceshoppe.R;
import com.example.ecomerceshoppe.model.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CategoryService {



    public static List<String> loadLogoName() {



        String[] nameLogo = new String[]{
                "Áo Quần", "Điện Thoại & Phụ Kiện", "Máy Tính", "Nhà Sách Online", "Máy tính & LapTop", "Thiết Bị Điện tử", "Sách"
        };



        return Arrays.asList(nameLogo);


    }

    private List<String> initDataForCategory(){
        List<String> ListCategory = new ArrayList<>();
        ListCategory.add("Áo Quần");
        ListCategory.add("Điện Thoại");
        ListCategory.add("Sách");
        ListCategory.add("Máy Tính");
        ListCategory.add("Điện Tử");
        ListCategory.add("Thuốc");
        return ListCategory;

    }

    public static int[] loadLogo() {
        int[] logo  = new int[]{
                R.drawable.quanao,
                R.drawable.dt,
                R.drawable.maytitnh,
                R.drawable.sach,
                R.drawable.pc,
                R.drawable.dientu,
                R.drawable.sach1

        };

        return logo;


    }




}
