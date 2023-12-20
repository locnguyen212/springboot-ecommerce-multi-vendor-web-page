package com.dodo.api.controllers.api.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dodo.api.IServices.IShopOwnerService;
import com.dodo.api.dtos.ShopownerDto;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/data/shop")
@Tag(name = "Data Shop")
public class ShopownerApiController {
	@Autowired
	IShopOwnerService ownerService;

	//allow all
	@GetMapping(value = { "find-by-id" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<ShopownerDto> findById(@RequestParam(value = "id", required = true) int id) {
		try {
			return new ResponseEntity<ShopownerDto>(ownerService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ShopownerDto>(HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping(value = { "find-by-status" }, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ShopownerDto>> findByStatus(@RequestParam(value = "status", required = true) Boolean status) {
		try {
			return new ResponseEntity<List<ShopownerDto>>(ownerService.findByStatus(status), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ShopownerDto>>(HttpStatus.BAD_REQUEST);
		}

	}
}
