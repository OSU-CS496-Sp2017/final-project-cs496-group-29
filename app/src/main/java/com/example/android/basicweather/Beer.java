package com.example.android.basicweather;

/**
 * Created by Vinod on 6/14/2017.
 */

public class Beer {

    private String name;
    private String createDate;
    private String categoryName;
    private String description;

    Beer(String n,  String create, String category,String d){

        name = n;
        createDate = create;
        categoryName = category;
        description = d;

    }

    public String get_name(){
        return name;
    }
    public String get_createDate(){
        return createDate;
    }
    public String get_categoryName(){
        return categoryName;
    }
    public String get_description(){
        return description;
    }

}
