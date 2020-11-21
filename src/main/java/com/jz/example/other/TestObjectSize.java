package com.jz.example.other;

import com.carrotsearch.sizeof.RamUsageEstimator;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Description： 获取对象大小
 * Author: hujingzheng
 * Date: 2020/11/21 16:45
 */
public class TestObjectSize {

    public static void main(String[] args) {
        System.out.println(RamUsageEstimator.sizeOf(1));
        System.out.println(RamUsageEstimator.sizeOf(true));
        System.out.println(RamUsageEstimator.sizeOf(1L));
        System.out.println(RamUsageEstimator.sizeOf(1f));
        System.out.println(RamUsageEstimator.sizeOf(1.0));
        System.out.println(RamUsageEstimator.sizeOf(new int[]{}));
        System.out.println(RamUsageEstimator.sizeOf(new ArrayList()));
        System.out.println(RamUsageEstimator.sizeOf(new HashMap()));
    }


}
