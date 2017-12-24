package com.hixyks.checkout.web_app.enums;

public enum DefaultCategory {
    all("所有",-10), noCategory("未分类",-5);
    
    private String name;
    private int id;

    private DefaultCategory(String name, int id) {
        this.name = name;
        this.id = id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getId() {
        return this.id;
    }
}
