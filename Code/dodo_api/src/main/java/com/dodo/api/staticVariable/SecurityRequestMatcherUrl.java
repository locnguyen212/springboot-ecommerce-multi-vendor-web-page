package com.dodo.api.staticVariable;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum SecurityRequestMatcherUrl {
	WHITE_LIST(
		new String[] {
			"/"
			, "/login/**"
			, "/api/auth/**"
			, "/api/register/**"
			, "/upload/**"
			, "/api/data/category/**"
			, "/api/data/product/**"
			, "/api/data/promotion/**"
			, "/api/data/review/**"
			, "/api/data/shop/find-by-id"
			, "/api/data/user/**"
			, "/v2/api-docs" //swagger open api start
			, "/v3/api-docs"
			, "/v3/api-docs/**"
			, "/swagger-resources"
			, "/swagger-resources/**"
			, "/configuration/ui"
			, "/configuration/security"
			, "/swagger-ui/**"
			, "/webjars/**"
			, "/swagger-ui.html"  //swagger open api end
		},
		new String[] {
				
		}
	),
	
	
	USER (
		new String[] {
			"/api/crud/shop/create"
		},
		new String[] {
			"USER"
		}
	),
	
	SHOPOWNER (
		new String[] {
    		"/api/data/order/find-by-order-status-and-current-shop"
    		, "/api/data/order/find-by-order-status-not-in-and-current-shop"
    		, "/api/data/order/find-by-order-status-and-current-shop"
    		, "/api/data/order/find-by-order-status-not-in-and-current-shop"
    		, "/api/data/product/find-product-without-promotion-by-current-shop"
    		, "/api/data/product/find-active-product-by-current-shop"
    		, "/api/data/product/find-by-name-and-current-shop"
    		, "/api/data/shop-coupon/find-by-current-shop"
    		, "/api/crud/product/**"
    		, "/api/crud/promotion/**"
    		, "/api/crud/shop-coupon/**"
    		, "/api/crud/shop/edit-profile"
		},
		new String[] {
			"SHOPOWNER"
		}
	),
	
	SUPERADMIN (
		new String[] {
    		"/api/data/order/find-by-order-status-and-current-shop"
    		, "/api/data/order/find-by-order-status-not-in-and-current-shop"
    		, "/api/data/order/find-sales-data-by-year"
    		, "/api/data/order/find-sales-data-by-year-and-month"
    		, "/api/data/order/find-sales-data-years"
    		, "/api/crud/category/delete"
    		, "/api/crud/parent-category/delete"
    		, "/api/crud/user/delete"
		},
		new String[] {
			"SUPER_ADMIN"
		}
	),
	
	ADMIN_SUPERADMIN (
		new String[] {
    		"/api/data/category/find-by-status"
    		, "/api/data/category/find-by-status-not-null"
    		, "/api/data/category/find-by-user-id"
    		, "/api/data/parent-category/**"
    		, "/api/data/shop/find-by-status"
    		, "/api/data/user/find-all"
    		, "/api/data/user/find-by-role-user-and-shop"
    		, "/api/crud/category/edit"
    		, "/api/crud/parent-category/create"
    		, "/api/crud/parent-category/edit"
    		, "/api/crud/shop/edit"
    		, "/api/crud/shop/approval"
    		, "/api/crud/user/create"
    		, "/api/crud/user/edit"
		},
		new String[] {
			"SUPER_ADMIN",
			"ADMIN"
		}
	),
	
	USER_SHOPOWNER (
		new String[] {
			"/api/data/item/**"
			, "/api/data/order/find-by-order-status-and-logged-in-user"
			, "/api/data/order/find-by-order-status-not-in-and-logged-in-user"
			, "/api/data/order/find-by-payment-status-and-logged-in-user"
			, "/api/data/order/find-by-order-status-not-in-and-logged-in-user"
			, "/api/data/order-cancellation/**"
			, "/api/data/order-details/**"
			, "/api/data/review/find-by-logged-in-user"
			, "/api/data/shop-coupon/find-by-id"
			, "/api/data/wishlist/**"
			, "/api/crud/item/**"
			, "/api/crud/order-cancellation/**"
			, "/api/crud/order/**"
			, "/api/crud/order-details/**"
			, "/api/crud/review/**"
			, "/api/crud/wishlist/**"
		},
		new String[] {
			"USER",
			"SHOPOWNER"
		}
	),
	
	ADMIN_SUPERADMIN_SHOPOWNER (
		new String[] {
			"/api/data/category/find-all-paginate"
			, "/api/crud/category/create"
		},
		new String[] {
			"SUPER_ADMIN",
			"ADMIN",
			"SHOPOWNER"
		}
	),
	
	ADMIN_SUPERADMIN_SHOPOWNER_USER (
			new String[] {
				"/api/crud/user/edit-profile"
			},
			new String[] {
				"SUPER_ADMIN",
				"ADMIN",
				"SHOPOWNER",
				"USER"
			}
	),
	
	;

    // "/api/crud/user/delete"
	@Getter
	private final String[] urls;
	
	@Getter
	private final String[] roles;
}
