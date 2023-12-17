package com.dodo.api.servicesImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dodo.api.IServices.IMailService;

import jakarta.activation.DataHandler;
import jakarta.activation.FileDataSource;
import jakarta.mail.BodyPart;
import jakarta.mail.Multipart;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

@Service
public class MailServiceImpl implements IMailService {
	//để gửi mail, ta cần java mail sender từ thư viện chọn trong spring boot, và interface JavaMailSender
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void send(String from, String to, String title, String content) throws Exception {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
		mimeMessageHelper.setFrom(from);
		mimeMessageHelper.setTo(to);
		mimeMessageHelper.setSentDate(new Date());
		mimeMessageHelper.setSubject(title);
		//set content, nếu có html thì đặt true, để nó render html
		mimeMessageHelper.setText(content, true);
		//gửi mail
		javaMailSender.send(mimeMessage);
	}

	@Override
	public void sendWithFile(String from, String to, String title, String content, MultipartFile file)
			throws Exception {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
		mimeMessageHelper.setFrom(from);
		mimeMessageHelper.setTo(to);
		mimeMessageHelper.setSentDate(new Date());
		mimeMessageHelper.setSubject(title);
		
		//tạo multipart, bao gồm 2 phần là content mail và file 
		Multipart multipart = new MimeMultipart();
		
		//set content email, ta dùng BodyPart cho content của mail có file
		BodyPart bodyPart = new MimeBodyPart();		
		bodyPart.setContent(content, "text/html");
		//thêm content vào mail
		multipart.addBodyPart(bodyPart);
		
		//set file cho email, ta dùng MimeBodyPart để đính kèm file vào mail
		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setFileName(file.getOriginalFilename());
		FileDataSource fileDataSource = new FileDataSource(multipartToFile(file));
		mimeBodyPart.setDataHandler(new DataHandler(fileDataSource));		
		//thêm file vào mail
		multipart.addBodyPart(mimeBodyPart);
		
		//thêm content vào mimeMessage
		mimeMessage.setContent(multipart);
		
		//gửi mail
		javaMailSender.send(mimeMessage);	
	}
	
	@Override
	public void sendWithFiles(String from, String to, String title, String content, List<MultipartFile> files)
			throws Exception {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
		mimeMessageHelper.setFrom(from);
		mimeMessageHelper.setTo(to);
		mimeMessageHelper.setSentDate(new Date());
		mimeMessageHelper.setSubject(title);
		
		//tạo multipart, bao gồm 2 phần là content mail và file 
		Multipart multipart = new MimeMultipart();
		
		//set content email, ta dùng BodyPart cho content của mail có file
		BodyPart bodyPart = new MimeBodyPart();		
		bodyPart.setContent(content, "text/html");
		//thêm content vào mail
		multipart.addBodyPart(bodyPart);
		
		//set file cho email, ta dùng MimeBodyPart để đính kèm file vào mail
		for(var file : files) {
			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			mimeBodyPart.setFileName(file.getOriginalFilename());
			FileDataSource fileDataSource = new FileDataSource(multipartToFile(file));
			mimeBodyPart.setDataHandler(new DataHandler(fileDataSource));		
			//thêm file vào mail
			multipart.addBodyPart(mimeBodyPart);
		}	
		
		//thêm content vào mimeMessage
		mimeMessage.setContent(multipart);
		
		//gửi mail
		javaMailSender.send(mimeMessage);
		
	}

	private File multipartToFile(MultipartFile multipartFile) {
		try {
			File file = new File(multipartFile.getOriginalFilename());
			file.createNewFile();
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(multipartFile.getBytes());
			fileOutputStream.close();
			return file;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}



}
