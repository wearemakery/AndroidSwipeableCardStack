package com.wenchao.cardstack;

import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class CardUtils {
  final static int LEFT = 0;
  final static int TOP = 1;
  final static int RIGHT = 2;
  final static int BOTTOM = 3;

    public static void scale(View v, int pixel){
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)v.getLayoutParams();
        params.leftMargin -= pixel;
        params.rightMargin -= pixel;
        params.topMargin -= pixel;
        params.bottomMargin -= pixel;
        v.setLayoutParams(params);
    }

    public static LayoutParams getMoveParams(View v, int upDown,int leftRight){
        RelativeLayout.LayoutParams original = (RelativeLayout.LayoutParams)v.getLayoutParams();
        //RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(original);
        RelativeLayout.LayoutParams params = cloneParams(original);
        params.leftMargin += leftRight;
        params.rightMargin -= leftRight;
        params.topMargin -= upDown;
        params.bottomMargin += upDown;
        return params;
    }


  public static LayoutParams getMoveParamsUpDown(View v, int upDown) {
    RelativeLayout.LayoutParams original = (RelativeLayout.LayoutParams) v.getLayoutParams();
    RelativeLayout.LayoutParams params = cloneParams(original);
    params.topMargin += upDown;
    params.bottomMargin -= upDown;
    return params;
  }


  public static LayoutParams getMoveParamsLeftRight(View v, int leftRight) {
    RelativeLayout.LayoutParams original = (RelativeLayout.LayoutParams) v.getLayoutParams();
    RelativeLayout.LayoutParams params = cloneParams(original);
    params.leftMargin += leftRight;
    params.rightMargin -= leftRight;
    return params;
  }

  public static void move(View v, int upDown,int leftRight){
    RelativeLayout.LayoutParams params = getMoveParams(v,upDown,leftRight);
    v.setLayoutParams(params);
  }

    public static LayoutParams scaleFrom(View v, LayoutParams params, int pixel) {
        if(BuildConfig.DEBUG){
           Log.d("pixel", "onScroll: " + pixel);
        }
        params = cloneParams(params);
        params.leftMargin -= pixel;
        params.rightMargin -= pixel;
        params.topMargin -= pixel;
        params.bottomMargin -= pixel;
        if(BuildConfig.DEBUG){
           Log.d("pixel", "onScroll: " + pixel);
         }
        v.setLayoutParams(params);

        return params;
    }

    public static LayoutParams moveFrom(View v, LayoutParams params, int leftRight, int upDown) {
        params = cloneParams(params);
        params.leftMargin += leftRight;
        params.rightMargin -= leftRight;
        params.topMargin -= upDown;
        params.bottomMargin += upDown;
        v.setLayoutParams(params);

        return params;
    }

    //a copy method for RelativeLayout.LayoutParams for backward compartibility
    public static RelativeLayout.LayoutParams cloneParams(RelativeLayout.LayoutParams params){
        RelativeLayout.LayoutParams copy = new RelativeLayout.LayoutParams(params.width,params.height);
        copy.leftMargin = params.leftMargin;
        copy.topMargin = params.topMargin;
        copy.rightMargin = params.rightMargin;
        copy.bottomMargin = params.bottomMargin;
        int[] rules = params.getRules();
        for (int i = 0 ; i< rules.length; i++ ){
            copy.addRule(i,rules[i]);
        }
        //copy.setMarginStart(params.getMarginStart());
        //copy.setMarginEnd(params.getMarginEnd());

        return copy;
    }

    public static float distance(float x1, float y1, float x2, float y2) {

        return (float) Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
    }

  public static int direction(float x1, float y1, float x2, float y2) {
    float dX = Math.abs(x2 - x1);
    float dY = Math.abs(y2 - y1);

    if (dX > dY) {
      //HORIZONTAL
      if (x1 > x2) {
        //LEFT
        return 0;
      } else {
        //RIGHT
        return 2;
      }
    } else {
      //VERTICAL
      if (y1 > y2) {
        //UP
        return 1;
      } else {
        //DOWN
        return 3;
      }
    }
  }


}
