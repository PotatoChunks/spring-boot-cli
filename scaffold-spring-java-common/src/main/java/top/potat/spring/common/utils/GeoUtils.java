package top.potat.spring.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 地理位置工具类
 */
public class GeoUtils {
    private static final Logger logger = LoggerFactory.getLogger(GeoUtils.class);
    private static final double EARTH_RADIUS = 6371; // 地球半径，单位千米
    private static final String BAIDU_MAP_KEY = "KQNBZ-JPDLB-UEDUZ-N6RAU-NCRX5-OMBWZ";

    /**
     * 计算两个经纬度点之间的距离（Haversine公式）
     */
    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c; // 返回千米
    }


}
