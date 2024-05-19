package com.storm.score.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

/**
 * packageName    : com.storm.score.utils
 * fileName       : NetworkUtils
 * author         : ojy
 * date           : 2024/05/19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/05/19        ojy       최초 생성
 */
public class NetworkUtils {

    public static String getRealIpAddress() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface networkInterface : Collections.list(networkInterfaces)) {
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                for (InetAddress inetAddress : Collections.list(inetAddresses)) {
                    if (!inetAddress.isLoopbackAddress() && inetAddress.isSiteLocalAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "Unknown Host";
    }
}
