package com.myappinternship.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.myappinternship.R;
import com.myappinternship.adapters.SliderAdapter;
import com.myappinternship.models.SliderItem;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class ImageSliderActivity extends AppCompatActivity {

    String strImag[]= {"Android","Java","PHP","C","C++"};
    int imgData[] = {R.drawable.ic_home,R.drawable.icon_2,
            R.drawable.ic_home,R.drawable.icon_2,R.drawable.ic_home};
    private ArrayList<SliderItem> sliderItemArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_slider);

        SliderView sliderView = findViewById(R.id.imageSlider);
        sliderItemArrayList = new ArrayList<SliderItem>();
        for (int i = 0; strImag.length > i; i++) {
                SliderItem sliderItem = new SliderItem(strImag[i], imgData[i]);
            sliderItemArrayList.add(sliderItem);
        }

        SliderAdapter adapter = new SliderAdapter(ImageSliderActivity.this,sliderItemArrayList);

        sliderView.setSliderAdapter(adapter);

        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();

    }
}