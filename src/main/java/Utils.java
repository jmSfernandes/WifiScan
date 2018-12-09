import java.util.ArrayList;
import java.util.List;

class Utils {
    static final String WINDOWS="WINDOWS";
    static final String LINUX="LINUX";
    static final String OSX="OSX";

    private static final int MAX_DB = -50;
    private static final int Min_DB = -100;

    static String getOS() throws Exception {
        String os_name=System.getProperty("os.name").toUpperCase();
        if(os_name.contains(WINDOWS))
            return WINDOWS;
        else if(os_name.contains(OSX))
            return OSX;
        else if(os_name.contains(LINUX))
            return LINUX;
        else
            throw new Exception("OS not yet supported, currently supporting only Windows, Linux and MAC osx");
    }


    static int getRssiFromQuality(int quality) {
        return (quality / 2) + Min_DB;
    }

    static List<WifiResult> parseLinuxScan(String[] scanLines) {
        WifiResult wr = new WifiResult();
        List<WifiResult> wifiList = new ArrayList<WifiResult>();

        for (String line : scanLines) {
            if (line.toLowerCase().contains("essid:")) {
                wr.setSSID(line.split("ESSID:")[1].replace("\"",""));
                wifiList.add(wr);
                wr = new WifiResult();
            } else if (line.toLowerCase().contains("address:")) {
                wr.setBSSID(line.split(": ")[1]);
            } else if (line.toLowerCase().contains("signal level=")) {
                String str_rssi = line.split(" level=")[1].split("dBm")[0].trim();
                int rssi = (int) Integer.parseInt(str_rssi);
                wr.setRssi(rssi);
            }
        }

        return wifiList;
    }

    static List<WifiResult> parseOSXScan(String[] scanLines) {

        List<WifiResult> wifiList = new ArrayList<WifiResult>();
        int index_ssid = 0;
        int index_bssid = 0;
        int index_rssi = 0;
        for (int i = 0; i < scanLines.length; i++) {
            String line = scanLines[i];
            if (i == 0) {
                index_ssid = line.indexOf("SSID")+4;
                index_rssi = line.indexOf("RSSI");
                index_bssid = line.indexOf("BSSID");
            } else {
                String ssid = line.substring(0, index_ssid).trim();
                String bssid = line.substring(index_bssid, index_bssid+17).trim();
                int rssi = Integer.parseInt(line.substring(index_rssi).split(" ")[0]);
                wifiList.add(new WifiResult(ssid, bssid, rssi));
            }
        }

        return wifiList;
    }

    static List<WifiResult> parseWindowsScan(String[] scanLines) {
        WifiResult wr = new WifiResult();
        List<WifiResult> wifiList = new ArrayList<WifiResult>();
        String current_SSID = "";
        for (String line : scanLines) {

            if(line.toLowerCase().contains("bssid")) {
                wr.setBSSID(line.split(" : ")[1]);
            }else if (line.toLowerCase().contains("ssid")) {
                wr.setSSID(line.split(" : ")[1]);
                current_SSID = wr.getSSID();
            } else if (line.toLowerCase().contains("signal")) {
                String str_rssi = line.split(" : ")[1].split("%")[0].trim();
                int rssi = (int) Utils.getRssiFromQuality(Integer.parseInt(str_rssi));
                wr.setRssi(rssi);
                wifiList.add(wr);
                wr = new WifiResult();
                wr.setSSID(current_SSID);
            }

        }
        return wifiList;
    }
}
