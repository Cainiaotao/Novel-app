package com.tantao.novel.view.main.adpater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tantao.novel.R;
import com.youth.banner.Banner;

import java.util.List;

/**
 * Created by tantao on 2017/10/19.
 */

public class NovelAdpater extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private final int TYPE_ONE = 0;
    private final int TYPE_TWO = 1;
    private final int TYPE_THREE = 2;
    private final int TYPE_FOUR = 3;
    private int currentType;
    private Context context;


    public NovelAdpater(Context context) {
        this.context = context;
    }

    /** 加载显示不同的item **/
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case TYPE_ONE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_novel_type0, parent, false);
                return new ViewHolderfirstType(view);
            case TYPE_TWO:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_novel_type1, parent, false);
                return new ViewHoldersecondType(view);
            case TYPE_THREE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_novel_type2, parent, false);
                return new ViewHolderThreeType(view);
            case TYPE_FOUR:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_novel_type3, parent, false);
                return new ViewHolderfourType(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolderfirstType){

        }else if (holder instanceof ViewHoldersecondType){

        }else if (holder instanceof ViewHolderThreeType){

        }else if (holder instanceof ViewHolderfourType){

        }
    }



    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    class ViewHolderfirstType extends RecyclerView.ViewHolder{
        EditText novel_search_et;
        Button novel_search_btn;

        public ViewHolderfirstType(View itemView) {
            super(itemView);
            novel_search_et = (EditText) itemView.findViewById(R.id.novel_search_editText);
            novel_search_btn = (Button) itemView.findViewById(R.id.novel_search_button);
        }
    }

    class ViewHoldersecondType extends RecyclerView.ViewHolder{
        Banner novel_ad_banner;

        public ViewHoldersecondType(View itemView) {
            super(itemView);
            novel_ad_banner = (Banner) itemView.findViewById(R.id.novel_Banner);
        }
    }

    class ViewHolderThreeType extends RecyclerView.ViewHolder{
        LinearLayout novel0;
        LinearLayout novel1;
        LinearLayout novel2;
        LinearLayout novel3;
        LinearLayout novel4;
        LinearLayout novel5;
        LinearLayout novel6;
        LinearLayout novel7;
        LinearLayout novel8;
        LinearLayout novel_more;

        public ViewHolderThreeType(View itemView) {
            super(itemView);
            novel0 = (LinearLayout) itemView.findViewById(R.id.novel_type0);
            novel1 = (LinearLayout) itemView.findViewById(R.id.novel_type1);
            novel2 = (LinearLayout) itemView.findViewById(R.id.novel_type2);
            novel3 = (LinearLayout) itemView.findViewById(R.id.novel_type3);
            novel4 = (LinearLayout) itemView.findViewById(R.id.novel_type4);
            novel5 = (LinearLayout) itemView.findViewById(R.id.novel_type5);
            novel6 = (LinearLayout) itemView.findViewById(R.id.novel_type6);
            novel7 = (LinearLayout) itemView.findViewById(R.id.novel_type7);
            novel8 = (LinearLayout) itemView.findViewById(R.id.novel_type8);
            novel_more = (LinearLayout) itemView.findViewById(R.id.novel_type9);
        }
    }

    class ViewHolderfourType extends RecyclerView.ViewHolder{
        ImageView novel_follow_img;
        TextView novel_follow_title,novel_follow_desp;
        Button novel_follow_btn;

        public ViewHolderfourType(View itemView) {
            super(itemView);
            novel_follow_img = (ImageView) itemView.findViewById(R.id.novel_follow_imageView);
            novel_follow_title = (TextView)itemView.findViewById(R.id.novel_followTitle_textView);
            novel_follow_desp = (TextView)itemView.findViewById(R.id.novel_followDesp_textView);
            novel_follow_btn = (Button) itemView.findViewById(R.id.novel_follow_button);

        }
    }
}
