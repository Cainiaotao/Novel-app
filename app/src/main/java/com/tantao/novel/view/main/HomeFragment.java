package com.tantao.novel.view.main;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.Toast;

import com.tantao.novel.R;
import com.tantao.novel.base.BaseWebView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 *
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {


    private Button SearchBtn,MoreBtn;
    private TabHost tabHost;
    private RelativeLayout netWorklayout,netWork1layout;
    private LinearLayout homeTitlelayout;
    private WebView webView,webViewComics;
    private WebSettings webSettings,webComicsSettings;
    private SwipeRefreshLayout newSwipeRefreshLayout;
    private final String newsUrl="https://sina.cn/?wm=4007";
    private final String comicsUrl="http://m.ac.qq.com/";

    private Handler handler= new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    webViewGoBack();
                    break;
            }
        }
    };

    private void webViewGoBack(){
        webView.goBack();
    }
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;




    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        initTabHost(view);
        initWebView(view);
        initComicsWebView(view);

        return view;
    }

    private void initTabHost(View view){
        SearchBtn = (Button)view.findViewById(R.id.home_search_button);
        MoreBtn = (Button)view.findViewById(R.id.home_more_button);

        tabHost = (TabHost) view.findViewById(R.id.home_tabHost);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("news").setIndicator("新闻").setContent(R.id.tab_news));
        tabHost.addTab(tabHost.newTabSpec("novel").setIndicator("小说").setContent(R.id.tab_novel));
        tabHost.addTab(tabHost.newTabSpec("comics").setIndicator("漫画").setContent(R.id.tab_comics));

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (tabId.equals("news")) {   //第一个标签
                    Toast.makeText(getActivity(), "新闻", Toast.LENGTH_SHORT).show();
                }
                if (tabId.equals("novel")) {   //第二个标签
                    Toast.makeText(getActivity(), "小说", Toast.LENGTH_SHORT).show();
                }
                if (tabId.equals("comics")) {   //第三个标签
                    Toast.makeText(getActivity(), "漫画", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void initWebView(View view){
        newSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.news_swipe);
        homeTitlelayout = (LinearLayout)  view.findViewById(R.id.home_title);
        netWorklayout = (RelativeLayout) view.findViewById(R.id.no_network0);
        webView = (WebView) view.findViewById(R.id.news_webView);
        newSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        newSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //重新刷新页面
                webView.loadUrl(webView.getUrl());
            }
        });


        webView.loadUrl(newsUrl);
        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        //取消滚动条
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        //触摸焦点起作用
        webView.requestFocus();
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.d("TAG",url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
              if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                  view.loadUrl(request.getUrl().toString());
              }else {
                  view.loadUrl(request.toString());
              }
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                netWorklayout.setVisibility(View.VISIBLE);
            }
        });

        //设置进度条
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    //隐藏进度条
                    newSwipeRefreshLayout.setRefreshing(false);
                } else {
                    if (!newSwipeRefreshLayout.isRefreshing())
                        newSwipeRefreshLayout.setRefreshing(true);
                }
                super.onProgressChanged(view, newProgress);
            }
        });


        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()){
                   handler.sendEmptyMessage(1);
                   return true;
                }
                return false;
            }
        });


    }

    private void initComicsWebView(View view){
        netWork1layout = (RelativeLayout) view.findViewById(R.id.no_network2);
        webViewComics = (WebView) view.findViewById(R.id.comics_webView);
        webComicsSettings = webViewComics.getSettings();
        webComicsSettings.setJavaScriptEnabled(true);
        //webComicsSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webComicsSettings.setUseWideViewPort(true);
        webComicsSettings.setLoadWithOverviewMode(true);
        webViewComics.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    view.loadUrl(request.getUrl().toString());
                }else {
                    view.loadUrl(request.toString());
                }
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                netWork1layout.setVisibility(View.VISIBLE);
            }
        });

        webViewComics.loadUrl(comicsUrl);



    }




    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //clearCache
        webView.clearCache(true);
        webView.clearHistory();
        webView.clearFormData();
        webView.destroy();
    }
}
