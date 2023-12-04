//toggle between hiding and showing the dropdown content
function myFunction() {
  document.getElementById("myDropdown").classList.toggle("show");
}
// Closes dropdown
window.onclick = function (event) {
  if (!event.target.matches(".dropbtn")) {
    var dropdowns = document.getElementsByClassName("dropdown-content");
    var i;
    for (i = 0; i < dropdowns.length; i++) {
      var openDropdown = dropdowns[i];
      if (openDropdown.classList.contains("show")) {
        openDropdown.classList.remove("show");
      }
    }
  }
};

// Sidebar Menu Script
$.sidebarMenu = function (menu) {
  var animationSpeed = 300;
  $(menu).on("click", "li a", function (e) {
    var $this = $(this);
    var checkElement = $this.next();
    if (checkElement.is(".treeview-menu") && checkElement.is(":visible")) {
      checkElement.slideUp(animationSpeed, function () {
        checkElement.removeClass("menu-open");
      });
      checkElement.parent("li").removeClass("active");
    }

    //If the menu is not visible
    else if (
      checkElement.is(".treeview-menu") &&
      !checkElement.is(":visible")
    ) {
      //Get the parent menu
      var parent = $this.parents("ul").first();
      //Close all open menus within the parent
      var ul = parent.find("ul:visible").slideUp(animationSpeed);
      //Remove the menu-open class from the parent
      ul.removeClass("menu-open");
      //Get the parent li
      var parent_li = $this.parent("li");
      //Open the target menu and add the menu-open class
      checkElement.slideDown(animationSpeed, function () {
        //Add the class active to the parent li
        checkElement.addClass("menu-open");
        parent.find("li.active").removeClass("active");
        parent_li.addClass("active");
      });
    }
    //if this isn't a link, prevent the page from being redirected
    if (checkElement.is(".treeview-menu")) {
      e.preventDefault();
    }
  });
};
$.sidebarMenu($(".sidebar-menu"));

/* ----- Dashboard Sidebar Open Close ----- */
$(document).on("ready", function () {
  $(".dashboard_sidebar_toggle_icon").on("click", function () {
    $(".dashboard.dashboard_wrapper").toggleClass("dsh_board_sidebar_hidden");
  });
});

async function addToCart(productId, quantity = 1) {
  if (productId == "") {
    alert("Product does not exist !");
    return false;
  } else {
    await $.ajax({
      type: "GET",
      url: "/user/cart/add_cart",
      data: {
        id: productId,
        quantity: quantity,
      },
      success: async function (response) {
        await countItem();
        if(response == "error"){
          WarningAlert("Error");
        }else {
          SuccessAlert("Add cart success!");
        }
      },
      error: function (e) {
        WarningAlert("Error");
      },
    });
  }
}

async function countItem() {
  await $.ajax({
    type: "GET",
    url: "/user/api/cart/getCountItem",
    success: function (response) {
      $(".totalItem").text(response);
    },
    error: function (error) {
       WarningAlert("Error");
    },
  });
}

function getQuantityProduct(productId) {
  return new Promise( function (resolve, reject) {
    $.ajax({
      type: 'GET',
      url: '/user/api/cart/getTotalQuantityForProduct',
      data: {
        productId: productId
      },
      success: function (response) {
        resolve(response);
      },
      error: function (error) {
        reject(error);
      }
    });
  });
}
function SuccessAlert(message) {
  var alertBox = document.createElement("div");
  alertBox.id = "customAlert";
  alertBox.className = "success-alert";

  var pipe = document.createElement("div");
  pipe.className = "pipe";

  var alertMessage = document.createElement("p");
  alertMessage.id = "alertMessage";
  alertMessage.innerText = message;
  alertBox.appendChild(pipe);
  alertBox.appendChild(alertMessage);
  document.body.appendChild(alertBox);
  alertBox.style.display = "block";

  setTimeout(function () {
    alertBox.style.display = "none";
  }, 1500);
}

function WarningAlert(message) {
  var alertBox = document.createElement("div");
  alertBox.id = "customAlert";
  alertBox.className = "warning-alert";

  var pipe = document.createElement("div");
  pipe.className = "pipe";

  var alertMessage = document.createElement("p");
  alertMessage.id = "alertMessage";
  alertMessage.innerText = message;
  alertBox.appendChild(pipe);
  alertBox.appendChild(alertMessage);
  document.body.appendChild(alertBox);
  alertBox.style.display = "block";

  setTimeout(function () {
    alertBox.style.display = "none";
  }, 1500);
}
