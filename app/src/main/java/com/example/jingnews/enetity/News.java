package com.example.jingnews.enetity;

public class News {
    public String newsid;
    public String newstitle;
    public String newsdetail;
    public String newstype;
    public String newsdate;
    public String newstext;
    public int newsphoto;
    public String newsautor;

    public News(String newsid){
        this.newsid=newsid;
    }
    public News(String newsid,String newstitle){
        super();
        this.newsid=newsid;
        this.newstitle=newstitle;
    }
    public News(String newsid,String newstitle,String newsdetail){
        super();
        this.newsid=newsid;
        this.newstitle=newstitle;
        this.newsdetail=newsdetail;
    }
    public News(String newsid,String newstitle,String newsdetail,String newstype){
        super();
        this.newsid=newsid;
        this.newstitle=newstitle;
        this.newsdetail=newsdetail;
        this.newstype=newstype;
    }
    public News(String newsid,String newstitle,String newsdetail,String newstype,String newsdate){
        super();
        this.newsid=newsid;
        this.newstitle=newstitle;
        this.newsdetail=newsdetail;
        this.newstype=newstype;
        this.newsdate=newsdate;
    }
    public News(String newsid,String newstitle,String newsdetail,String newstype,String newsdate,String newstext){
        super();
        this.newsid=newsid;
        this.newstitle=newstitle;
        this.newsdetail=newsdetail;
        this.newstype=newstype;
        this.newsdate=newsdate;
        this.newstext=newstext;
    }
    public News(String newsid,String newstitle,String newsdetail,String newstype,String newsdate,String newstext,int newsphoto){
        super();
        this.newsid=newsid;
        this.newstitle=newstitle;
        this.newsdetail=newsdetail;
        this.newstype=newstype;
        this.newsdate=newsdate;
        this.newstext=newstext;
        this.newsphoto=newsphoto;
    }
    public News(String newsid,String newstitle,String newsdetail,String newsdate,int newsphoto,String newsautor){
        super();
        this.newsid=newsid;
        this.newstitle=newstitle;
        this.newsdetail=newsdetail;
        this.newsdate=newsdate;
        this.newsphoto=newsphoto;
        this.newsautor=newsautor;
    }

    public String getNewsid() {
        return newsid;
    }
    public void setNewsid(String newsid) {
        this.newsid= newsid;
    }

    public String getNewstitle(){
        return newstitle;
    }
    public void setNewstitle(String newstitle){
        this.newstitle=newstitle;
    }

    public  String getNewsdetail(){
        return newsdetail;
    }
    public  void setNewsdetail(String newsdetail){
        this.newsdetail=newsdetail;
    }

    public String getNewstype(){
        return newstype;
    }
    public void setNewstype(String newstype){
        this.newstype=newstype;
    }

    public String getNewsdate(){ return newsdate;}
    public void setNewsdate(String newsdate) { this.newsdate=newsdate;}

    public String getNewstext(){return newstext;}
    public void setNewstext(String newstext){this.newstext=newstext;}

    public int getNewsphoto(){return newsphoto;}
    public void setNewsphoto(int newsphoto){this.newsphoto=newsphoto;}

    public String getNewsautor(){return newsautor;}

    public void setNewsautor(String newsautor) {
        this.newsautor = newsautor;
    }
}
