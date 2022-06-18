package com.vignesh.ocr.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import net.sourceforge.tess4j.TesseractException;

@Service
public interface ITesseractService {

	ResponseEntity<String> convertToText(MultipartFile file) throws TesseractException, Exception;


	

}
