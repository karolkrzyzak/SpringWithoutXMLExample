package info.kkrzyzak.kk.dao;

import info.kkrzyzak.kk.model.ChatUser;

public interface KKDao {
	
	void save(ChatUser chatUser);
	
	void update(ChatUser chatUser);
	
	void delete(ChatUser chatUser);
	
	ChatUser findByCode(String code);

}
