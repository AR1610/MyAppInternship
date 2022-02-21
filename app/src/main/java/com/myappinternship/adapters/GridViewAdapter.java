package com.myappinternship.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myappinternship.R;
import com.myappinternship.models.BookModel;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class GridViewAdapter extends BaseAdapter {
    Context context;
    ArrayList<BookModel> bookModelArrayList;

    public GridViewAdapter(Context context, ArrayList<BookModel> bookModelArrayList) {

        this.context = context;
        this.bookModelArrayList = bookModelArrayList;

    }

    @Override
    public int getCount() {
        return bookModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return bookModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.raw_grid, null);

        CircleImageView imgBook = convertView.findViewById(R.id.img_book);
        TextView tvBook = convertView.findViewById(R.id.tv_book);
        imgBook.setImageResource(bookModelArrayList.get(position).getImgBook());
        tvBook.setText(bookModelArrayList.get(position).getStrBook());


        return convertView;
    }
}
