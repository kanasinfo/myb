package com.myb.portal.util;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class NetworkKit {
	private static String byteToHex(byte b) { 
	    String s = "00" + Integer.toHexString(b); 
	    return s.substring(s.length() - 2); 
	}
	public static List<NetInterface> getNetInterfaces(){
		List<NetInterface> nis=new ArrayList<NetInterface>();
		Enumeration<NetworkInterface> el=null;
		try {
			el = NetworkInterface.getNetworkInterfaces();
		} catch (Exception e) {
			return nis;
		}
		NetworkInterface nwi;
		NetInterface ni;
	    while (el.hasMoreElements()) {
	    	nwi=el.nextElement();
	    	try {
				if(nwi.isVirtual()||nwi.isPointToPoint()||nwi.isLoopback())continue;
			} catch (SocketException e1) {
				continue;
			}
	        byte[] mac=null;
			try {
				mac = nwi.getHardwareAddress();
			} catch (Exception e) {
				return nis;
			} catch (Throwable t) {
				return nis;
			}
	        if (mac == null || mac.length == 0) continue; 

	        StringBuffer builder = new StringBuffer(); 
	        for (byte b : mac) { 
	        	builder.append(byteToHex(b)); 
	        	builder.append("-");
	        }
	        builder.deleteCharAt(builder.length() - 1);
	        ni = new NetInterface();
	        ni.setName(nwi.getName());
	        ni.setDisplayName(nwi.getDisplayName());
	        ni.setMacAddress(builder.toString());
	        try {
				ni.setUp(nwi.isUp());
			} catch (SocketException e) {
				ni.setUp(false);
			}
	        nis.add(ni);
	    }
	    return nis;
	}
	public static List<String> getMacAddresses(){
		List<NetInterface> nis = getNetInterfaces();
		List<String> macs=new ArrayList<String>();
		for(NetInterface ni:nis)macs.add(ni.getMacAddress());
	    return macs;
	}

	public static class NetInterface {
		private String name;
		private String displayName;
		private String macAddress;
		private boolean isUp;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDisplayName() {
			return displayName;
		}
		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}
		public String getMacAddress() {
			return macAddress;
		}
		public void setMacAddress(String macAddress) {
			this.macAddress = macAddress;
		}
		public boolean isUp() {
			return isUp;
		}
		public void setUp(boolean isUp) {
			this.isUp = isUp;
		}
	}
}
