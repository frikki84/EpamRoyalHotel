package by.epamtc.jwd2020.dziadkouskaya.dao.connection_pool;

public class ConnectionPoolError extends Error {


	public ConnectionPoolError() {
	}

	public ConnectionPoolError(String message) {
		super(message);
	}

	public ConnectionPoolError(Throwable e) {
		super(e);
	}

	public ConnectionPoolError(String message, Throwable e) {
		super(message, e);
	}

}
