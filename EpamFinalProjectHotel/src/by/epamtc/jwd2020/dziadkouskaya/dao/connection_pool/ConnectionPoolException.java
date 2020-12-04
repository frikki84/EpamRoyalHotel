package by.epamtc.jwd2020.dziadkouskaya.dao.connection_pool;

public class ConnectionPoolException extends Error {


	public ConnectionPoolException() {
	}

	public ConnectionPoolException(String message) {
		super(message);
	}

	public ConnectionPoolException(Throwable e) {
		super(e);
	}

	public ConnectionPoolException(String message, Throwable e) {
		super(message, e);
	}

}
