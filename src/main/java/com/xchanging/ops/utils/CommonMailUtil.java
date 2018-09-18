package com.xchanging.ops.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;



public class CommonMailUtil extends SpringBeanAutowiringSupport
{
	
	private static Logger logger = LoggerFactory.getLogger(CommonMailUtil.class);
    @Autowired
	private static MailService mailservice;

   
   
    public static String sendMail(Map<String,String> mailProps,Map<String,String> transData) throws Exception{
    	String message = "";
    	logger.info("Enter sendMail() ");
    	mailservice = new MailService();
		try{
    		String mailfrom= CommonUtils.getCurrentUser().getEmail();
    		String mailInCc= StringUtils.defaultString(mailProps.get("mailInCc"))+","+CommonUtils.getCurrentUser().getEmail();
			String mailInTo=StringUtils.defaultString(mailProps.get("mailInTo"));
			logger.info("TO address is      :"+mailInTo+" CC :"+mailInCc);
			String emailTo[]=mailInTo.split(",");
			String emailCc[] = mailInCc.split(",");
			
			mailservice.sendMail(mailfrom, cleanEmailArray(emailTo),cleanEmailArray(emailCc), mailProps.get("subject"), generateMailContentFromtemplate(transData));
			}catch(Exception e){
				logger.error("Error occured while sending mail : ", e);
				throw e;
			}
    	
		logger.info("sendMail() done");
		return message;
	 }
    
    
    
    public static String sendMailWithoutTemplate(Map<String,String> mailProps) throws Exception{
    	String message = "";
    	logger.info("Enter sendMail() ");
    	mailservice = new MailService();
		try{
			String mailfrom= CommonUtils.getCurrentUser().getEmail();
			String mailInBCC= StringUtils.defaultString(mailProps.get("mailInBCC"));
			String mailInTo=StringUtils.defaultString(mailProps.get("mailInTo"))+","+CommonUtils.getCurrentUser().getEmail();;
			logger.info("TO address is      :"+mailInTo);
			String emailTo[]=mailInTo.split(",");
			String emailBcc[] = mailInBCC.split(",");
			mailservice.sendMail(mailfrom, cleanEmailArray(emailTo),cleanEmailArray(emailBcc), mailProps.get("subject"), mailProps.get("content"));
				
			}catch(Exception e){
				logger.error("Error while sending mail "+e);
				 message = "Exception occured while sending mail";
			}
    	
		logger.info("sendMail() done");
		return message;
	 }
    
    
    
    
    public static String sendMailWithEmailTemplate(Map<String,String> mailProps,String emailTemplate){
    	String message = "";
    	logger.info("Enter sendMail() ");
    	mailservice = new MailService();
		try{
    		String mailfrom= CommonUtils.getCurrentUser().getEmail();
			String mailInCC= StringUtils.defaultString(mailProps.get("mailInCC"))+","+CommonUtils.getCurrentUser().getEmail();
			String mailInTo=StringUtils.defaultString(mailProps.get("mailInTo"));
			logger.info("TO address is      :"+mailInTo);
			String emailTo[]=mailInTo.split(",");
			String emailCc[] = mailInCC.split(",");
			
			mailservice.sendMail(mailfrom, cleanEmailArray(emailTo),cleanEmailArray(emailCc), mailProps.get("subject"), emailTemplate);
				
			}catch(Exception e){
				logger.error("Error while sending mail "+e);
				 message = "Exception occured while sending mail";
			}
    	
		logger.info("sendMail() done");
		return message;
    }
    public static String sendMailWithAttach(Map<String,String> mailProps,String content, String attachement,String fileName){
    	String message = "";
    	logger.info("Enter sendMail() ");
    	mailservice = new MailService();
		try{
    		String mailfrom= CommonUtils.getCurrentUser().getEmail();
			String mailInBCC= StringUtils.defaultString(mailProps.get("mailInBCC"));
			String mailInTo=StringUtils.defaultString(mailProps.get("mailInTo"))+","+CommonUtils.getCurrentUser().getEmail();;
			String emailTo[]=mailInTo.split(",");
			String emailBcc[] = mailInBCC.split(",");
			
			if(attachement!=null){
			mailservice.sendMail(mailfrom, cleanEmailArray(emailTo), cleanEmailArray(emailBcc), mailProps.get("subject"), content,  attachement, fileName);
			}
			else {
				mailservice.sendMail(mailfrom, emailTo,emailBcc, mailProps.get("subject"), content);
			}
		}catch(Exception e){
			logger.error("Error while sending mail "+e);
			 message = "Exception occured while sending mail";
			}
    	
		logger.info("sendMail() done");
		return message;
	 }
	
   
	  public static String  generateMailContent(String mailTemplate, Map infomap){
		  
	    	logger.info("getcreateGRMailContent(Map infomap)");
	    	String template =System.getProperty(mailTemplate+"_content");
	    	
	    	if(StringUtils.isEmpty(template)){
	    		logger.info("mailTemplate  in generateMailContent method  "+mailTemplate);
	    		template =CommonUtils.readFile(new File(mailTemplate));
	    		System.setProperty(mailTemplate+"_content",template);
	    	}
	    	logger.info("getcreateGRMailContent(Map infomap) done");
	    	return  fillUpMailTemplate(template,infomap);
	  }
	  
	  public static String  generateMailContentFromtemplate(Map rfcMap){
		  
	    	logger.info("getcreateGRMailContent(Map infomap)");
	    	String template =System.getProperty(rfcMap.get("{{mail_template}}").toString());
	    	
	    	if(StringUtils.isNotEmpty(template)){
	    		logger.info("template location :"+template);
	    		template =CommonUtils.readFile(new File(template));
	    	}
	    	
	    	logger.info("getcreateGRMailContent(Map infomap) done");
	    	return  fillUpMailTemplate(template,rfcMap);
	  }
	  
	 
	  public static String fillUpMailTemplate(String content, Map<String,String> infomap){
			logger.info("fillUpMailTemplate()");
			Iterator<String> entries = infomap.keySet().iterator();
	          while(entries.hasNext()){
	        	  String tmp= entries.next();
	        	  if(content.contains(tmp)){
	        		  content = content.replace(tmp,StringUtils.defaultString(infomap.get(StringUtils.defaultString(tmp))));
	        	  }
	          }
	          logger.info("fillUpMailTemplate() done");
	          return content;
		}
	  
	  

	  private static String[] cleanEmailArray(String[] ids){
		  List<String> idsList= new ArrayList<String>();

		  for(int i=0; i<ids.length; i++){
			if(!StringUtils.isBlank(ids[i])){
				idsList.add(ids[i]);
			}
		  }
		  String[] finalList = new String[idsList.size()];
		  
		  for(int i=0; i<finalList.length; i++){
			  finalList[i]=idsList.get(i);
		  }
		  return finalList;
		  
	  }
	  
	  
}
