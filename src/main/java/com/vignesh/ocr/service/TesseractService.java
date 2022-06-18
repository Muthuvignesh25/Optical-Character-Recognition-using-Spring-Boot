package com.vignesh.ocr.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.sourceforge.tess4j.Tesseract;


@Service
public class TesseractService implements ITesseractService {

	@Override
	public ResponseEntity<String> convertToText(MultipartFile file) throws Exception {
		String ext = FilenameUtils.getExtension(file.getOriginalFilename());
		if (!"png".equals(ext) && !"jpg".equals(ext)) {
			return ResponseEntity.badRequest().build();
		}
		String result = "";

		try {
			BufferedImage img = ImageIO.read(file.getInputStream());

			Tesseract tesseract = new Tesseract();

			tesseract.setDatapath("C://Users//Cashapona//Documents//Tesseract");
			result = "";

			tesseract.setLanguage("eng");
			result = tesseract.doOCR(img);
			try {
				FileWriter myWriter = new FileWriter("C://Users//Cashapona//Documents//Tesseract//result.txt");
				myWriter.write(result);
				myWriter.close(); 
				
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
		} catch (IOException e) {
			throw new Exception("Error");
		}
		return ResponseEntity.ok(result);

	}
}
