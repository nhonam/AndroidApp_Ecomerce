//package com.example.ecomerceshoppe.adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.List;
//
//public class CategoryRCVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//
//    Context context;
//    private String[] logoNameList;
//    private int[] imgPhotoList;
//
//    public  CategoryRCVAdapter(Context context, String[] logoNameList, int[] imgPhotoList) {
//        this.context = context;
//        this.logoNameList = logoNameList;
//        this.imgPhotoList = imgPhotoList;
//    }
//
//    public void setListData(String[] logoNameList, int[] imgPhotoList) {
//        this.logoNameList = logoNameList;
//        this.imgPhotoList = imgPhotoList;
//        notifyDataSetChanged();
//    }
//
//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Context context = parent.getContext();
//        LayoutInflater inflater = LayoutInflater.from(context);
//
//        // Nạp layout cho View biểu diễn phần tử sinh viên
//        View itemView =
//                inflater.inflate(R.layout.item_recycle_view, parent, false);
//
//        ViewHolder viewHolder = new ViewHolder(itemView);
//        return viewHolder;
//        return null;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//}
