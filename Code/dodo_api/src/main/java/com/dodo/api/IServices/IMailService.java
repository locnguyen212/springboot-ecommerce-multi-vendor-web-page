package com.dodo.api.IServices;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface IMailService {
	public void send(String from, String to, String title, String content) throws Exception;
	public void sendWithFile(String from, String to, String title, String content, MultipartFile file) throws Exception;
	public void sendWithFiles(String from, String to, String title, String content, List<MultipartFile> files) throws Exception;
}
