package com.tantao.novel.view.main.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tantao.novel.R;
import com.tantao.novel.base.MeBean;

import java.util.List;

/**
 * Created by tantao on 2017/10/18.
 */

public class MeListViewAdpater extends BaseAdapter{

    public static final int ITEM_ONE = 0;
    public static final int ITEM_TWO = 1;
    public static final int ITEM_THREE = 2;
    public static final int TYPE_VIEW = 3;

    private Context context;
    private LayoutInflater inflater;
    private List<MeBean> mData;
    public MeListViewAdpater(Context context,List<MeBean> mData) {
        this.context = context;
        this.mData = mData;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (mData != null)return mData.size();
        return 0;
    }

    @Override
    public MeBean getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /** 返回3种不同的布局 **/
    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return ITEM_ONE;
        }else if (position == 1){
            return ITEM_TWO;
        }else{
            return ITEM_THREE;
        }
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_VIEW;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder1 holder1=null;
        Holder2 holder2=null;
        Hodler3 hodler3=null;
        int type = getItemViewType(position);
        if (convertView == null) {
            switch (type){
                case ITEM_ONE:
                    convertView = inflater.inflate(R.layout.listview_me_type0,null);
                    holder1 = new Holder1();
                    break;
                case ITEM_TWO:
                    convertView = inflater.inflate(R.layout.listview_me_type1,null);
                    holder2 = new Holder2();
                    break;
                case ITEM_THREE:
                    convertView = inflater.inflate(R.layout.listview_me_type2,null);
                    hodler3 = new Hodler3();
                    break;
            }
        }
        return convertView;
    }

    public class Holder1{
        ImageView my_image;
        TextView my_name,my_desp;
        ImageView my_setting;
    }

    public class Holder2{
        ImageView my_vip_img,my_shop_img,my_walkt_img;
        TextView my_vip,my_shop,my_walkt;
    }

    public class Hodler3{
        ImageView mylist_img,my_go_img;
        TextView my_list_name;
    }

}


