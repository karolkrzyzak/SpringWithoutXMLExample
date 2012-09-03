package info.kkrzyzak.kk.bo;

import info.kkrzyzak.kk.model.ChatUser;

public interface KKBo {
	
	void save(ChatUser stock);
	
	void update(ChatUser stock);
	
	void delete(ChatUser stock);
	
	ChatUser findByCode(String stockCode);

}
