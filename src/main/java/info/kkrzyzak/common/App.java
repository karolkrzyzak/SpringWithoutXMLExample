package info.kkrzyzak.common;

import info.kkrzyzak.kk.bo.KKBo;
import info.kkrzyzak.kk.model.ChatUser;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext appContext = 
    		new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");
	
    	KKBo stockBo = (KKBo)appContext.getBean("stockBo");
    	
    	/** insert **/
    	ChatUser chatUser = new ChatUser();
    	chatUser.setCode("7664");
    	chatUser.setName("HAIO2");
    	stockBo.save(chatUser);
    	
    	/** select **/
    	ChatUser chatUser1 = stockBo.findByCode("7664");
    	System.out.println(chatUser1);
    	
    	/** update **/
    	chatUser1.setName("HAIO-2");
    	stockBo.update(chatUser1);
    	
    	/** delete **/
    	stockBo.delete(chatUser1);
    	
    	System.out.println("Done");
    }
}
