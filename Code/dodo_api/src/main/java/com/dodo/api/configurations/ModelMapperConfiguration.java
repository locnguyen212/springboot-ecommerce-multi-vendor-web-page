package com.dodo.api.configurations;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.dodo.api.dtos.ParentcategoryDto;
import com.dodo.api.dtos.ProductDto;
import com.dodo.api.dtos.ReviewDto;
import com.dodo.api.dtos.ShopownerDto;
import com.dodo.api.dtos.UserDto;
import com.dodo.api.models.Parentcategory;
import com.dodo.api.models.Product;
import com.dodo.api.models.Review;
import com.dodo.api.models.Shopowner;
import com.dodo.api.models.User;


@Configuration
public class ModelMapperConfiguration {
	@Autowired
	private Environment environment;
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMappers = new ModelMapper();
		
		Converter<String, String> photoToUrl = new AbstractConverter<String, String>() {
			@Override
			protected String convert(String source) {
				return environment.getProperty("base-url-upload")+source;
			}
			
		};
		
		modelMappers.typeMap(Product.class, ProductDto.class)
		.addMappings(mappers -> {
			mappers.using(photoToUrl).map(Product::getProductImage, ProductDto::setProductImage);
		});
		
		modelMappers.typeMap(Parentcategory.class, ParentcategoryDto.class)
		.addMappings(mappers -> {
			mappers.using(photoToUrl).map(Parentcategory::getParentCategoryImagePath, ParentcategoryDto::setParentCategoryImagePath);
		});
		
		modelMappers.typeMap(Review.class, ReviewDto.class)
		.addMappings(mappers -> {
			mappers.using(photoToUrl).map(Review::getReviewImage, ReviewDto::setReviewImage);
		});
		
		modelMappers.typeMap(Shopowner.class, ShopownerDto.class)
		.addMappings(mappers -> {
			mappers.using(photoToUrl).map(Shopowner::getShopLogoPath, ShopownerDto::setShopLogoPath);
		});
		
		modelMappers.typeMap(User.class, UserDto.class)
		.addMappings(mappers -> {
			mappers.using(photoToUrl).map(User::getAvatar, UserDto::setAvatar);
		});
		
		
		return modelMappers;
	}
}
