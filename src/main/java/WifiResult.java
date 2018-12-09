import java.util.Objects;

public class WifiResult {

    private String SSID;
    private String BSSID;
    private int rssi;

    public WifiResult() {
    }

    public WifiResult(String SSID, String BSSID, int rssi) {
        this.SSID = SSID;
        this.BSSID = BSSID;
        this.rssi = rssi;
    }

    public String getSSID() {
        return SSID;
    }

    public void setSSID(String SSID) {
        this.SSID = SSID;
    }

    public String getBSSID() {
        return BSSID;
    }

    public void setBSSID(String BSSID) {
        this.BSSID = BSSID;
    }

    public int getRssi() {
        return rssi;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }

    @Override
    public String toString() {
        return "WifiResult{" +
                "SSID='" + SSID + '\'' +
                ", BSSID='" + BSSID + '\'' +
                ", rssi=" + rssi +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WifiResult that = (WifiResult) o;
        return rssi == that.rssi &&
                Objects.equals(SSID, that.SSID) &&
                Objects.equals(BSSID, that.BSSID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(SSID, BSSID, rssi);
    }
}
