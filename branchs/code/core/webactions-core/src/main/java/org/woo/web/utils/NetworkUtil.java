/**
 *
 */
package org.woo.web.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author Administrator
 * @method
 * @date 下午6:10:52
 */
public class NetworkUtil {
    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(NetworkUtil.class);
    private static String mobileGateWayHeaders[] = new String[]{"ZXWAP",
            "chinamobile.com", "monternet.com", "infoX",
            "XMS 724Solutions HTG", "Bytemobile",};
    private static String[] pcHeaders = new String[]{"Windows 98",
            "Windows ME", "Windows 2000", "Windows XP", "Windows NT", "Ubuntu"};
    private static String[] mobileUserAgents = new String[]{"Nokia",
            "SAMSUNG", "MIDP-2", "CLDC1.1", "SymbianOS", "MAUI",
            "UNTRUSTED/1.0", "Windows CE", "iPhone", "iPad", "Android",
            "BlackBerry", "UCWEB", "ucweb", "BREW", "J2ME", "YULONG", "YuLong",
            "COOLPAD", "TIANYU", "TY-", "K-Touch", "Haier", "DOPOD", "Lenovo",
            "LENOVO", "HUAQIN", "AIGO-", "CTC/1.0", "CTC/2.0", "CMCC",
            "DAXIAN", "MOT-", "SonyEricsson", "GIONEE", "HTC", "ZTE", "HUAWEI",
            "webOS", "GoBrowser", "IEMobile", "WAP2.0"};

    /**
     * @param request
     * @return
     * @throws IOException
     */
    public final static String getIpAddress(HttpServletRequest request)
            throws IOException {
        String ip = request.getHeader("X-Forwarded-For");
        if (logger.isInfoEnabled()) {
            logger.info("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip="
                    + ip);
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0
                    || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
                if (logger.isInfoEnabled()) {
                    logger.info("getIpAddress(HttpServletRequest) - Proxy-Client-IP - String ip="
                            + ip);
                }
            }
            if (ip == null || ip.length() == 0
                    || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
                if (logger.isInfoEnabled()) {
                    logger.info("getIpAddress(HttpServletRequest) - WL-Proxy-Client-IP - String ip="
                            + ip);
                }
            }
            if (ip == null || ip.length() == 0
                    || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
                if (logger.isInfoEnabled()) {
                    logger.info("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ip="
                            + ip);
                }
            }
            if (ip == null || ip.length() == 0
                    || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                if (logger.isInfoEnabled()) {
                    logger.info("getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ip="
                            + ip);
                }
            }
            if (ip == null || ip.length() == 0
                    || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
                if (logger.isInfoEnabled()) {
                    logger.info("getIpAddress(HttpServletRequest) - getRemoteAddr - String ip="
                            + ip);
                }
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++) {
                String strIp = (String) ips[index];
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }

    public static List<String> getLocalIPList() {
        List<String> ipList = new ArrayList<String>();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface
                    .getNetworkInterfaces();
            NetworkInterface networkInterface;
            Enumeration<InetAddress> inetAddresses;
            InetAddress inetAddress;
            String ip;
            while (networkInterfaces.hasMoreElements()) {
                networkInterface = networkInterfaces.nextElement();
                inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    inetAddress = inetAddresses.nextElement();
                    if (inetAddress != null
                            && inetAddress instanceof Inet4Address) { // IPV4
                        ip = inetAddress.getHostAddress();
                        ipList.add(ip);
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return ipList;
    }

    public static boolean isMobileDevice(HttpServletRequest request) {
        boolean b = false;
        boolean pcFlag = false;
        boolean mobileFlag = false;
        String via = request.getHeader("Via");
        String userAgent = request.getHeader("user-agent");
        for (int i = 0; via != null && !via.trim().equals("")
                && i < mobileGateWayHeaders.length; i++) {
            if (via.contains(mobileGateWayHeaders[i])) {
                mobileFlag = true;
                break;
            }
        }
        for (int i = 0; !mobileFlag && userAgent != null
                && !userAgent.trim().equals("") && i < mobileUserAgents.length; i++) {
            if (userAgent.contains(mobileUserAgents[i])) {
                mobileFlag = true;
                break;
            }
        }
        for (int i = 0; userAgent != null && !userAgent.trim().equals("")
                && i < pcHeaders.length; i++) {
            if (userAgent.contains(pcHeaders[i])) {
                pcFlag = true;
                break;
            }
        }
        if (mobileFlag == true && pcFlag == false) {
            b = true;
        }
        return b;// false pc true shouji
    }

    /**
     * @param agent
     * @return
     */
    public static String getBrowserName(String agent) {
        if (agent.indexOf("msie 7") > 0) {
            return "IE7";
        } else if (agent.indexOf("msie 8") > 0) {
            return "IE8";
        } else if (agent.indexOf("msie 9") > 0) {
            return "IE9";
        } else if (agent.indexOf("msie 10") > 0) {
            return "IE10";
        } else if (agent.indexOf("msie") > 0) {
            return "IE";
        } else if (agent.indexOf("gecko") > 0 && agent.indexOf("rv:11") > 0) {
            return "IE11";
        } else if (agent.indexOf("opera") > 0) {
            return "Opera";
        } else if (agent.indexOf("chrome") > 0) {
            return "Chrome";
        } else if (agent.indexOf("firefox") > 0) {
            return "Firefox";
        } else if (agent.indexOf("ucbrowser") > 0) {
            return "UCBrowser";
        } else if (agent.indexOf("applewebkit") > 0) {
            return "Safari";
        } else if (agent.indexOf("webkit") > 0) {
            return "webkit";
        } else {
            return "Others";
        }
    }

    public static void main(String[] args) {
        List<String> ipList = NetworkUtil.getLocalIPList();
        for (String ip : ipList) {
            System.out.println(ip);
        }
    }
}
