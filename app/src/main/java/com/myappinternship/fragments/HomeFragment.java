package com.myappinternship.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.myappinternship.activity.ImageSliderActivity;
import com.myappinternship.adapters.BookAdapter;
import com.myappinternship.R;
import com.myappinternship.adapters.SliderAdapter;
import com.myappinternship.models.BookModel;
import com.myappinternship.models.SliderItem;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    ListView listView;

    String strBook[] = {"Book 1", "Book 2", "Book 3", "Book 4", "Book 5", "Book 6", "Book 7", "Book 8"};
    int imgBook[] = {R.drawable.ic_home, R.drawable.ic_home, R.drawable.ic_home,
            R.drawable.ic_home, R.drawable.ic_home, R.drawable.ic_home, R.drawable.ic_home, R.drawable.ic_home};

    ArrayList<BookModel> bookModelArrayList;

    /*Slider Code*/
    String strImag[]= {"Android","Java","PHP","C","C++"};

    int imgData[] = {R.drawable.careerafter,R.drawable.ic_auto12,
            R.drawable.ic_service3,R.drawable.subject_combination,R.drawable.teaching_reedsws};
    private ArrayList<SliderItem> sliderItemArrayList;


    /*over Slider Code*/
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

        /*Slider Code*/


        SliderView sliderView = rootview.findViewById(R.id.imageSlider);
        sliderItemArrayList = new ArrayList<SliderItem>();
        for (int i = 0; strImag.length > i; i++) {
            SliderItem sliderItem = new SliderItem(strImag[i], imgData[i]);
            sliderItemArrayList.add(sliderItem);
        }

        SliderAdapter adapter = new SliderAdapter(getActivity(),sliderItemArrayList);

        sliderView.setSliderAdapter(adapter);

        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();


        /*over Slider Code*/



        return rootview;
    }
}