package com.tantao.novel.view.main.adpater;

import android.support.v7.widget.RecyclerView;
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


    public NovelAdpater() {
        super();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
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

    class ViewHoldersecondType{
        Banner novel_ad_banner;
    }

    class ViewHolderThreeType{
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
    }

    class ViewHolderfourType{
        ImageView novel_follow_img;
        TextView novel_follow_title,novel_follow_desp;
        Button novel_follow_btn;
    }
}
