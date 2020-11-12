package by.epamtc.jwd2020.dziadkouskaya.view;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class MyTagEmpty extends SimpleTagSupport {
	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		out.println(Date.valueOf(LocalDate.now()));

	}
}
