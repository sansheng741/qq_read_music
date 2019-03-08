package com.ck.utils;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;



public class MyUtils {
	// 设置谁发邮件 注意，不能用qq邮箱 ssl链接
	public String myEmailAccount = "sanshengmailbox@163.com";
	// 这不是登录密码，是授权密码
	public String myEmailPassword = "chenkai815";
	public String myEmailSMTPHost = "smtp.163.com";

	// 邮件内容 收件人邮箱
	public void sendMail(String receiveMail) {
		Random rd = new Random();
		StringBuffer sbf = new StringBuffer();
		int flag = 0;
		for(int i = 0; i < 6; i++){
		    flag = rd.nextInt(3);  //生成随机数，判断生成验证码的种类（数字、大写字母、小写字母）
		    if(flag==0){
		    	sbf.append(rd.nextInt(10));  //生成数字0-9
		    }else if(flag==1){
		        sbf.append((char)(rd.nextInt(26)+97));	//生成小写字母a~z
		    }else{
		        sbf.append((char)(rd.nextInt(26)+65));	//生成大写字母A~Z
		    }
		}
		String code = sbf.toString();
		a.code = code;
		System.out.println(code);
		try {
			// 1、创建参数配置，用于连接邮件服务器的参数配置
			Properties props = new Properties(); // 参数配置
			props.setProperty("mail.transport.protocol", "smtp");// 使用的协议（javaMail规范要求）
			props.setProperty("mail.smtp.host", myEmailSMTPHost);// 发件人的邮箱的SMTP
																	// 服务器地址
			props.setProperty("mail.smtp.auth", "true");// 需要请求认证
			// 2、根据配置创建会话对象，用于和邮件服务器交互
			Session session = Session.getInstance(props);
			session.setDebug(false);
			// 3、创建一封邮件
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myEmailAccount, "QQ2018", "UTF-8"));
			message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "用户", "UTF-8"));
			message.setSubject("网易邮箱注册", "UTF-8");
			message.setContent("亲，欢迎注册QQ，每一天，乐在沟通。       您的验证码为：" + code, "text/html;charset=UTF-8");
			message.setSentDate(new Date());
			message.saveChanges();
			// 4、根据Session获取邮件传输对象
			Transport transport = session.getTransport();
			transport.connect(myEmailAccount, myEmailPassword);
			transport.sendMessage(message, message.getAllRecipients());
			// 7、关闭连接
			transport.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	// 弹出框信息
	public void alert(Shell shell, String title, String message) {
		MessageBox mb = new MessageBox(shell, SWT.NONE);
		mb.setText(title);
		mb.setMessage(message);
		mb.open();
	}

	// 居中显示
	public void centerShell(Shell shell) {
		// Dimension：标出尺寸、维 Toolkit：工具包
		Dimension dem = Toolkit.getDefaultToolkit().getScreenSize();
		// 窗口剧中显示
		shell.setLocation((dem.width - shell.getSize().x) / 2, (dem.height - shell.getSize().y) / 2);
	}
}