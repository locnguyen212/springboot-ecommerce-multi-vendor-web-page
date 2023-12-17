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
import com.dodo.api.models.Parentcategory;
import com.dodo.api.models.Product;
import com.dodo.api.models.Review;
import com.dodo.api.models.Shopowner;


@Configuration
public class ModelMapperConfiguration {
	@Autowired
	private Environment environment;
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMappers = new ModelMapper();
		
//		Converter<Integer, Parentcategory> idToParentCategory = new AbstractConverter<Integer, Parentcategory>() {
//			@Override
//			protected Parentcategory convert(Integer source) {
//				var target = new Parentcategory();
//				target.setParentCategoryId(source);
//				return target;
//			}
//			
//		};
//		
//		Converter<Integer, User> idToUser = new AbstractConverter<Integer, User>() {
//			@Override
//			protected User convert(Integer source) {
//				var target = new User();
//				target.setUserId(source);
//				return target;
//			}
//			
//		};
//		
//		Converter<Integer, Category> idToCategory = new AbstractConverter<Integer, Category>() {
//			@Override
//			protected Category convert(Integer source) {
//				var target = new Category();
//				target.setCategoryId(source);
//				return target;
//			}
//			
//		};
//		
//		Converter<Integer, Order> idToOrder = new AbstractConverter<Integer, Order>() {
//			@Override
//			protected Order convert(Integer source) {
//				var target = new Order();
//				target.setOrderId(source);
//				return target;
//			}
//			
//		};
//		
//		Converter<Integer, Product> idToProduct = new AbstractConverter<Integer, Product>() {
//			@Override
//			protected Product convert(Integer source) {
//				var target = new Product();
//				target.setProductId(source);
//				return target;
//			}
//			
//		};
//		
//		Converter<Integer, Role> idToRole = new AbstractConverter<Integer, Role>() {
//			@Override
//			protected Role convert(Integer source) {
//				var target = new Role();
//				target.setRoleId(source);
//				return target;
//			}
//			
//		};
//		
//		Converter<Integer, Shopowner> idToShop = new AbstractConverter<Integer, Shopowner>() {
//			@Override
//			protected Shopowner convert(Integer source) {
//				var target = new Shopowner();
//				target.setOwnerId(source);
//				return target;
//			}
//			
//		};
		
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
		
		
		return modelMappers;
	}
}
