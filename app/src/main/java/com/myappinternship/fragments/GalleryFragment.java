package com.myappinternship.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.myappinternship.adapters.BookAdapter;
import com.myappinternship.R;
import com.myappinternship.adapters.GridViewAdapter;
import com.myappinternship.models.BookModel;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    GridView gridView;
    String strBook[] = {"Book 1", "Book 2", "Book 3", "Book 4", "Book 5", "Book 6", "Book 7", "Book 8"};
    int imgBook[] = {R.drawable.ic_home, R.drawable.ic_home, R.drawable.ic_home,
            R.drawable.ic_home, R.drawable.ic_home, R.drawable.ic_home, R.drawable.ic_home, R.drawable.ic_home};
    ArrayList<BookModel> bookModelArrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_gallery, container, false);
        gridView = rootview.findViewById(R.id.grid_view);

        bookModelArrayList = new ArrayList<BookModel>();
        for (int i = 0; i < strBook.length; i++) {
            BookModel bookModel = new BookModel(strBook[i], imgBook[i]);
            bookModelArrayList.add(bookModel);
        }


        GridViewAdapter gridViewAdapter = new GridViewAdapter(getActivity(),bookModelArrayList);
        gridView.setAdapter(gridViewAdapter);


        return rootview;
    }
}