package cn.itproject.crm.websocket;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itproject.crm.bean.Notification;

/**
 * Servlet implementation class SendMsg
 */
@WebServlet("/sendMsg")
public class SendMsg extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String msg = req.getParameter("msg");
		Notification notification = new Notification();
		notification.setContent(msg);
		notification.setReceiverId(1);
		WebSocketUtil.send(notification);
	}
}
