package com.dodo.api.servicesImpl;

import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dodo.api.IServices.IProductService;
import com.dodo.api.IServices.IPromotionService;
import com.dodo.api.dtos.ProductDto;
import com.dodo.api.models.Product;
import com.dodo.api.modelview.ProductView;
import com.dodo.api.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private IPromotionService promotionService;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<ProductDto> findAll() {
		try {
			return modelMapper.map(productRepository.findAll(), new TypeToken<List<ProductDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	public ProductDto findById(int id) {
		try {
			return modelMapper.map(productRepository.findById(id).get(), ProductDto.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean save(ProductDto dto) {
		try {
			Product model = modelMapper.map(dto, Product.class);
			productRepository.save(model);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(int id) {
		try {
			productRepository.delete(productRepository.findById(id).get());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<ProductDto> findByKeyword(String keyword) {		
		try {
			return modelMapper.map(productRepository.findByKeyword(keyword), new TypeToken<List<ProductDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	public List<ProductView> findProductViewByProductNameAndCategoryName(String productName, String categoryName) {
		try {
			int topLimit = 50;
			return productRepository.findProductViewByProductNameAndCategoryName(productName, categoryName, topLimit);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	public ProductDto findByProductIdCustom(int id, Boolean statusPr, Boolean statusCate, Boolean statusShop) {
		try {
			return modelMapper.map(productRepository.findByProductIdCustom(id, statusPr, statusCate, statusShop), ProductDto.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ProductDto> findByStatusCustom() {
		try {
			return modelMapper.map(productRepository.findByStatusCustom(true, null, true), new TypeToken<List<ProductDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	public Page<ProductView> findProductViewPageByProductNameAndCategoryName(String productName, String categoryName, int pageNo,
			int pageSize) {
		try {
			Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
			return productRepository.findProductViewByProductNamePage(productName, categoryName, pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<ProductView> findProductViewPageByCategoryId(int id, int pageNo, int pageSize) {
		try {
			Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
			return productRepository.findProductViewByCategoryIdPage(id, pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public int getTotalQuantityByProductId(int productId) {
		try {
			return productRepository.getTotalQuantityByProductId(productId);
		} catch (Exception e) {
			return -1;
		}
	}
	
	@Override
	public Page<ProductView> getListProductByShop(Boolean statusPr, Boolean statusCate, Boolean statusShop, int idShop, int pageNo, int pageSize) {
		try {
			Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
			return productRepository.getListProductByShop(statusPr, statusCate, statusShop, idShop, pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	// LOC
	@Override
	public List<ProductDto> findByShopownerOwnerId(int id) {
		try {
			return modelMapper.map(productRepository.findByShopownerOwnerId(id), new TypeToken<List<ProductDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	public List<ProductDto> findProductWithoutPromotionByShopownerId(int shopownerId) {
		try {
			var productWithPromotionIds = promotionService.findProductIdGotPromotionByShopownerId(shopownerId);
			return modelMapper.map(productRepository.findByIdsNotInAndShopownerIdAndStatusActive(productWithPromotionIds, shopownerId), new TypeToken<List<ProductDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	public List<ProductDto> findByShopownerOwnerIdAndStatusActive(int id) {
		try {
			return modelMapper.map(productRepository.findByShopownerOwnerIdAndStatusActive(id), new TypeToken<List<ProductDto>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	public ProductDto findByProductNameAndShopownerOwnerId(String productName, int shopId) {
		try {
			return modelMapper.map(productRepository.findByProductNameAndShopownerOwnerId(productName, shopId), ProductDto.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// LOC





}