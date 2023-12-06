package com.dodo.web.controllers.shop_owners;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dodo.web.IServices.IOrderDetailService;
import com.dodo.web.IServices.IOrderService;
import com.dodo.web.IServices.IShopOwnerCouponService;
import com.dodo.web.IServices.IShopOwnerService;
import com.dodo.web.IServices.IUserService;
import com.dodo.web.models.Order;
import com.dodo.web.models.Shopowner;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("shop-owners")
public class HomeShopController {
	@Autowired
	IUserService userService;

	@Autowired
	IShopOwnerService shopOwnerService;

	@Autowired
	IShopOwnerCouponService couponService;

	@Autowired
	IOrderService orderService;

	@Autowired
	IOrderDetailService detailService;


	@GetMapping({ "index", "", "/" })
	public String index(Model model, HttpSession session, Authentication authentication) {
		if (authentication != null) {
			var user = userService.findByUsername(authentication.getName());
			Shopowner shopOwner = shopOwnerService.findByUserUsername(user.getUsername());
			if (shopOwner != null) {
				session.setAttribute("userId", user.getUserId());
				session.setAttribute("ownerId", shopOwner.getOwnerId());
				session.setAttribute("shopName", shopOwner.getShopName());
				session.setAttribute("shopLogoPath", shopOwner.getShopLogoPath());
				shopOwner.setOwnerId(shopOwner.getOwnerId());
				// Add the shopowner object to the model
				model.addAttribute("shopowner", shopOwner);
				Integer totalOrders = orderService.getTotalOrdersForShopOwner(shopOwner.getOwnerId());
				model.addAttribute("totalOrders", totalOrders != null ? totalOrders : 0);

				LocalDateTime currentTime = LocalDateTime.now();

				// Đặt thời gian bắt đầu là 00:00 hôm nay
				LocalDateTime startTime = LocalDateTime.of(currentTime.toLocalDate(), LocalTime.MIN);

				// Đặt thời gian kết thúc là 23:59 hôm nay
				LocalDateTime endTime = LocalDateTime.of(currentTime.toLocalDate(), LocalTime.MAX);

				// Chuyển các giá trị LocalDateTime thành ZonedDateTime để có thể sử dụng ZoneId
				LocalDateTime startOfToday = LocalDateTime.now().with(LocalTime.MIN);
				LocalDateTime endOfToday = LocalDateTime.now().with(LocalTime.MAX);

				Date startDate = Date.from(startOfToday.atZone(ZoneId.systemDefault()).toInstant());
				Date endDate = Date.from(endOfToday.atZone(ZoneId.systemDefault()).toInstant());

				// Sau đó sử dụng startDate và endDate trong truy vấn của bạn
				List<Order> newOrders = orderService.getOrdersByShopOwnerAndOrderDateBetween(
						shopOwner.getOwnerId(), startDate, endDate);
				int numberOfNewOrders = newOrders.size();
				model.addAttribute("numberOfNewOrders", numberOfNewOrders);

				// Tính toàn bộ doanh thu từ các đơn hàng đã giao
				List<Order> deliveredOrders = orderService.getOrdersByShopOwnerIdAndStatus(shopOwner.getOwnerId(),
						"Delivered");
				Double totalEarnings = 0.0;
				for (Order order : deliveredOrders) {
					totalEarnings += order.getTotalAmount();
				}
				model.addAttribute("totalEarnings", totalEarnings);

				// Lọc ra các đơn hàng chỉ trong ngày hiện tại
				/// Lấy danh sách đơn hàng hủy trong khoảng thời gian startDate đến endDate
				List<Order> cancelledOrdersToday = orderService.getOrdersByShopOwnerAndOrderDateBetween2(
						shopOwner.getOwnerId(), startDate, endDate);
				// Lấy danh sách tất cả đơn hàng đã hủy từ tất cả các ngày
				List<Order> allCancelledOrders = orderService.getAllCancelledOrders(shopOwner.getOwnerId());
				// Tính tổng giá trị của tất cả các đơn hàng đã hủy từ tất cả các ngày
				Double totalEarningsCancelledAll2 = allCancelledOrders.stream()
						.mapToDouble(Order::getTotalAmount)
						.sum();

				// Đưa kết quả vào model để sử dụng trong view
				model.addAttribute("totalEarningsCancelledAll2", totalEarningsCancelledAll2);

				// Tính tổng số lượng sản phẩm bị hủy và tổng giá trị của các đơn hàng hủy từ
				// tất cả các ngày
				Double totalEarningsCancelledAll = cancelledOrdersToday.stream()
						.mapToDouble(Order::getTotalAmount)
						.sum();
				model.addAttribute("totalEarningsCancelledAll", totalEarningsCancelledAll);

				// Lọc ra đơn hàng hủy trong ngày để hiển thị số lượng đơn hủy trong ngày
				List<Order> cancelledOrdersTodayFiltered = cancelledOrdersToday.stream()
						.filter(order -> {
							Instant instant = order.getOrderDate().toInstant(); // Chuyển đổi từ Date sang Instant
							LocalDate orderDate = instant.atZone(ZoneId.systemDefault()).toLocalDate(); // Chuyển đổi từ
																										// Instant sang
																										// LocalDate

							// Kiểm tra xem đơn hàng có thuộc ngày hiện tại không
							return orderDate.equals(LocalDate.now()); // Lọc theo ngày hiện tại
						})
						.collect(Collectors.toList());

				// Số lượng đơn hàng hủy trong ngày hiện tại
				int numberOfCancelledOrdersToday = cancelledOrdersTodayFiltered.size();
				model.addAttribute("numberOfCancelledOrdersToday", numberOfCancelledOrdersToday);

				// 3 sản phầm bán chạy

				List<Object[]> topSellingProducts = detailService.findTopSellingProducts(shopOwner.getOwnerId());

				List<Object[]> top3SellingProducts = topSellingProducts.stream()
						.limit(3) // Lấy ba sản phẩm đầu tiên
						.collect(Collectors.toList());
				model.addAttribute("top3SellingProducts", top3SellingProducts);

				// tính chart tròn về đơn đã giao và đơn bị trả về

				int cancelledCount = orderService.countCancelledOrdersByOwnerId(shopOwner.getOwnerId());
				int deliveredCount = orderService.countDeliveredOrdersByOwnerId(shopOwner.getOwnerId());

				model.addAttribute("cancelledCount", cancelledCount);
				model.addAttribute("deliveredCount", deliveredCount);

				// biểu đồ cột có thể thể hiện phân phối của tình trạng đơn hàng

				int cancelledCount2 = orderService.countCancelledOrdersByOwnerId(shopOwner.getOwnerId());
				int deliveredCount2 = orderService.countDeliveredOrdersByOwnerId(shopOwner.getOwnerId());
				int waitingForApprovalCount = orderService
						.countWaitingForApprovalOrdersByOwnerId(shopOwner.getOwnerId());
				int inProcessCount = orderService.countInProcessOrdersByOwnerId(shopOwner.getOwnerId());
				int packagingCount = orderService.countPackagingOrdersByOwnerId(shopOwner.getOwnerId());

				model.addAttribute("cancelledCount2", cancelledCount2);
				model.addAttribute("deliveredCount2", deliveredCount2);
				model.addAttribute("waitingForApprovalCount", waitingForApprovalCount);
				model.addAttribute("inProcessCount", inProcessCount);
				model.addAttribute("packagingCount", packagingCount);

				// Biểu đồ tròn có thể thể hiện phần trăm các đơn hàng đã thanh toán và chưa
				// thanh toán.

				int paidOrders = orderService.countPaymentStatusOrdersByOwnerId(shopOwner.getOwnerId());
				int unpaidOrders = orderService.countPaymentStatusNullOrdersByOwnerId(shopOwner.getOwnerId());
				model.addAttribute("paidOrders", paidOrders);
				model.addAttribute("unpaidOrders", unpaidOrders);

				// Biểu đồ cột hiển thị các sản phẩm được đặt hàng nhiều nhất.
				List<Object[]> topSellingProducts2 = detailService.findTopSellingProducts(shopOwner.getOwnerId());

				// Xử lý kết quả trả về để tạo các list chứa nhãn và số lượng cho biểu đồ cột
				List<String> productLabels = topSellingProducts.stream()
						.map(obj -> (String) obj[1]) // Cột thứ hai chứa tên sản phẩm
						.collect(Collectors.toList());

				List<Integer> productQuantities = topSellingProducts.stream()
						.map(obj -> ((Number) obj[2]).intValue()) // Cột thứ ba chứa tổng số lượng
						.collect(Collectors.toList());

				model.addAttribute("productLabels", productLabels);
				model.addAttribute("productQuantities", productQuantities);

			}

		}

		return "shopOwners/home/index";
	}

	public String formatDate(Date date) {
		// Logic to format the date into a String representation
		// For example:
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);
	}

	@GetMapping("/logout")
	public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		// Xóa các thông tin trong session
		session.invalidate();

		// Sử dụng Spring Security để logout
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}

		// Thêm thông báo thành công
		redirectAttributes.addFlashAttribute("successMessage", "Bạn đã đăng xuất thành công!");

		return "redirect:/login";
	}
}
