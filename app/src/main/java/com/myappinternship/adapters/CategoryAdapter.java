package com.myappinternship.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myappinternship.R;
import com.myappinternship.activity.CatDetailsActivity;
import com.myappinternship.models.BookModel;
import com.myappinternship.models.CategoryModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryAdapter extends BaseAdapter{
    Context context;
    ArrayList<CategoryModel> categoryModelArrayList;
    ArrayList<CategoryModel> categoryModelArrayListFiltered;

    public CategoryAdapter(Context context, ArrayList<CategoryModel> categoryModelArrayList) {

        this.context = context;
        this.categoryModelArrayList = categoryModelArrayList;
        this.categoryModelArrayListFiltered = new ArrayList<CategoryModel>();
        this.categoryModelArrayListFiltered.addAll(categoryModelArrayList);

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
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = categoryModelArrayList.get(position).getCat_url();
                String catName = categoryModelArrayList.get(position).getCat_name();
                String catDescription = categoryModelArrayList.get(position).getCat_description();
                String catID = categoryModelArrayList.get(position).getCat_id();

                Intent i = new Intent(context, CatDetailsActivity.class);
                i.putExtra("KEY_ID",catID);
                i.putExtra("KEY_NAME",catName);
                i.putExtra("KEY_DESC",catDescription);
                i.putExtra("KEY_URL",url);
                context.startActivity(i);
            }
        });
        return convertView;
    }
    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        categoryModelArrayList.clear();
        if (charText.length() == 0) {
            categoryModelArrayList.addAll(categoryModelArrayListFiltered);
        } else {
            for (CategoryModel wp : categoryModelArrayListFiltered) {
                if (wp.getCat_name().toLowerCase(Locale.getDefault()).contains(charText)) {
                    categoryModelArrayList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}
