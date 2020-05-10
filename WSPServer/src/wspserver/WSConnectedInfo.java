/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wspserver;

import java.net.*; 
import java.io.*; 
import java.text.*; 
import java.util.*;

public class WSConnectedInfo {
    
    private String wsid;
    private Socket wssocket;

    public WSConnectedInfo(String wsid, Socket wssocket) {
        this.wsid = wsid;
        this.wssocket = wssocket;
    }

    public String getWsid() {
        return wsid;
    }

    public void setWsid(String wsid) {
        this.wsid = wsid;
    }

    public Socket getWssocket() {
        return wssocket;
    }

    public void setWssocket(Socket wssocket) {
        this.wssocket = wssocket;
    }
}
