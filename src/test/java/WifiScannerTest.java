import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Test;

import javax.rmi.CORBA.Util;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class WifiScannerTest {

    @Test
    public void testParsingWindowsScan() throws IOException {
        List<WifiResult> expectedList = new ArrayList<WifiResult>();
        expectedList.add(new WifiResult("Bijan", "c0:c1:c0:f0:6f:cd", Utils.getRssiFromQuality(16)));
        expectedList.add(new WifiResult("chemed", "f8:35:dd:0a:da:be", Utils.getRssiFromQuality(38)));
        expectedList.add(new WifiResult("Remote_Guest", "00:1a:1e:46:cd:11", Utils.getRssiFromQuality(40)));
        expectedList.add(new WifiResult("telecommuter", "00:1a:1e:46:cd:10", Utils.getRssiFromQuality(40)));
        expectedList.add(new WifiResult("angiebat", "2c:b0:5d:36:e3:b8", Utils.getRssiFromQuality(38)));
        expectedList.add(new WifiResult("HP-Print-9F-Deskjet 2540 series", "58:20:b1:21:63:9f", Utils.getRssiFromQuality(76)));
        expectedList.add(new WifiResult("Wamahahi88", "98:6b:3d:d7:84:e0", Utils.getRssiFromQuality(18)));
        expectedList.add(new WifiResult("18Nascar19", "80:37:73:87:56:36", Utils.getRssiFromQuality(36)));
        expectedList.add(new WifiResult("Kalam", "00:23:69:d4:47:9f", Utils.getRssiFromQuality(36)));
        expectedList.add(new WifiResult("ToscheStation", "80:37:73:ba:f7:d8", Utils.getRssiFromQuality(89)));
        expectedList.add(new WifiResult("ToscheStation_EXT", "a0:63:91:2b:9e:65", Utils.getRssiFromQuality(84)));


        File file = new File(".\\src\\test\\java\\outputScan\\windowsOutput.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String st;
        while ((st = br.readLine()) != null)
            sb.append(st).append("\n");
        List<WifiResult> wifiResultList = Utils.parseWindowsScan(sb.toString().split("\n"));
        Assert.assertEquals(wifiResultList, expectedList);
    }

    @Test
    public void testParsingLinuxScan() throws IOException {
        List<WifiResult> expectedList = new ArrayList<WifiResult>();
        expectedList.add(new WifiResult("visitor", "C4:0A:CB:2C:30:13",-76));
        expectedList.add(new WifiResult("Dukeblue", "C4:0A:CB:2C:30:14",-77));
        expectedList.add(new WifiResult("\\x00", "00:1E:79:D7:A5:04",-74));
        expectedList.add(new WifiResult("\\x00", "C4:0A:CB:B3:8B:B5",-79));
        expectedList.add(new WifiResult("\\x00", "C4:0A:CB:2C:27:15",-70));
        expectedList.add(new WifiResult("DUKE", "C4:0A:CB:B3:8B:B0",-81));
        expectedList.add(new WifiResult("\\x00", "C4:0A:CB:B3:D6:05",-46));
        expectedList.add(new WifiResult("Dukeblue", "D8:24:BD:E9:E8:14",-81));
        expectedList.add(new WifiResult("eduroam", "C4:0A:CB:B3:8B:B1",-79));
        expectedList.add(new WifiResult("Dukeblue", "C4:0A:CB:24:5D:34",-80));
        expectedList.add(new WifiResult("\\x00", "D8:24:BD:E9:E8:1D",-97));
        expectedList.add(new WifiResult("\\x00", "D8:24:BD:E9:E8:12",-82));
        expectedList.add(new WifiResult("eduroam", "D8:24:BD:E9:E8:1E",-93));

        File file = new File(".\\src\\test\\java\\outputScan\\iwlistUbuntuOutput.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String st;
        while ((st = br.readLine()) != null)
            sb.append(st).append("\n");
        List<WifiResult> wifiResultList = Utils.parseLinuxScan(sb.toString().split("\n"));

        Assert.assertEquals(wifiResultList,expectedList);

    }

    @Test
    public void testParsingOSXScan() throws IOException {
        List<WifiResult> expectedList = new ArrayList<WifiResult>();
        expectedList.add(new WifiResult("xfinitywifi", "22:86:8c:d5:30:d8", -82));
        expectedList.add(new WifiResult("XFSETUP-30DA", "10:86:8c:d5:30:d8", -81));
        expectedList.add(new WifiResult("RGNWG", "00:7f:28:8b:0c:1d", -74));
        expectedList.add(new WifiResult("XFSETUP-30DA_EXT", "74:44:01:35:46:34", -75));
        expectedList.add(new WifiResult("traviata", "20:e5:2a:16:79:d4", -73));
        expectedList.add(new WifiResult("xfinitywifi", "4e:7a:8a:1d:7e:cc", -70));
        expectedList.add(new WifiResult("xfinitywifi", "e6:89:2c:1a:02:e0", -91));
        expectedList.add(new WifiResult("HOME-02E2", "e8:89:2c:1a:02:e0", -92));
        expectedList.add(new WifiResult("threeR", "3c:7a:8a:1d:7e:cc", -70));
        expectedList.add(new WifiResult("NETGEAR13", "50:6a:03:93:89:e3", -91));
        expectedList.add(new WifiResult("Ewifi", "08:86:3b:6d:bc:16", -53));
        expectedList.add(new WifiResult("namaste", "c0:ff:d4:e6:dd:2b", -67));
        expectedList.add(new WifiResult("ForzaRoma 5GHz", "60:a4:4c:29:8b:44", -88));
        expectedList.add(new WifiResult("Ewifi5", "08:86:3b:6d:bc:18", -66));

        File file = new File(".\\src\\test\\java\\outputScan\\osxOutput.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String st;
        while ((st = br.readLine()) != null)
            sb.append(st).append("\n");
        List<WifiResult> wifiResultList = Utils.parseOSXScan(sb.toString().split("\n"));
        Assert.assertEquals(wifiResultList, expectedList);

    }
}
