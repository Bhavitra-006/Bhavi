// Get farmer data
let farmer = JSON.parse(localStorage.getItem("farmerData"));

// If not logged in
if (!farmer) {
  window.location.href = "farm.html";
}

// Initialize values if they don't exist
farmer.products = farmer.products || 0;
farmer.orders = farmer.orders || 0;
farmer.earnings = farmer.earnings || 0;

// Save updated data
localStorage.setItem("farmerData", JSON.stringify(farmer));

// Show farmer info
document.getElementById("name").textContent = farmer.name;
document.getElementById("farm").textContent = farmer.farm;
document.getElementById("location").textContent = farmer.location;

// Show stats
document.getElementById("products").textContent = farmer.products;
document.getElementById("orders").textContent = farmer.orders;
document.getElementById("earnings").textContent = farmer.earnings;


// Logout
function logout() {
  localStorage.removeItem("farmerData");
  window.location.href = "farm.html";
}