package com.dodo.web.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dodo.web.IServices.IProductService;
import com.dodo.web.IServices.IPromotionService;
import com.dodo.web.models.Product;
import com.dodo.web.modelview.ProductView;
import com.dodo.web.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private IPromotionService promotionService;

	@Override
	public List<Product> findAll() {
		try {
			return productRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Product findById(int id) {
		try {
			return productRepository.findById(id).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean save(Product product) {
		try {
			productRepository.save(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(int id) {
		try {
			productRepository.delete(findById(id));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<ProductView> findProductViewByProductName(String productName, String categoryName) {
		try {
			int topLimit = 50;
			return productRepository.findProductViewByProductName(productName, categoryName, topLimit);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Product findProductById(int id, Boolean statusPr, Boolean statusCate, Boolean statusShop) {
		try {
			return productRepository.findProductById(id, statusPr, statusCate, statusShop);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Product> getAllAndStatus() {
		try {
			return productRepository.getAllAndStatus(true, null, true);
		} catch (Exception e) {
			return null;
		}
	}

	public List<Product> getProductsByOwnerId2(Integer ownerId) {
		return productRepository.findByOwnerId2(ownerId);
	}

	@Override
	public Page<ProductView> findProductViewByProductNamePage(String productName, String categoryName, int pageNo,
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
	public Page<ProductView> findProductViewByCategoryIdPage(int id, int pageNo, int pageSize) {
		try {
			Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
			return productRepository.findProductViewByCategoryIdPage(id, pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<ProductView> getListProductByShop(Boolean statusPr, Boolean statusCate, Boolean statusShop, int idShop,
			int pageNo, int pageSize) {
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
	public List<Product> findByShopownerOwnerId(int id) {
		try {
			return productRepository.findByShopownerOwnerId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Product> findProductWithoutPromotionByShopownerId(int shopownerId) {
		try {
			var productWithPromotionIds = promotionService.findProductIdGotPromotionByShopownerId(shopownerId);
			return productRepository.findByIdsNotInAndShopownerIdAndStatusActive(productWithPromotionIds, shopownerId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Product> findByShopownerOwnerIdAndStatusActive(int id) {
		try {
			return productRepository.findByShopownerOwnerIdAndStatusActive(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Product findByProductNameAndShopownerOwnerId(String productName, int shopId) {
		try {
			return productRepository.findByProductNameAndShopownerOwnerId(productName, shopId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// LOC

}