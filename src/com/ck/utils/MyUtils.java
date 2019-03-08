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
	// ����˭���ʼ� ע�⣬������qq���� ssl����
	public String myEmailAccount = "sanshengmailbox@163.com";
	// �ⲻ�ǵ�¼���룬����Ȩ����
	public String myEmailPassword = "chenkai815";
	public String myEmailSMTPHost = "smtp.163.com";

	// �ʼ����� �ռ�������
	public void sendMail(String receiveMail) {
		Random rd = new Random();
		StringBuffer sbf = new StringBuffer();
		int flag = 0;
		for(int i = 0; i < 6; i++){
		    flag = rd.nextInt(3);  //������������ж�������֤������ࣨ���֡���д��ĸ��Сд��ĸ��
		    if(flag==0){
		    	sbf.append(rd.nextInt(10));  //��������0-9
		    }else if(flag==1){
		        sbf.append((char)(rd.nextInt(26)+97));	//����Сд��ĸa~z
		    }else{
		        sbf.append((char)(rd.nextInt(26)+65));	//���ɴ�д��ĸA~Z
		    }
		}
		String code = sbf.toString();
		a.code = code;
		System.out.println(code);
		try {
			// 1�������������ã����������ʼ��������Ĳ�������
			Properties props = new Properties(); // ��������
			props.setProperty("mail.transport.protocol", "smtp");// ʹ�õ�Э�飨javaMail�淶Ҫ��
			props.setProperty("mail.smtp.host", myEmailSMTPHost);// �����˵������SMTP
																	// ��������ַ
			props.setProperty("mail.smtp.auth", "true");// ��Ҫ������֤
			// 2���������ô����Ự�������ں��ʼ�����������
			Session session = Session.getInstance(props);
			session.setDebug(false);
			// 3������һ���ʼ�
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myEmailAccount, "QQ2018", "UTF-8"));
			message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "�û�", "UTF-8"));
			message.setSubject("��������ע��", "UTF-8");
			message.setContent("�ף���ӭע��QQ��ÿһ�죬���ڹ�ͨ��       ������֤��Ϊ��" + code, "text/html;charset=UTF-8");
			message.setSentDate(new Date());
			message.saveChanges();
			// 4������Session��ȡ�ʼ��������
			Transport transport = session.getTransport();
			transport.connect(myEmailAccount, myEmailPassword);
			transport.sendMessage(message, message.getAllRecipients());
			// 7���ر�����
			transport.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	// ��������Ϣ
	public void alert(Shell shell, String title, String message) {
		MessageBox mb = new MessageBox(shell, SWT.NONE);
		mb.setText(title);
		mb.setMessage(message);
		mb.open();
	}

	// ������ʾ
	public void centerShell(Shell shell) {
		// Dimension������ߴ硢ά Toolkit�����߰�
		Dimension dem = Toolkit.getDefaultToolkit().getScreenSize();
		// ���ھ�����ʾ
		shell.setLocation((dem.width - shell.getSize().x) / 2, (dem.height - shell.getSize().y) / 2);
	}
}