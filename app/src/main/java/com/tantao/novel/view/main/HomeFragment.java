package com.tantao.novel.view.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.HttpAuthHandler;
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
import com.tantao.novel.view.main.adpater.NovelAdpater;
import com.tantao.novel.view.main.adpater.NovelTypeBean;
import com.tantao.novel.view.more.MoreActivity;
import com.tantao.novel.view.search.SearcActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 *
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {


    private Button SearchBtn,MoreBtn;
    private TabHost tabHost;
    private RelativeLayout netWorklayout,netWork1layout;
    private LinearLayout homeTitlelayout;
    private WebView webView,webViewComics;
    private WebSettings webSettings,webComicsSettings;
    private SwipeRefreshLayout newSwipeRefreshLayout;
    private final String newsUrl="https://sina.cn/?wm=4007";
    private final String comicsUrl="http://m.ac.qq.com/";


    private RecyclerView mRecyclerView;
    private NovelAdpater novelAdpater;
    private List<NovelTypeBean> mData;

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
        initNovelView(view);
        initComicsWebView(view);
        return view;//
    }

    private void initNovelView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.novel_recycler);
        mData = new ArrayList<>();
        mData.add(new NovelTypeBean("1","1"));
        mData.add(new NovelTypeBean("2","2"));
        mData.add(new NovelTypeBean("3","3"));
        mData.add(new NovelTypeBean("4","4"));
        mData.add(new NovelTypeBean("5","5"));
        novelAdpater = new NovelAdpater(getContext(),mData);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(novelAdpater);
    }

    private void initTabHost(View view){
        SearchBtn = (Button)view.findViewById(R.id.home_search_button);
        MoreBtn = (Button)view.findViewById(R.id.home_more_button);
        SearchBtn.setOnClickListener(this);
        MoreBtn.setOnClickListener(this);

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
                    if (request.getUrl().toString() == null)return false;
                    try {
                        if(request.getUrl().toString().startsWith("weixin://") //微信
                                || request.getUrl().toString().startsWith("alipays://") //支付宝
                                || request.getUrl().toString().startsWith("mailto://") //邮件
                                || request.getUrl().toString().startsWith("tel://")//电话
                                || request.getUrl().toString().startsWith("dianping://")//大众点评
                                || request.getUrl().toString().startsWith("intent://")
                            //其他自定义的scheme
                                ) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(request.toString()));
                            startActivity(intent);
                            return true;
                        }
                    } catch (Exception e) { //防止crash (如果手机上没有安装处理某个scheme开头的url的APP, 会导致crash)
                        return true;//没有安装该app时，返回true，表示拦截自定义链接，但不跳转，避免弹出上面的错误页面
                    }

                  view.loadUrl(request.getUrl().toString());
                }else {
                    if(request.toString() == null) return false;
                    try {
                        if(request.toString().startsWith("weixin://") //微信
                                || request.toString().startsWith("alipays://") //支付宝
                                || request.toString().startsWith("mailto://") //邮件
                                || request.toString().startsWith("tel://")//电话
                                || request.toString().startsWith("dianping://")//大众点评
                                || request.toString().startsWith("intent://")//新浪
                                //其他自定义的scheme
                                ) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(request.toString()));
                            startActivity(intent);
                            return true;
                        }
                    } catch (Exception e) { //防止crash (如果手机上没有安装处理某个scheme开头的url的APP, 会导致crash)
                        return true;//没有安装该app时，返回true，表示拦截自定义链接，但不跳转，避免弹出上面的错误页面
                    }
                    view.loadUrl(request.toString());
                }
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
                netWorklayout.setVisibility(View.VISIBLE);
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
        webViewComics.loadUrl(comicsUrl);
        webComicsSettings = webViewComics.getSettings();
        webComicsSettings.setJavaScriptEnabled(true);
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
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_search_button:
                startActivity(new Intent(getActivity(), SearcActivity.class));
                break;
            case R.id.home_more_button:
                startActivity(new Intent(getActivity(), MoreActivity.class));
                break;
        }
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
