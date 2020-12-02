package by.epamtc.jwd2020.dziadkouskaya.controller;

import java.sql.Date;
import java.util.List;

import by.epamtc.jwd2020.dziadkouskaya.bean.BabyExpense;
import by.epamtc.jwd2020.dziadkouskaya.bean.RoomBooking;
import by.epamtc.jwd2020.dziadkouskaya.dao.DaoException;
import by.epamtc.jwd2020.dziadkouskaya.dao.impl.BookingDaoImpl;

public class Main {
	public static void main(String[] args) throws DaoException {
		BookingDaoImpl daoImpl = new BookingDaoImpl();
		List<RoomBooking> list = daoImpl.findFreeRooms(90, 3, 0, Date.valueOf("2020-12-05"), Date.valueOf("2020-12-08"));
		BabyExpense expense = daoImpl.findBabyExpense();
		System.out.println(list);
		
		
		
	}

}
