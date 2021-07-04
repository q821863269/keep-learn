package cn.goduck.kl.common.web.util;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.util.Strings;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.lionsoul.ip2region.Util;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

@Slf4j
public class IpUtil {

    private static final String LOCAL_IP = "127.0.0.1";

    /**
     * 获取IP地址
     * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
     * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = null;
        try {
            if (request == null) {
                return "";
            }
            ip = request.getHeader("x-forwarded-for");
            if (checkIp(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (checkIp(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (checkIp(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (checkIp(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (checkIp(ip)) {
                ip = request.getRemoteAddr();
                if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
                    // 根据网卡取本机配置的IP
                    ip = getLocalAddr();
                }
            }
        } catch (Exception e) {
            log.error("IPUtils ERROR, {}", e.getMessage());
        }

        //使用代理，则获取第一个IP地址
        if (StrUtil.isNotBlank(ip)) {
            assert ip != null;
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }

    private static boolean checkIp(String ip) {
        String unknown = "unknown";
        return StrUtil.isEmpty(ip) || ip.length() == 0 || unknown.equalsIgnoreCase(ip);
    }

    /**
     * 获取本机的IP地址
     */
    private static String getLocalAddr() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.error("InetAddress.getLocalHost()-error, {}", e.getMessage());
        }
        return null;
    }

    /**
     * 根据IP获取城市信息
     */
    public static String getCityInfo(String ip) {
        return getCityInfo(ip, DbSearcher.BTREE_ALGORITHM);
    }

    public static String getCityInfo(String ip, Integer algorithm) {
        if (!Util.isIpAddress(ip)) {
            return Strings.EMPTY;
        }
        if (LOCAL_IP.equals(ip)) {
            return "本地访问";
        }
        String dbPath = IpUtil.class.getResource("/ip2region.db").getPath();
        File file = new File(dbPath);
        if (!file.exists()) {
            String tmpdir = System.getProperties().getProperty("java.io.tmpdir");
            dbPath = tmpdir + "/ip2region.db";
            file = new File(dbPath);
            if (!file.exists()) {
                try {
                    FileUtils.copyInputStreamToFile(Objects.requireNonNull(IpUtil.class.getClassLoader().getResourceAsStream("classpath:ip2region.db")), file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        DbSearcher searcher = null;
        try {
            DbConfig config = new DbConfig();
            searcher = new DbSearcher(config, dbPath);
            Method method;
            switch (algorithm) {
                case DbSearcher.BTREE_ALGORITHM:
                    method = searcher.getClass().getMethod("btreeSearch", String.class);
                    break;
                case DbSearcher.BINARY_ALGORITHM:
                    method = searcher.getClass().getMethod("binarySearch", String.class);
                    break;
                case DbSearcher.MEMORY_ALGORITYM:
                default:
                    method = searcher.getClass().getMethod("memorySearch", String.class);
                    break;
            }
            DataBlock dataBlock = (DataBlock) method.invoke(searcher, ip);
            return dataBlock.getRegion();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (searcher != null) {
                try {
                    searcher.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return Strings.EMPTY;
    }

}