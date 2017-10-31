package com.tantao.novel.view.main.adpater;

/**
 * Created by tantao on 2017/10/31.///
 */

public class NovelTypeBean {
    private String followName;
    private String followNumber;

    public NovelTypeBean(){

    }
    public NovelTypeBean(String followName, String followNumber) {
        this.followName= followName;
        this.followNumber = followNumber;
    }

    public String getFollowName(){
        return followName;
    }

    public String getFollowNumber(){
        return followNumber;
    }

    public void setFollowName(String followName){
        this.followName= followName;
    }

    public void setFollowNumber(String followNumber){
        this.followNumber = followNumber;
    }
}
