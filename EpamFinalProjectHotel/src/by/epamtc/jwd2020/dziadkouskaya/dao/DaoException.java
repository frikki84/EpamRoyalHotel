package by.epamtc.jwd2020.dziadkouskaya.dao;

public class DaoException extends Exception {


	public DaoException() {
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(Exception e) {
		super(e);
	}

	public DaoException(String message, Exception e) {
		super(message, e);
	}
	
	public DaoException(String message, Throwable e) {
		super(message, e);
	}

}
