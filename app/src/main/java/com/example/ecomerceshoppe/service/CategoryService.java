package com.example.ecomerceshoppe.service;

import com.example.ecomerceshoppe.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CategoryService {



    public static List<String> loadLogoName() {



        String[] nameLogo = new String[]{
                "Áo Quần", "Điện Thoại", "Sách", "Máy Tính", "Điện Tử", "Thuốc", "Nho Nam", "Nho Nam", "Nho Nam", "Nho Nam", "Nho Nam", "Nho Nam"
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
                R.drawable.a,
                R.drawable.b,
                R.drawable.c,
                R.drawable.d,
                R.drawable.e,
                R.drawable.f,
                R.drawable.g,
                R.drawable.h,
                R.drawable.i,
                R.drawable.bn1,
                R.drawable.bn2,
                R.drawable.bn3

        };

        return logo;


    }




}
