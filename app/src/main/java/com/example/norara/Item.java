package com.example.norara;

public class Item {
    String link; // 행사 이미지
    String title; // 행사제목
    String add1; // 주소1
    String add2; // 주소2
    String eventstartdate; // 축제 시작 날짜
    String eventenddate; // 축제 끝나는 날짜

    public String getLink(){
        return link;
    }

    public String getTitle(){
        return title;
    }
    public String getAdd1(){
        return add1;
    }
    public String getAdd2(){
        return add2;
    }
    public String getEventstartdate(){
        return eventstartdate;
    }
    public String getEventenddate(){
        return eventenddate;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setAdd1(String add1){
        this.add1 = add1;
    }
    public void setAdd2(String add2){
        this.add2 = add2;
    }
    public void setEventstartdate(String eventstartdate){
        this.eventstartdate = eventstartdate;
    }
    public void setEventenddate(String eventenddate){
        this.eventenddate = eventenddate;
    }

}
