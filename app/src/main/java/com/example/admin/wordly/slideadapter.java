package com.example.admin.wordly;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static android.content.Intent.FLAG_ACTIVITY_NO_HISTORY;
import static android.content.Intent.makeMainActivity;
import static java.security.AccessController.getContext;

public class slideadapter extends PagerAdapter {
    Context context;
    LayoutInflater inflater;

    public int[] images={
            R.drawable.logo,
            R.drawable.slide2,
            R.drawable.slide3,
            R.drawable.logo
    };

    public String[] titles={
            "Welcome to Wordly",
            "Learn Words",
            "Assess yourself",
            "Start Test"
    };

    public String[] buttontext={
            "",
            "",
            "",
            "hello"
    };

    public String[] swipe={
            "swipe left",
            "swipe left",
            "swipe left",
            ""
    };

    public int[] background= {
            Color.rgb(255,255,255),
            Color.rgb(255,255,255),
            Color.rgb(255,255,255),  //should be <255
            Color.rgb(255,255,255)
    };

    public slideadapter(Context context)
    {
        this.context=context;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (RelativeLayout)object);
    }

    @Override
    public Object instantiateItem(final ViewGroup container, int position) {
        inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=  inflater.inflate(R.layout.slide1,container,false);
        final RelativeLayout slidelinearlayout=(RelativeLayout) view.findViewById(R.id.sliderelativelayout);
       ImageView slideimageview= (ImageView) view.findViewById(R.id.slideimage);
        TextView slidetitle= (TextView) view.findViewById(R.id.slidetitle);
        slidelinearlayout.setBackgroundColor(background[position]);
       slideimageview.setImageResource(images[position]);
        slidetitle.setText(titles[position]);
        TextView slideswipe= (TextView) view.findViewById(R.id.slideswipe);
        slideswipe.setText(swipe[position]);
        if(position==3)
        {
            slidetitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(v.getContext(),finalquiz.class);
                    ((slider)context).finish();
                    context.startActivity(intent);

                }
            });
        }

        container.addView(view);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);

    }


}
