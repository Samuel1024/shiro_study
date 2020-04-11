package com.offcn.shiro;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

import javax.sound.midi.Soundbank;

public class MD5Test {

    public static void main(String[] args) {
        String password = "123456";

        //使用md5普通加密
        Md5Hash md5Hash = new Md5Hash(password);
        System.out.println(md5Hash.toHex());
        //使用md5 加盐加密
        Md5Hash md5Hash1 = new Md5Hash(password, "samuel");
        System.out.println(md5Hash1);
        //加盐迭代加密
        Md5Hash md5Hash2 = new Md5Hash(password, "samuel", 3);
        System.out.println(md5Hash2);

        SimpleHash simpleHash = new SimpleHash("MD5", password, "samuel", 3);
        System.out.println(simpleHash);


    }
}
