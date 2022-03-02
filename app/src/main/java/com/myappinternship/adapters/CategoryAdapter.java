package com.myappinternship.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myappinternship.R;
import com.myappinternship.models.BookModel;
import com.myappinternship.models.CategoryModel;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryAdapter extends BaseAdapter {
    Context context;
    ArrayList<CategoryModel> categoryModelArrayList;

    public CategoryAdapter(Context context, ArrayList<CategoryModel> categoryModelArrayList) {

        this.context = context;
        this.categoryModelArrayList = categoryModelArrayList;

    }

    @Override
    public int getCount() {
        return categoryModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.raw_list, null);

        CircleImageView imgBook = convertView.findViewById(R.id.img_book);
        String url = categoryModelArrayList.get(position).getCat_url();
         Glide.with(context).load(url).into(imgBook);
        TextView tvBook = convertView.findViewById(R.id.tv_book);
        //imgBook.setImageResource(categoryModelArrayList.get(position).getImgBook());
        tvBook.setText(categoryModelArrayList.get(position).getCat_name());


        return convertView;
    }
}
