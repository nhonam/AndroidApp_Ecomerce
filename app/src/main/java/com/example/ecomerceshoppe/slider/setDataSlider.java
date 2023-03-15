package com.example.ecomerceshoppe.slider;

import java.util.ArrayList;
import java.util.List;

public class setDataSlider {

    public static List<Photo> getListPhoto() {
        List<Photo> list = new ArrayList<>();
        list.add(new Photo("https://vanhoadoisong.vn/wp-content/uploads/2022/05/100-hinh-nen-hinh-anh-rose-dep-full-hd-cho-dien-thoai-may-tinh-thumbnail.jpg"));
        list.add(new Photo("https://media.vov.vn/sites/default/files/styles/large/public/2022-05/still_4.jpg"));
        list.add(new Photo("https://images.fpt.shop/unsafe/filters:quality(5)/fptshop.com.vn/uploads/images/tin-tuc/149444/Originals/hinh-nen-iPhone-14-1.jpeg"));
         return list;

    }
}
