package info.kkrzyzak.kk.bo.impl;

import info.kkrzyzak.kk.bo.KKBo;
import info.kkrzyzak.kk.dao.KKDao;
import info.kkrzyzak.kk.model.ChatUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("stockBo")
public class KKBoImpl implements KKBo {
	
	@Autowired
    KKDao stockDao;
	
	public void setStockDao(KKDao stockDao) {
		this.stockDao = stockDao;
	}

	public void save(ChatUser chatUser){
		stockDao.save(chatUser);
	}
	
	public void update(ChatUser chatUser){
		stockDao.update(chatUser);
	}
	
	public void delete(ChatUser chatUser){
		stockDao.delete(chatUser);
	}
	
	public ChatUser findByCode(String sc){
		return stockDao.findByCode(sc);
	}
}
