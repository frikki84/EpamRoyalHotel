package by.epamtc.jwd2020.dziadkouskaya.dao.connection_pool;

//все параметры для создания коннекшн (чтобы не руками писать,  таскать отсюда автоматом)
public class DBParametr {

	private DBParametr() {

	}

	public static final String DB_DRIVER = "db.driver";
	public static final String DB_URL = "db.url";
	public static final String DB_USER = "db.user";
	public static final String DB_PASSWORD = "db.password";
	public static final String DB_POOL_SIZE = "db.poolsize";

}
