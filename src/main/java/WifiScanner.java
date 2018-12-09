import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that allows the scan of available wifi networks in the proximity.
 * Returns a list of of WifiResult objects.
 * The list contains the SSID, Bssid and Rssi for each available Access Point
 */
public class WifiScanner {
    private String OS;
    private final String PROCESS_WINDOWS = "netsh wlan show networks mode=Bssid";
    private final String PROCESS_LINUX = "sudo iwlist %s scan";
    private final String PROCESS_OSX = "airport -s";
    private String DEFAULT_INTERFACE = "wlan0";

    /**
     * @throws Exception
     *  when OS is not one of the following
     *      * Windows
     *      * Linux
     *      * OSX
     */
    public WifiScanner() throws Exception {
        OS = Utils.getOS();
    }

    /**
     * @param wifiInterface
     * @throws Exception
     *  when OS is not one of the following
     *      * Windows
     *      * Linux
     *      * OSX
     *
     * WifiInterface- allows the user to change the desired interface on Linux
     * Default_Interface="wlan0"
     */
    public WifiScanner(String wifiInterface) throws Exception {
        OS = Utils.getOS();
        this.DEFAULT_INTERFACE = wifiInterface;
    }

    /**
     * @return List<WifiResult>
     * @throws Exception
     * Runs the Wifi scan
     */
    public List<WifiResult> run() throws Exception {

        Runtime rt = Runtime.getRuntime();
        List<WifiResult> wifiList = new ArrayList<WifiResult>();
        StringBuilder scan_response = new StringBuilder();
        Process pr = null;

        if (OS.equals(Utils.WINDOWS))
            pr = rt.exec(PROCESS_WINDOWS);
        else if (OS.equals(Utils.LINUX))
            pr = rt.exec(String.format(PROCESS_LINUX, DEFAULT_INTERFACE));
        else if (OS.equals(Utils.OSX))
            pr = rt.exec(PROCESS_OSX);


        BufferedReader br = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line = "";
        while ((line = br.readLine()) != null) {
            scan_response.append(line).append("\n");
        }

        if (OS.equals(Utils.WINDOWS))
            wifiList = Utils.parseWindowsScan(scan_response.toString().split("\n"));
        else if (OS.equals(Utils.LINUX))
            wifiList = Utils.parseLinuxScan(scan_response.toString().split("\n"));
        else if (OS.equals(Utils.OSX))
            wifiList = Utils.parseOSXScan(scan_response.toString().split("\n"));
        else
            throw new Exception("OS not yet supported, currently supporting only Windows, Linux and MAC osx");

        return wifiList;


    }


}