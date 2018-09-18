package com.xchanging.ops.utils;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xchanging.ops.model.Config;
import com.xchanging.ops.service.ConfigService;
 
public class RefDataUtil {
	private static Logger logger = LoggerFactory.getLogger(RefDataUtil.class);
	
	private static Map<String,Object> appProps = new Hashtable<String, Object>(); 
	

	 
	public static void readProperties(ConfigService service){
		List<Config> configs = service.findAll(0,10000);
		Iterator<Config> itr = configs.iterator();
		//appProps = new Hashtable<String, Object>();
		while(itr.hasNext()){
			Config config= itr.next();
			//logger.info("Key : "+config.getCode()+" Value :"+config.getValue());
			System.setProperty(config.getCode(), config.getValue());
		}
	}


	public static Map<String, Object> getAppProps() {
		return appProps;
	}


	public static void setAppProps(Map<String, Object> appProps) {
		RefDataUtil.appProps = appProps;
	}
	
	public static void addAppProps(String key,Object obj) {
		if(RefDataUtil.appProps!=null){
			RefDataUtil.appProps.put(key, obj);
		}
		
	}
	
	
	
	
	

}
