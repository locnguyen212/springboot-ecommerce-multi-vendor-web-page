package com.dodo.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dodo.web.models.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
	//====LOC====
	public List<Review> findByProductProductId(int id);
	public List<Review> findByProductProductName(String name);
	
	public List<Review> findByUserUserId(int id);
	public List<Review> findByUserUsername(String name);
	
	public List<Review> findByRating(int rating);

	@Query(value = "SELECT r FROM Review r "
        + "WHERE r.product.shopowner.ownerId = :shopId")
	public List<Review> getReviewsByShopId(@Param("shopId") int shopId);
	//====LOC====
}
