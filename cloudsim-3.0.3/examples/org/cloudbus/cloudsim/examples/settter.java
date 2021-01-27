package org.cloudbus.cloudsim.examples;
import org.cloudbus.cloudsim.DatacenterBroker;
import java.util.HashMap;
import java.util.Map;

public class settter{
	
	 public static Map<Integer,String > data = new HashMap <Integer,String>();
	//public static Map<Integer,String > data;
     public void setValue(Map<Integer,String> map)
      {
        this.data = map;
      }
     public Map<Integer,String> getValue()
      {
       return this.data;
      }
     
}