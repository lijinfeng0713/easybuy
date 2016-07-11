package com.gentlemen.easybuy.model;

/**
 * Created by ljf-梁燕双栖 on 2016/6/30.
 */
public class MyURL {

    //根路径
    private static final String BASE_URL = "http://192.168.1.158:8080";

    //登录URL
    public static final String LOGIN_URL = BASE_URL+"/api/logging/login/user";

    //注册URL
    public static final String REGISTER_URL = BASE_URL+"/api/user";

    //查询所有货物
    public static final String queryAllURL = BASE_URL+"";

    //首页URL
    public static final String INDEX_URL = BASE_URL+"/api/goods";

    //加入购物车
    public static final String ADD_TO_CART_URL = BASE_URL+"/api/user/order/cart/add";

    //查询货物
    public static final String QUERY_BY_CONDITION = BASE_URL+"/api/goods/search";
}
