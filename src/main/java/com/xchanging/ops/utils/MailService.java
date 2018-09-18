package com.xchanging.ops.utils;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xchanging.ops.model.OpsDocument;

/**
 *  Utility methods for sending mail. These are thread-safe, so clients are
 *  encouraged to keep a static instance of this service. <p>
 *
 *  <code>static MailService ms = new MailService();</code>
 */
@Service
public class MailService implements Runnable{

	
    public MailService() {
		super();
		
	}

	private Logger logger = LoggerFactory.getLogger(MailService.class);

    private  String  fromAddress;
    private String[] toAddresses;
    private String[] bccAddresses;
    private String   subject;
    private String    body;
    private OpsDocument document;
    
  public MailService(String fromAddress, String [] toAddresses,
			String [] bccAddresses, String subject, String body) {
		super();
	
		this.fromAddress = fromAddress;
		this.toAddresses = toAddresses;
		this.bccAddresses = bccAddresses;
		this.subject = subject;
		this.body = body;
	}

  public MailService(String fromAddress, String [] toAddresses,
			String [] bccAddresses, String subject, String body, OpsDocument document) {
	// TODO Auto-generated constructor stub
		super();
		
		this.fromAddress = fromAddress;
		this.toAddresses = toAddresses;
		this.bccAddresses = bccAddresses;
		this.subject = subject;
		this.body = body;
		this.document=document;
     }

      /**
     * Sends mail including attachments stored in map of attachment names and
     * files.
     */
  public void sendMail(String fromAddress, String[] toAddresses,String[] bccAddresses, String subject, String body, OpsDocument document) throws Exception {
	  logger.debug("Enter : sendMail");
      if (logger.isDebugEnabled()) {
          for (int i = 0; i < toAddresses.length; i++) {
              logger.debug("sending mail to " + toAddresses[i] + " from " + fromAddress + " with subject " + subject);
          }
      }
      if(fromAddress==null || toAddresses==null || subject==null){
          logger.error("Neither of From address, To Address nor subject can't be null");
          return;
      }

      
      MimeMessage message = createMimeMessage();
      int toAddressSize = checkValid(toAddresses);
      Address[] receipients  =new  InternetAddress[toAddressSize];
      for (int i = 0; i < receipients.length; i++) {
          receipients[i] = new InternetAddress(toAddresses[i]);
      }
      
      if(null!=bccAddresses && bccAddresses.length >0){
      	int size = checkValid(bccAddresses);
      	Address[] bcc  =new  InternetAddress[size];
	        for (int i = 0; i < bcc.length; i++) {
	        	if(!StringUtils.isEmpty(bccAddresses[i]))
	        	bcc[i] = new InternetAddress(bccAddresses[i]);
	        }
	        if(bcc!=null && bcc.length>0)
	        	message.addRecipients(Message.RecipientType.BCC, bcc);
      }
      if(receipients!=null && receipients.length>0)
      message.addRecipients(Message.RecipientType.TO, receipients);
     

      Address[] sender  =new  InternetAddress[1];
      sender[0] = new InternetAddress(fromAddress);
      message.addFrom(sender);

      message.setSubject(subject);
      Multipart multipart = new MimeMultipart();
      MimeBodyPart messageBodyPart = new MimeBodyPart();
      messageBodyPart.setContent(body, "text/html");
      multipart.addBodyPart(messageBodyPart, 0);
      MimeBodyPart attachmentDoc=new MimeBodyPart();
      
      attachmentDoc.setFileName(document.getName());
      attachmentDoc.setContent(document,document.getType());
      multipart.addBodyPart(attachmentDoc);
      message.setContent(multipart);
      Transport.send(message);
      
      
      for (int i = 0; i < toAddresses.length; i++) {
          receipients[i] = new InternetAddress(toAddresses[i]);
          logger.info("Successfully mails sent to "+receipients[i]);
      }
      logger.debug("Exit : sendMail");
  }

    public void sendMail(String fromAddress, String[] toAddresses, String[] toCc,String subject, String body, String attachment, String icsFileName)throws Exception{
        logger.debug("Enter : sendMail");
        if (logger.isDebugEnabled()) {
            logger.debug("Mailing to " + toAddresses + " from " + fromAddress + " with subject " + subject);
        }
        if (fromAddress == null || toAddresses == null || subject == null) {
            logger.error("Neither of From address, To Address nor subject can't be null");
            return;
        }
        try{
            //MimeMessage message = createMimeMessage();
            MimeMessage message = createMimeMessageforRFC();
            Address[] receipients = new InternetAddress[toAddresses.length];
            for (int i = 0; i < toAddresses.length; i++) {
                receipients[i] = new InternetAddress(toAddresses[i]);
            }
            Address[] ccreceipients = new InternetAddress[toCc.length];
            for (int i = 0; i < toCc.length; i++) {
            	ccreceipients[i] = new InternetAddress(toCc[i]);
            }
            
            
            
            
            message.addRecipients(Message.RecipientType.TO, receipients);
            message.addRecipients(Message.RecipientType.CC, ccreceipients);
            Address[] sender = new InternetAddress[1];
            sender[0] = new InternetAddress(fromAddress);
            message.addFrom(sender);
            message.setSubject(subject);
            Multipart multipart = new MimeMultipart();
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(body, "text/html");
            multipart.addBodyPart(messageBodyPart, 0);
            if (StringUtils.isNotEmpty(attachment) && StringUtils.isNotEmpty(icsFileName)) {
                MimeBodyPart messageBodyPartAttachment = new MimeBodyPart();
               // DataSource source = new FileDataSource(attachment);
                messageBodyPartAttachment.attachFile(attachment);
                //messageBodyPartAttachment.setDataHandler(new DataHandler(source));
                messageBodyPartAttachment.setFileName(icsFileName);
                multipart.addBodyPart(messageBodyPartAttachment);
            }

            message.setContent(multipart);
            Transport.send(message);
        	
        }catch(IOException ioe){
        	ioe.printStackTrace();
        	throw ioe;
        }catch(MessagingException smge){
        	smge.printStackTrace();
        	throw smge;
        }catch(Exception e){
        	e.printStackTrace();
        	throw e;
        }
        logger.info("Successfully mails sent to " + toAddresses.toString());
        logger.debug("Exit : sendMail");
    }

