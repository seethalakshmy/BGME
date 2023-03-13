package com.bgmiddleeast.residemenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bgmiddleeast.R;

/**
 * User: special
 * Date: 13-12-10
 * Time: 下午11:05
 * Mail: specialcyci@gmail.com
 */
public class ResideMenuHeader extends LinearLayout {

    /** menu item  icon  */
    private ImageView iv_icon;
    /** menu item  title */


    public ResideMenuHeader(Context context) {
        super(context);
        initViews(context);
    }

    public ResideMenuHeader(Context context, int icon) {
        super(context);
        initViews(context);
        iv_icon.setImageResource(icon);

    }



    private void initViews(Context context){
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.residemenu_header, this);
        iv_icon = (ImageView) findViewById(R.id.iv_icon);

    }

    /**
     * set the icon color;
     *
     * @param icon
     */
    public void setIcon(int icon){
        iv_icon.setImageResource(icon);
    }



}
