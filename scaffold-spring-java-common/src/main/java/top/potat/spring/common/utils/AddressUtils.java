package top.potat.spring.common.utils;


import org.springframework.http.server.reactive.ServerHttpRequest;

import javax.servlet.http.HttpServletRequest;
import java.net.*;
import java.util.Enumeration;

/** ip相关的工具类
 * 获取ip地址
 * */
public class AddressUtils {

    private static final String LOCAL_IP = "127.0.0.1";

    private static final String UNKNOWN = "unknown";
    private static final String SEPARATOR = ",";
    private static final String HEADER_X_FORWARDED_FOR = "x-forwarded-for";
    private static final String HEADER_X_REAL_IP = "x-real-ip";
    private static final String HEADER_PROXY_CLIENT_IP = "Proxy-Client-IP";
    private static final String HEADER_WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
    /**
     * 获取真实客户端IP
     * @param serverHttpRequest
     * @return
     */
    public static String getRealIpAddress(ServerHttpRequest serverHttpRequest) {
        String ipAddress;
        try {
            // 1.根据常见的代理服务器转发的请求ip存放协议，从请求头获取原始请求ip。值类似于203.98.182.163, 203.98.182.163
            ipAddress = serverHttpRequest.getHeaders().getFirst(HEADER_X_REAL_IP);
            if (StringUtils.isEmpty(ipAddress) || UNKNOWN.equalsIgnoreCase(ipAddress)){
                ipAddress = serverHttpRequest.getHeaders().getFirst(HEADER_X_FORWARDED_FOR);
            }
            if (StringUtils.isEmpty(ipAddress) || UNKNOWN.equalsIgnoreCase(ipAddress)) {
                ipAddress = serverHttpRequest.getHeaders().getFirst(HEADER_PROXY_CLIENT_IP);
            }
            if (StringUtils.isEmpty(ipAddress) || UNKNOWN.equalsIgnoreCase(ipAddress)) {
                ipAddress = serverHttpRequest.getHeaders().getFirst(HEADER_WL_PROXY_CLIENT_IP);
            }
            // 2.如果没有转发的ip，则取当前通信的请求端的ip
            if (StringUtils.isEmpty(ipAddress) || UNKNOWN.equalsIgnoreCase(ipAddress)) {
                InetSocketAddress inetSocketAddress = serverHttpRequest.getRemoteAddress();
                if(inetSocketAddress != null) {
                    ipAddress = inetSocketAddress.getAddress().getHostAddress();
                }
                // 如果是127.0.0.1，则取本地真实ip
//                if (LOCAL_IP.equals(ipAddress)) {
//                    InetAddress localAddress = StringUtil.getLocalHostLANAddress();
//                    if(localAddress.getHostAddress() != null) {
//                        ipAddress = localAddress.getHostAddress();
//                    }
//                }
            }
            // 对于通过多个代理的情况，第⼀个IP为客户端真实IP,多个IP按照','分割
            // "***.***.***.***"
            if (ipAddress != null) {
                ipAddress = ipAddress.split(SEPARATOR)[0].trim();
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        return ipAddress == null ? LOCAL_IP : ("0:0:0:0:0:0:0:1".equals(ipAddress) ? LOCAL_IP : ipAddress);
    }

    /** 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址*/
    public static String getIpAddress(HttpServletRequest request){
        if (request == null) {
            return null;
        }
        String ip = request.getHeader("ip");
        if (!checkIP(ip)) {
            ip = request.getHeader("IP");
        }
        if (!checkIP(ip)) {
            ip = request.getHeader("x-real-ip");
        }
        if (!checkIP(ip)) {
            ip = request.getHeader("remote-host");
        }
        if (!checkIP(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (!checkIP(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (!checkIP(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (!checkIP(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (!checkIP(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? LOCAL_IP : ip;
    }

    private static boolean checkIP(String ip) {
        if (ip == null || ip.length() == 0 || "unkown".equalsIgnoreCase(ip)
                || ip.split(".").length != 4) {
            return false;
        }
        return true;
    }

    /**
     * 将IPv4地址转换成字节
     *IPv4地址
     * @param text
     * @return byte 字节
     */
    public static byte[] textToNumericFormatV4(String text) {
        if (text.length() == 0) {
            return null;
        }

        byte[] bytes = new byte[4];
        String[] elements = text.split("\\.", -1);
        try {
            long l;
            int i;
            switch (elements.length) {
                case 1:
                    l = Long.parseLong(elements[0]);
                    if ((l < 0L) || (l > 4294967295L))
                        return null;
                    bytes[0] = (byte) (int) (l >> 24 & 0xFF);
                    bytes[1] = (byte) (int) ((l & 0xFFFFFF) >> 16 & 0xFF);
                    bytes[2] = (byte) (int) ((l & 0xFFFF) >> 8 & 0xFF);
                    bytes[3] = (byte) (int) (l & 0xFF);
                    break;
                case 2:
                    l = Integer.parseInt(elements[0]);
                    if ((l < 0L) || (l > 255L))
                        return null;
                    bytes[0] = (byte) (int) (l & 0xFF);
                    l = Integer.parseInt(elements[1]);
                    if ((l < 0L) || (l > 16777215L))
                        return null;
                    bytes[1] = (byte) (int) (l >> 16 & 0xFF);
                    bytes[2] = (byte) (int) ((l & 0xFFFF) >> 8 & 0xFF);
                    bytes[3] = (byte) (int) (l & 0xFF);
                    break;
                case 3:
                    for (i = 0; i < 2; ++i) {
                        l = Integer.parseInt(elements[i]);
                        if ((l < 0L) || (l > 255L))
                            return null;
                        bytes[i] = (byte) (int) (l & 0xFF);
                    }
                    l = Integer.parseInt(elements[2]);
                    if ((l < 0L) || (l > 65535L))
                        return null;
                    bytes[2] = (byte) (int) (l >> 8 & 0xFF);
                    bytes[3] = (byte) (int) (l & 0xFF);
                    break;
                case 4:
                    for (i = 0; i < 4; ++i) {
                        l = Integer.parseInt(elements[i]);
                        if ((l < 0L) || (l > 255L))
                            return null;
                        bytes[i] = (byte) (int) (l & 0xFF);
                    }
                    break;
                default:
                    return null;
            }
        } catch (NumberFormatException e) {
            //log.error("数字格式化异常",e);
            return null;
        }
        return bytes;
    }

    private static boolean internalIp(byte[] addr) {
        final byte b0 = addr[0];
        final byte b1 = addr[1];
        // 10.x.x.x/8
        final byte SECTION_1 = 0x0A;
        // 172.16.x.x/12
        final byte SECTION_2 = (byte) 0xAC;
        final byte SECTION_3 = (byte) 0x10;
        final byte SECTION_4 = (byte) 0x1F;
        // 192.168.x.x/16
        final byte SECTION_5 = (byte) 0xC0;
        final byte SECTION_6 = (byte) 0xA8;
        boolean flag = false;
        switch (b0) {
            case SECTION_1:
                flag = true;
                break;
            case SECTION_2:
                if (b1 >= SECTION_3 && b1 <= SECTION_4) {
                    flag = true;
                }
                break;
            case SECTION_5:
                if (b1 == SECTION_6) {
                    flag = true;
                }
                break;
            default:
                break;
        }
        return flag;
    }

    public static boolean internalIp(String ip) {
        boolean res = false;
        byte[] addr = textToNumericFormatV4(ip);
        if (addr != null && ip != null) {
            res = internalIp(addr) || LOCAL_IP.equals(ip);
        }
        return res;
    }

    public static String getLocalIP() {
        String ip = "";
        if (System.getProperty("os.name").toLowerCase().startsWith("windows")) {
            InetAddress addr;
            try {
                addr = InetAddress.getLocalHost();
                ip = addr.getHostAddress();
            } catch (UnknownHostException e) {
                //log.error("获取失败",e);
            }
            return ip;
        } else {
            try {
                Enumeration<?> e1 = (Enumeration<?>) NetworkInterface
                        .getNetworkInterfaces();
                while (e1.hasMoreElements()) {
                    NetworkInterface ni = (NetworkInterface) e1.nextElement();
                    if (!ni.getName().equals("eth0")) {
                        continue;
                    } else {
                        Enumeration<?> e2 = ni.getInetAddresses();
                        while (e2.hasMoreElements()) {
                            InetAddress ia = (InetAddress) e2.nextElement();
                            if (ia instanceof Inet6Address)
                                continue;
                            ip = ia.getHostAddress();
                            return ip;
                        }
                        break;
                    }
                }
            } catch (SocketException e) {
                //log.error("获取失败",e);
            }
        }
        return "";
    }
}
