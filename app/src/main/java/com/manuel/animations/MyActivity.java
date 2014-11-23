package com.manuel.animations;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;


public class MyActivity extends ActionBarActivity {

    ViewPager viewPager;
    MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);


        mAdapter = new MyAdapter(getSupportFragmentManager());
        viewPager=(ViewPager)findViewById(R.id.pager);

        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());

        viewPager.setAdapter(mAdapter);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });

    }

    /** ----------------------------Page transformer --------------------------**/

    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float v) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (v < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (v <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(v));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (v < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }

    }

    /** -------------------------- ADAPTADOR ------------------------------------*/

    class MyAdapter extends FragmentStatePagerAdapter {


        ArrayList<FragmentPrueba> list;

        public MyAdapter(FragmentManager fm) {
            super(fm);
            list = new ArrayList<FragmentPrueba>();
            int a=0;

            for (int i=0;i<10;i++){
                a +=1;
                int color = 0;
                switch (a){
                    case 1: color = Color.CYAN;
                        break;
                    case 2: color = Color.MAGENTA;
                        break;
                    case 3: color = Color.YELLOW;
                        a =0;
                        break;
                }

               Bundle bundle = new Bundle();
                bundle.putInt("Color",color);

                FragmentPrueba fragment =new FragmentPrueba();
                fragment.setArguments(bundle);

                list.add(fragment);

            }
        }



        @Override
        public Fragment getItem(int position) {

            Fragment fragment = null;

            for (int i=0;i<10;i++){

                if (position == i){
                    fragment = list.get(i);
                }

            }

            return fragment;
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }


    
}
