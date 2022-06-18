package com.vignesh.ocr.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vignesh.ocr.service.ITesseractService;

import java.io.File;



@RestController
public class TesseractController {
	
	@Autowired
	ITesseractService tesseractService;
	


	@PostMapping("/ocr")
	public ResponseEntity<String> convertToText(@RequestParam(name="file") MultipartFile file) throws Exception{
		return tesseractService.convertToText(file);
		
	}
	
	

}
