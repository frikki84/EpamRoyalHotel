package by.epamtc.jwd2020.dziadkouskaya.dao.connection_pool;

public class ConnectionPoolException extends Error {


	public ConnectionPoolException() {
	}

	public ConnectionPoolException(String message) {
		super(message);
	}

	public ConnectionPoolException(Exception e) {
		super(e);
	}

	public ConnectionPoolException(String message, Exception e) {
		super(message, e);
	}

}
