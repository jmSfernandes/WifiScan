# WifiScan
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.jmSfernandes/WifiScan/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.jmSfernandes/WifiScan)

Simple library that allows java users to scan the available Wifi networks. 
Available for Windows, Linux (iwlist must be installed) and MAC osx (airport). 

```java
 WifiScanner wifiScanner=new WifiScanner();
 List<WifiResult> wifiList  = wifiScanner.run();
```

For Linux users, if you want to change the interface (default=wlan0).
Just use: 
```java
 WifiScanner wifiScanner=new WifiScanner("interface name");
 List<WifiResult> wifiList  = wifiScanner.run();
```
import through maven and graddle:
```xml
<dependency>
    <groupId>com.github.jmSfernandes</groupId>
    <artifactId>WifiScan</artifactId>
    <version>1.0.1</version>
</dependency>
```
```json
compile 'com.github.jmSfernandes:WifiScan:1.0.1'
```

Copyright [2018]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
