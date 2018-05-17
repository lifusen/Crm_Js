package cn.itproject.crm.service.impl;

import java.util.Date;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import cn.itproject.crm.service.EmailService;
import cn.itproject.crm.util.EmailConstant;

@Service
public class EmailServiceImpl implements EmailService {
	@Resource
	private JavaMailSender javaMailSender;

	@Override
	public void sendText(String email, String subject, String content) throws Exception {
		if (email == null || email.trim().length() == 0) {
			return;
		}
		if (content == null || content.trim().length() == 0) {
			return;
		}
		
		System.out.println("email:"+email);
		System.out.println("subject:"+subject);
		System.out.println("content:"+content);
		
		SimpleMailMessage simpleTextMessage = new SimpleMailMessage();
		simpleTextMessage.setFrom(EmailConstant.FROM);
		simpleTextMessage.setTo(email);
		Date sendDate = new Date();
		simpleTextMessage.setSubject(subject);
		simpleTextMessage.setText(content);
		simpleTextMessage.setSentDate(sendDate);
		javaMailSender.send(simpleTextMessage);
	}

	@Override
	public void sendHtml(String email, String subject, String content) throws Exception {
		if (email == null || email.trim().length() == 0) {
			return;
		}
		if (content == null || content.trim().length() == 0) {
			return;
		}
		
		System.out.println("email:"+email);
		System.out.println("subject:"+subject);
		System.out.println("content:"+content);
		
		// 建立邮件消息,发送简单邮件和html邮件的区别
		MimeMessage mailMessage = javaMailSender.createMimeMessage();
		// 为防止乱码，添加编码集设置
		MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, "UTF-8");
		messageHelper.setFrom(EmailConstant.FROM);
		messageHelper.setTo(email);
		messageHelper.setSubject(subject);
		messageHelper.setText(content,true);
		messageHelper.setSentDate(new Date());
		javaMailSender.send(mailMessage);
	}

}
