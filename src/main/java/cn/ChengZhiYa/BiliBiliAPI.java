package cn.ChengZhiYa;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

public class BiliBiliAPI {
    public static String 获取个人信息JSON(Integer UID) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).
                setConnectionRequestTimeout(5000)
                .setSocketTimeout(5000)
                .setRedirectsEnabled(true)
                .build();
        HttpGet httpGet = new HttpGet("https://api.bilibili.com/x/space/acc/info?mid=" + UID);

        //伪装成合法浏览器
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36 Edg/108.0.1462.54");

        httpGet.setConfig(requestConfig);
        HttpResponse httpResponse = httpClient.execute(httpGet);
        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            return EntityUtils.toString(httpResponse.getEntity());
        }
        return null;
    }

    //个人信息API
    public static Integer 获取B站粉丝牌等级(String jsonResult) {
        JSONObject jsonObject = JSONObject.parseObject(jsonResult);
        JSONObject UserDataJSON = JSONObject.parseObject(jsonObject.getString("data"));
        JSONObject FansMedalDataJSON = JSONObject.parseObject(UserDataJSON.getString("fans_medal"));
        if (JSONObject.parseObject(FansMedalDataJSON.getString("medal")) == null) {
            return 0;
        }
        JSONObject MedalDataJSON = JSONObject.parseObject(FansMedalDataJSON.getString("medal"));
        return MedalDataJSON.getIntValue("level");
    }
    public static Integer 获取B站粉丝牌创建者UID(String jsonResult) {
        JSONObject jsonObject = JSONObject.parseObject(jsonResult);
        JSONObject UserDataJSON = JSONObject.parseObject(jsonObject.getString("data"));
        JSONObject FansMedalDataJSON = JSONObject.parseObject(UserDataJSON.getString("fans_medal"));
        if (JSONObject.parseObject(FansMedalDataJSON.getString("medal")) == null) {
            return 0;
        }
        JSONObject MedalDataJSON = JSONObject.parseObject(FansMedalDataJSON.getString("medal"));
        return MedalDataJSON.getIntValue("target_id");
    }
    public static String 获取B站粉丝牌创建者(String jsonResult) throws IOException {
        JSONObject jsonObject = JSONObject.parseObject(jsonResult);
        JSONObject UserDataJSON = JSONObject.parseObject(jsonObject.getString("data"));
        JSONObject FansMedalDataJSON = JSONObject.parseObject(UserDataJSON.getString("fans_medal"));
        if (JSONObject.parseObject(FansMedalDataJSON.getString("medal")) == null) {
            return "没有粉丝牌";
        }
        JSONObject MedalDataJSON = JSONObject.parseObject(FansMedalDataJSON.getString("medal"));
        return 获取B站名称(获取个人信息JSON(MedalDataJSON.getIntValue("target_id")));
    }
    public static String 获取B站粉丝牌名称(String jsonResult) {
        JSONObject jsonObject = JSONObject.parseObject(jsonResult);
        JSONObject UserDataJSON = JSONObject.parseObject(jsonObject.getString("data"));
        JSONObject FansMedalDataJSON = JSONObject.parseObject(UserDataJSON.getString("fans_medal"));
        if (JSONObject.parseObject(FansMedalDataJSON.getString("medal")) == null) {
            return "没有粉丝牌";
        }
        JSONObject MedalDataJSON = JSONObject.parseObject(FansMedalDataJSON.getString("medal"));
        if (MedalDataJSON.getString("medal_name") != null) {
            return MedalDataJSON.getString("medal_name");
        }
        return null;
    }
    public static String 获取B站性别(String jsonResult) {
        JSONObject jsonObject = JSONObject.parseObject(jsonResult);
        JSONObject UserDataJSON = JSONObject.parseObject(jsonObject.getString("data"));
        if (UserDataJSON.getString("sex") != null) {
            return UserDataJSON.getString("sex");
        }
        return null;
    }
    public static String 获取B站名称(String jsonResult) {
        JSONObject jsonObject = JSONObject.parseObject(jsonResult);
        JSONObject UserDataJSON = JSONObject.parseObject(jsonObject.getString("data"));
        if (UserDataJSON.getString("name") != null) {
            return UserDataJSON.getString("name");
        }
        return null;
    }
    public static String 获取B站直播间链接(String jsonResult) {
        JSONObject jsonObject = JSONObject.parseObject(jsonResult);
        JSONObject UserDataJSON = JSONObject.parseObject(jsonObject.getString("data"));
        JSONObject LiveJSON = JSONObject.parseObject(UserDataJSON.getString("live_room"));
        if (LiveJSON == null) {
            return "没有直播间!";
        }
        return "https://live.bilibili.com/" + LiveJSON.getString("roomid");
    }
    public static boolean 获取B站直播状态(String jsonResult) {
        JSONObject jsonObject = JSONObject.parseObject(jsonResult);
        JSONObject UserDataJSON = JSONObject.parseObject(jsonObject.getString("data"));
        JSONObject LiveJSON = JSONObject.parseObject(UserDataJSON.getString("live_room"));
        if (LiveJSON == null) {
            return false;
        }
        return LiveJSON.getInteger("liveStatus") == 1;
    }
    public static String 获取B站简介(String jsonResult) {
        JSONObject jsonObject = JSONObject.parseObject(jsonResult);
        JSONObject UserDataJSON = JSONObject.parseObject(jsonObject.getString("data"));
        if (UserDataJSON.getString("sign") != null) {
            return UserDataJSON.getString("sign");
        }
        return null;
    }
    public static Integer 获取B站等级(String jsonResult) {
        JSONObject jsonObject = JSONObject.parseObject(jsonResult);
        JSONObject DataJSON = JSONObject.parseObject(jsonObject.getString("data"));
        return DataJSON.getIntValue("level");
    }
    public static String 获取B站头像图片链接(String jsonResult) {
        JSONObject jsonObject = JSONObject.parseObject(jsonResult);
        JSONObject DataJSON = JSONObject.parseObject(jsonObject.getString("data"));
        return DataJSON.getString("face");
    }
    public static List<String> 获取B站个人标签(String jsonResult) {
        JSONObject jsonObject = JSONObject.parseObject(jsonResult);
        JSONObject DataJSON = JSONObject.parseObject(jsonObject.getString("data"));
        return JSON.parseArray(DataJSON.getString("tags"), String.class);
    }

    //动态API
    public static List<String> 获取B站动态合集(Integer UID) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).
                setConnectionRequestTimeout(5000)
                .setSocketTimeout(5000)
                .setRedirectsEnabled(true)
                .build();
        HttpGet httpGet = new HttpGet("https://api.vc.bilibili.com/dynamic_svr/v1/dynamic_svr/space_history?host_uid=" + UID);

        //伪装成合法浏览器
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36 Edg/108.0.1462.54");

        httpGet.setConfig(requestConfig);
        HttpResponse httpResponse = httpClient.execute(httpGet);
        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            String jsonResult = EntityUtils.toString(httpResponse.getEntity());
            JSONObject jsonObject = JSONObject.parseObject(jsonResult);
            JSONObject DataJSON = JSONObject.parseObject(jsonObject.getString("data"));
            return JSON.parseArray(DataJSON.getString("cards"), String.class);
        }
        return null;
    }
    public static Integer 获取动态浏览量(String DynamicJSONString) {
        JSONObject jsonObject = JSONObject.parseObject(DynamicJSONString);
        JSONObject DescDataJSON = JSONObject.parseObject(jsonObject.getString("desc"));
        return DescDataJSON.getIntValue("view");
    }
    public static Integer 获取动态点赞量(String DynamicJSONString) {
        JSONObject jsonObject = JSONObject.parseObject(DynamicJSONString);
        JSONObject DescDataJSON = JSONObject.parseObject(jsonObject.getString("desc"));
        return DescDataJSON.getIntValue("like");
    }
    public static String 获取动态链接(String DynamicJSONString) {
        JSONObject jsonObject = JSONObject.parseObject(DynamicJSONString);
        JSONObject DescDataJSON = JSONObject.parseObject(jsonObject.getString("desc"));
        return "https://t.bilibili.com/" + DescDataJSON.getString("dynamic_id_str");
    }
    public static String 获取动态内容(String DynamicJSONString) {
        JSONObject jsonObject = JSONObject.parseObject(DynamicJSONString);
        JSONObject CardDataJSON = JSONObject.parseObject(jsonObject.getString("card"));
        if (CardDataJSON.getString("item") == null) {
            return "无法从最新动态获取到内容";
        }
        JSONObject ItemDataJSON = JSONObject.parseObject(CardDataJSON.getString("item"));
        return ItemDataJSON.getString("content");
    }
    public static Integer 获取动态类型(String DynamicJSONString) {
        JSONObject jsonObject = JSONObject.parseObject(DynamicJSONString);
        JSONObject DescDataJSON = JSONObject.parseObject(jsonObject.getString("desc"));
        return DescDataJSON.getIntValue("type");
    }

    //依靠最新动态做出的获取最新视频
    public static String 获取最新视频标题(String DynamicJSONString) {
        JSONObject jsonObject = JSONObject.parseObject(DynamicJSONString);
        JSONObject CardDataJSON = JSONObject.parseObject(jsonObject.getString("card"));
        if (CardDataJSON.getString("title") == null) {
            return "无法从最新动态获取到最新视频";
        }
        return CardDataJSON.getString("title");
    }
    public static String 获取最新视频简介(String DynamicJSONString) {
        JSONObject jsonObject = JSONObject.parseObject(DynamicJSONString);
        JSONObject CardDataJSON = JSONObject.parseObject(jsonObject.getString("card"));
        if (CardDataJSON.getString("desc") == null) {
            return "无法从最新动态获取到最新视频";
        }
        return CardDataJSON.getString("desc");
    }
    public static String 获取最新视频链接(String DynamicJSONString) {
        JSONObject jsonObject = JSONObject.parseObject(DynamicJSONString);
        JSONObject CardDataJSON = JSONObject.parseObject(jsonObject.getString("card"));
        if (CardDataJSON.getString("short_link") == null) {
            return "无法从最新动态获取到最新视频";
        }
        return CardDataJSON.getString("short_link");
    }
    public static String 获取最新视频封面图片链接(String DynamicJSONString) {
        JSONObject jsonObject = JSONObject.parseObject(DynamicJSONString);
        JSONObject CardDataJSON = JSONObject.parseObject(jsonObject.getString("card"));
        if (CardDataJSON.getString("pic") == null) {
            return "无法从最新动态获取到最新视频";
        }
        return CardDataJSON.getString("pic");
    }
    public static String 获取最新视频视频分类(String DynamicJSONString) {
        JSONObject jsonObject = JSONObject.parseObject(DynamicJSONString);
        JSONObject CardDataJSON = JSONObject.parseObject(jsonObject.getString("card"));
        if (CardDataJSON.getString("tname") == null) {
            return "无法从最新动态获取到最新视频";
        }
        return CardDataJSON.getString("tname");
    }
    public static Integer 获取最新视频播放量(String DynamicJSONString) {
        JSONObject jsonObject = JSONObject.parseObject(DynamicJSONString);
        JSONObject CardDataJSON = JSONObject.parseObject(jsonObject.getString("card"));
        if (CardDataJSON.getString("stat") == null) {
            return 0;
        }
        JSONObject StatDataJSON = JSONObject.parseObject(CardDataJSON.getString("stat"));
        return StatDataJSON.getIntValue("view");
    }
    public static Integer 获取最新视频点赞量(String DynamicJSONString) {
        JSONObject jsonObject = JSONObject.parseObject(DynamicJSONString);
        JSONObject CardDataJSON = JSONObject.parseObject(jsonObject.getString("card"));
        if (CardDataJSON.getString("stat") == null) {
            return 0;
        }
        JSONObject StatDataJSON = JSONObject.parseObject(CardDataJSON.getString("stat"));
        return StatDataJSON.getIntValue("like");
    }
    public static Integer 获取最新视频投币量(String DynamicJSONString) {
        JSONObject jsonObject = JSONObject.parseObject(DynamicJSONString);
        JSONObject CardDataJSON = JSONObject.parseObject(jsonObject.getString("card"));
        if (CardDataJSON.getString("stat") == null) {
            return 0;
        }
        JSONObject StatDataJSON = JSONObject.parseObject(CardDataJSON.getString("stat"));
        return StatDataJSON.getIntValue("coin");
    }

    //统计API
    public static Integer 获取B站关注数(Integer UID) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).
                setConnectionRequestTimeout(5000)
                .setSocketTimeout(5000)
                .setRedirectsEnabled(true)
                .build();
        HttpGet httpGet = new HttpGet("https://api.bilibili.com/x/relation/stat?vmid=" + UID);

        //伪装成合法浏览器
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36 Edg/108.0.1462.54");

        httpGet.setConfig(requestConfig);
        HttpResponse httpResponse = httpClient.execute(httpGet);
        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            String jsonResult = EntityUtils.toString(httpResponse.getEntity());
            JSONObject jsonObject = JSONObject.parseObject(jsonResult);
            JSONObject DataJSON = JSONObject.parseObject(jsonObject.getString("data"));
            return DataJSON.getIntValue("following");
        }
        return 0;
    }
    public static Integer 获取B站粉丝数(Integer UID) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).
                setConnectionRequestTimeout(5000)
                .setSocketTimeout(5000)
                .setRedirectsEnabled(true)
                .build();
        HttpGet httpGet = new HttpGet("https://api.bilibili.com/x/relation/stat?vmid=" + UID);

        //伪装成合法浏览器
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36 Edg/108.0.1462.54");

        httpGet.setConfig(requestConfig);
        HttpResponse httpResponse = httpClient.execute(httpGet);
        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            String jsonResult = EntityUtils.toString(httpResponse.getEntity());
            JSONObject jsonObject = JSONObject.parseObject(jsonResult);
            JSONObject DataJSON = JSONObject.parseObject(jsonObject.getString("data"));
            return DataJSON.getIntValue("follower");
        }
        return 0;
    }
}
