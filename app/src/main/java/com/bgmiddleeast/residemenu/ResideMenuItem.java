package com.bgmiddleeast.residemenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bgmiddleeast.R;


/**
 * User: special
 * Date: 13-12-10
 * Time: 下午11:05
 * Mail: specialcyci@gmail.com
 */
public class ResideMenuItem extends LinearLayout {

    /** menu item  icon  */
//    private ImageView iv_icon;
    /** menu item  title */
    private TextView tv_title;
private  ImageView iv_icon;



    public ResideMenuItem(Context context, int icon, int title,String language) {
        super(context);
        initViews(context,language);
       iv_icon.setImageResource(icon);
        tv_title.setText(title);
    }



    private void initViews(Context context,String language){
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(language.equals("ar"))
        inflater.inflate(R.layout.residemenu_item_ar, this);
        else
            inflater.inflate(R.layout.residemenu_item, this);
       iv_icon = (ImageView) findViewById(R.id.iv_icon);
        tv_title = (TextView) findViewById(R.id.tv_title);
    }

    /**
     * set the icon color;
     *
     * @param icon
     */
    public void setIcon(int icon){
        //iv_icon.setImageResource(icon);
    }

    /**
     * set the title with resource
     * ;
     * @param title
     */
    public void setTitle(int title){
        tv_title.setText(title);
    }

    /**
     * set the title with string;
     *
     * @param title
     */
    public void setTitle(String title){
        tv_title.setText(title);
    }
}
