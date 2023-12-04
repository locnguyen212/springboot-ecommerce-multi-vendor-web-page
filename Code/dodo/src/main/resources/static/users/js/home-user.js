function SearchHeader() {
  var productName = $("#ip_searchProduct").val();
  var categoryName = $("#selectbox_alCategory").val();
  $.ajax({
    url: "/user/api/products/searchHeader",
    data: {
      productName: productName,
      categoryName: categoryName,
    },
    method: "GET",
    success: function (data) {
      var productList = $("#searchProductList ul");
      productList.empty();

      data.forEach(function (product) {
        var listItem = $("<li>");
        listItem.html(`
              <a href="/user/product/detail?id=${product.productId}">
                <div class="thumb">
                    <img src="/upload/${product.productImage}">
                </div>
                <div class="info-product" style="margin-top: 20px;">
                    <div style="font-size: 13pt;" class="title">${product.productName} </div>
                </div>
                </a>
            `);

        productList.append(listItem);
      });
    },
  });
}
$("#selectbox_alCategory").change(function () {
  $("#categoryName").val($(this).val());
});