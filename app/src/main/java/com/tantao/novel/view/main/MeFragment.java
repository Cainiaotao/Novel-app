package com.tantao.novel.view.main;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tantao.novel.R;
import com.tantao.novel.base.MeBean;
import com.tantao.novel.view.main.adpater.MeListViewAdpater;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 *
 * to handle interaction events.
 * Use the {@link MeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private ListView melist;
    private MeListViewAdpater meListViewAdpater;
    private List<MeBean> mData;



    public MeFragment() {
        // Required empty public constructor
    }

    public static MeFragment newInstance(String param1, String param2) {
        MeFragment fragment = new MeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_me, container, false);
        initView(view);


        return view;
    }

    private void initView(View view) {
        melist = (ListView) view.findViewById(R.id.me_list);
        mData = new ArrayList<>();
        mData.add(new MeBean("0"));
        mData.add(new MeBean("1"));
        mData.add(new MeBean("2"));
        mData.add(new MeBean("3"));
        mData.add(new MeBean("4"));
        mData.add(new MeBean("5"));
        mData.add(new MeBean("6"));
        meListViewAdpater = new MeListViewAdpater(getContext(),mData);
        melist.setAdapter(meListViewAdpater);
    }


    @Override
    public void onDetach() {
        super.onDetach();

    }



}
