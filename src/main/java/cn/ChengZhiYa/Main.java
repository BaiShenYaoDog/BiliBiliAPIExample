package cn.ChengZhiYa;

import java.io.IOException;
import java.util.Objects;

import static cn.ChengZhiYa.BiliBiliAPI.*;

public class Main {
    public static void main(String[] args) throws IOException {
        System.in.read();
        String UIDName = "503785557";
        Integer UID = 503785557;
        String_HashMap.set(UIDName,获取个人信息JSON(UID));
        System.out.println("B站名称:" + 获取B站名称(String_HashMap.get(UIDName)));
        System.out.println("B站性别:" + 获取B站性别(String_HashMap.get(UIDName)));
        System.out.println("B站头像图片链接:" + 获取B站头像图片链接(String_HashMap.get(UIDName)));
        System.out.println("B站简介:" + 获取B站简介(String_HashMap.get(UIDName)));
        System.out.println("B站等级:" + 获取B站等级(String_HashMap.get(UIDName)));
        System.out.println("B站个人标签:" + 获取B站个人标签(String_HashMap.get(UIDName)));
        System.out.println("B站粉丝数:" + 获取B站粉丝数(UID));
        System.out.println("B站关注数:" + 获取B站关注数(UID));
        System.out.println("B站粉丝牌等级:" + 获取B站粉丝牌等级(String_HashMap.get(UIDName)));
        System.out.println("B站粉丝牌创建者UID:" + 获取B站粉丝牌创建者UID(String_HashMap.get(UIDName)));
        System.out.println("B站粉丝牌创建者:" + 获取B站粉丝牌创建者(String_HashMap.get(UIDName)));
        System.out.println("B站粉丝牌名称:" + 获取B站粉丝牌名称(String_HashMap.get(UIDName)));
        //动态
        System.out.println("B站动态合集:" + 获取B站动态合集(UID));
        System.out.println("B站最新动态内容:" + 获取动态内容(Objects.requireNonNull(获取B站动态合集(UID)).get(0)));
        System.out.println("B站最新动态类型:" + 获取动态类型(Objects.requireNonNull(获取B站动态合集(UID)).get(0)));
        System.out.println("B站最新动态浏览量:" + 获取动态浏览量(Objects.requireNonNull(获取B站动态合集(UID)).get(0)));
        System.out.println("B站最新动态点赞量:" + 获取动态点赞量(Objects.requireNonNull(获取B站动态合集(UID)).get(0)));
        System.out.println("B站最新动态链接:" + 获取动态链接(Objects.requireNonNull(获取B站动态合集(UID)).get(0)));
        //视频
        System.out.println("B站最新视频标题:" + 获取最新视频标题(Objects.requireNonNull(获取B站动态合集(UID)).get(0)));
        System.out.println("B站最新视频简介:" + 获取最新视频简介(Objects.requireNonNull(获取B站动态合集(UID)).get(0)));
        System.out.println("B站最新视频封面图片链接:" + 获取最新视频封面图片链接(Objects.requireNonNull(获取B站动态合集(UID)).get(0)));
        System.out.println("B站最新视频视频分类:" + 获取最新视频视频分类(Objects.requireNonNull(获取B站动态合集(UID)).get(0)));
        System.out.println("B站最新视频播放量:" + 获取最新视频播放量(Objects.requireNonNull(获取B站动态合集(UID)).get(0)));
        System.out.println("B站最新视频点赞量:" + 获取最新视频点赞量(Objects.requireNonNull(获取B站动态合集(UID)).get(0)));
        System.out.println("B站最新视频投币量:" + 获取最新视频投币量(Objects.requireNonNull(获取B站动态合集(UID)).get(0)));
        System.out.println("B站最新视频链接:" + 获取最新视频链接(Objects.requireNonNull(获取B站动态合集(UID)).get(0)));
        //直播间
        System.out.println("B站直播间链接:" + 获取B站直播间链接(String_HashMap.get(UIDName)));
        System.out.println("B站直播状态:" + 获取B站直播状态(String_HashMap.get(UIDName)));
    }
}