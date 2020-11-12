package by.epamtc.jwd2020.dziadkouskaya.dao.connection_pool;

import java.util.ResourceBundle;
//подключая пакет, в котором находятся ресурсы
public class DBResourceManager {
	private final static DBResourceManager instance = new DBResourceManager();
	
	private ResourceBundle bundle = ResourceBundle.getBundle("resources.db");
	
	public static DBResourceManager getInstance() {
		return instance;
	}
	
	public String getValue(String key) {
		return bundle.getString(key);
	}
 
}
