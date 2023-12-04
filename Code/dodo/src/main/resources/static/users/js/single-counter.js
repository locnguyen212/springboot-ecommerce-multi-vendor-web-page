// function updateCountdown() {
//     const now = new Date();
//     const targetDate = new Date("2023-12-31T00:00:00"); // Ngày đếm ngược
//     const timeLeft = targetDate - now;

//     if (timeLeft <= 0) {
//         // Hiển thị thông báo khi đếm ngược kết thúc
//         document.getElementById("countdown").innerHTML = "Đếm ngược đã kết thúc!";
//     } else {
//         // Tính toán ngày, giờ, phút, giây còn lại
//         const days = Math.floor(timeLeft / (1000 * 60 * 60 * 24));
//         const hours = Math.floor((timeLeft % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
//         const minutes = Math.floor((timeLeft % (1000 * 60 * 60)) / (1000 * 60));
//         const seconds = Math.floor((timeLeft % (1000 * 60)) / 1000);

//         // Cập nhật giá trị
//         document.getElementById("days").innerText = days.toString().padStart(2, "0");
//         document.getElementById("hours").innerText = hours.toString().padStart(2, "0");
//         document.getElementById("minutes").innerText = minutes.toString().padStart(2, "0");
//         document.getElementById("seconds").innerText = seconds.toString().padStart(2, "0");
//     }
// }
// setInterval(updateCountdown, 1000);