    /**
     * Sends mail including body subject. files.
     */
   
    public void sendMail(String fromAddress, String[] toAddresses,String[] ccAddresses, String subject, String body) throws Exception {
        logger.debug("Enter : sendMail");
        if (logger.isDebugEnabled()) {
            for (int i = 0; i < toAddresses.length; i++) {
                logger.debug("sending mail to " + toAddresses[i] + " from " + fromAddress + " with subject " + subject);
            }
        }
        if(fromAddress==null || toAddresses==null || subject==null){
            logger.error("Neither of From address, To Address nor subject can't be null");
            return;
        }

        
        //MimeMessage message = createMimeMessage();
        MimeMessage message = createMimeMessageforRFC();
        int toAddressSize = checkValid(toAddresses);
        Address[] receipients  =new  InternetAddress[toAddressSize];
        for (int i = 0; i < receipients.length; i++) {
            receipients[i] = new InternetAddress(toAddresses[i]);
        }
        
        if(null!=ccAddresses && ccAddresses.length >0){
        	int size = checkValid(ccAddresses);
        	Address[] cc  =new  InternetAddress[size];
	        for (int i = 0; i < cc.length; i++) {
	        	if(!StringUtils.isEmpty(ccAddresses[i]))
	        		cc[i] = new InternetAddress(ccAddresses[i]);
	        }
	        if(cc!=null && cc.length>0)
	        	message.addRecipients(Message.RecipientType.CC, cc);
        }
        if(receipients!=null && receipients.length>0)
        message.addRecipients(Message.RecipientType.TO, receipients);
       

        Address[] sender  =new  InternetAddress[1];
        sender[0] = new InternetAddress(fromAddress);
        message.addFrom(sender);

        message.setSubject(subject);
        Multipart multipart = new MimeMultipart();
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(body, "text/html");
        multipart.addBodyPart(messageBodyPart, 0);
        message.setContent(multipart);
        //System.out.println("=====Before Send    : "+Thread.currentThread().getName());	
        Transport.send(message);
        
        
        for (int i = 0; i < toAddresses.length; i++) {
            receipients[i] = new InternetAddress(toAddresses[i]);
            logger.info("Successfully mails sent to "+receipients[i]);
        }
        logger.debug("Exit : sendMail");
    }

    protected MimeMessage createMimeMessage() throws Exception {
    	logger.info("createMimeMessage()");
       Properties props = new Properties();
       Session session= null;
       props.put("mail.smtp.host", System.getProperty("smtp.host","smtp.office365.com"));
       props.put("mail.smtp.port",System.getProperty("smtp.port","587") );
       props.put("mail.smtp.starttls.enable", "true");
       props.put("mail.smtp.auth", "true");
            session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        //return new PasswordAuthentication(System.getProperty("smtp.username","esolutionmail@ncte-india.org"),System.getProperty("smtp.password","Password@1234"));
                    	return new PasswordAuthentication("sunil.rana@xchanging.com","prachi@21");
                    }
                });
            logger.info("createMimeMessage() done");
        return new MimeMessage(session);
    }
    
    protected MimeMessage createMimeMessageforRFC() throws Exception {
    	logger.info("createMimeMessage()");
       Properties props = new Properties();
       Session session= null;
       props.put("mail.smtp.host", "smtp.xchanging.com");
       session = Session.getInstance(props);
//       props.put("mail.smtp.port",System.getProperty("smtp.port","587") );
//       props.put("mail.smtp.starttls.enable", "true");
//       props.put("mail.smtp.auth", "true");
//            session = Session.getInstance(props,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(System.getProperty("smtp.username","esolutionmail@ncte-india.org"),System.getProperty("smtp.password","Password@1234"));
//                    }
//                });
            logger.info("createMimeMessage() done");
        return new MimeMessage(session);
    }
    
    private int  checkValid(String[] bccAddresses){	
	    int validEmail =0;
	    for (int i = 0; i < bccAddresses.length; i++) {
	    	if(!StringUtils.isEmpty(bccAddresses[i]))
	    		validEmail++;
	    }
	    return validEmail;
    }

	public void run() {
		try {
			long startTime=System.currentTimeMillis();
			long stopTime=0L;
			logger.info("Enter into run method   :"+Thread.currentThread().getName());
			if(this.document!=null){
				logger.info("Document is attached so call documented send mail method");
				sendMail(fromAddress, toAddresses, bccAddresses, subject, body,document);
			}
			else{
				sendMail(fromAddress, toAddresses, bccAddresses, subject, body);
				stopTime=System.currentTimeMillis();
			}
			logger.info("Exit from run method   :"+Thread.currentThread().getName()+" Email Sending Time is   :"+(stopTime-startTime));
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Some Exception occurred  :",e);
		}
		
	}

}
