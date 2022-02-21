package com.myappinternship.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.myappinternship.adapters.BookAdapter;
import com.myappinternship.R;
import com.myappinternship.models.BookModel;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    ListView listView;

    String strBook[] = {"Book 1", "Book 2", "Book 3", "Book 4", "Book 5", "Book 6", "Book 7", "Book 8"};
    int imgBook[] = {R.drawable.ic_home, R.drawable.ic_home, R.drawable.ic_home,
            R.drawable.ic_home, R.drawable.ic_home, R.drawable.ic_home, R.drawable.ic_home, R.drawable.ic_home};

    ArrayList<BookModel> bookModelArrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_home, container, false);
        listView = rootview.findViewById(R.id.list_view);
        bookModelArrayList = new ArrayList<BookModel>();
        for (int i = 0; i < strBook.length; i++) {
            BookModel bookModel = new BookModel(strBook[i], imgBook[i]);
            bookModelArrayList.add(bookModel);
        }


        BookAdapter bookAdapter = new BookAdapter(getActivity(),bookModelArrayList);
        listView.setAdapter(bookAdapter);


        return rootview;
    }
}