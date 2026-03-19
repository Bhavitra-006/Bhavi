/*function addProduct(){

  const name = document.getElementById("productName").value;
  const qty = document.getElementById("productQty").value;

  if(name === "" || qty === ""){
    alert("Enter product details");
    return;
  }

  const li = document.createElement("li");
  li.textContent = name + " - " + qty + " kg";

  document.getElementById("productList").appendChild(li);

  document.getElementById("productName").value = "";
  document.getElementById("productQty").value = "";
}*/
let totalRevenue = 0;
let totalProducts = 0;
let salesChart;

function addProduct(){

const name = document.getElementById("productName").value;
const yieldQty = parseFloat(document.getElementById("productYield").value);
const price = parseFloat(document.getElementById("productPrice").value);

if(!name || !yieldQty || !price){
alert("Enter product, yield and price");
return;
}

const revenue = yieldQty * price;

totalRevenue += revenue;
totalProducts++;

// Save product in localStorage
let products = JSON.parse(localStorage.getItem("products")) || [];

products.push({
name: name,
yield: yieldQty,
price: price,
revenue: revenue
});

localStorage.setItem("products", JSON.stringify(products));

// Create list item
const li = document.createElement("li");

li.innerHTML =
`${name} | Yield: ${yieldQty}kg | Price: ₹${price}/kg | Revenue: ₹${revenue}
<button onclick="removeProduct(this,'${name}')">Remove</button>`;

document.getElementById("productList").appendChild(li);

// Update totals
document.getElementById("totalRevenue").innerText = totalRevenue;
document.getElementById("products").innerText = totalProducts;
document.getElementById("earnings").innerText = totalRevenue;

// Update graph
updateSalesChart();

// clear inputs
document.getElementById("productName").value="";
document.getElementById("productYield").value="";
document.getElementById("productPrice").value="";
}
function removeProduct(button, name){

let products = JSON.parse(localStorage.getItem("products")) || [];

products = products.filter(p => p.name !== name);

localStorage.setItem("products", JSON.stringify(products));

// remove from UI
button.parentElement.remove();

// recalculate totals
recalculateTotals();

// update graph
updateSalesChart();
}
function recalculateTotals(){

let products = JSON.parse(localStorage.getItem("products")) || [];

let revenue = 0;

products.forEach(p => {
revenue += p.revenue;
});

document.getElementById("products").innerText = products.length;
document.getElementById("earnings").innerText = revenue;
document.getElementById("totalRevenue").innerText = revenue;

totalProducts = products.length;
totalRevenue = revenue;

}

function updateSalesChart(){

const products = JSON.parse(localStorage.getItem("products")) || [];

const labels = products.map(p => p.name);
const revenues = products.map(p => p.revenue);

const ctx = document.getElementById("salesChart");

if(salesChart){
salesChart.destroy();
}

salesChart = new Chart(ctx, {
type: "bar",
data: {
labels: labels,
datasets: [{
label: "Revenue (₹)",
data: revenues,
backgroundColor: "#4CAF50"
}]
},
options: {
responsive: true,
scales: {
y: {
beginAtZero: true
}
}
}
});

}



function uploadImage(){

  const fileInput = document.getElementById("cropImage");

  if(fileInput.files.length === 0){
    alert("Select an image");
    return;
  }

  const img = document.createElement("img");
  img.src = URL.createObjectURL(fileInput.files[0]);
  img.style.width = "120px";
  img.style.margin = "10px";
  img.style.borderRadius = "10px";

  document.getElementById("imageGallery").appendChild(img);
}
function getWeather(){

const city = document.getElementById("cityInput").value;

fetch(`https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=659cf53a9e0b813a0c306f97cac28e1b&units=metric`)
.then(res => res.json())
.then(data => {

document.getElementById("weatherResult").innerHTML =
`Temperature: ${data.main.temp}°C <br>
Humidity: ${data.main.humidity}% <br>
Weather: ${data.weather[0].description}`;

})
.catch(()=>{
alert("City not found");
})

}
/*function listProduct(){

const product = document.getElementById("marketProduct").value;
const price = document.getElementById("marketPrice").value;

if(product=="" || price==""){
alert("Enter product and price");
return;
}

const li = document.createElement("li");

li.innerHTML = `${product} - ₹${price} 
<button onclick="this.parentElement.remove()">Remove</button>`;

document.getElementById("marketList").appendChild(li);

document.getElementById("marketProduct").value="";
document.getElementById("marketPrice").value="";
}*/
function listProduct(){

const product = document.getElementById("marketProduct").value;
const price = parseFloat(document.getElementById("marketPrice").value);

if(product=="" || price==""){
alert("Enter product and price");
return;
}

// Save product in localStorage
let marketProducts = JSON.parse(localStorage.getItem("marketProducts")) || [];

marketProducts.push({
name: product,
price: price
});

localStorage.setItem("marketProducts", JSON.stringify(marketProducts));

// Create list item
const li = document.createElement("li");

li.innerHTML = `${product} - ₹${price} 
<button onclick="removeMarketProduct(this)">Remove</button>`;

document.getElementById("marketList").appendChild(li);

// Update marketplace graph
updateMarketplaceChart();

// Clear inputs
document.getElementById("marketProduct").value="";
document.getElementById("marketPrice").value="";
}
function removeMarketProduct(button){

const li = button.parentElement;
const text = li.firstChild.textContent;

const productName = text.split("-")[0].trim();

let marketProducts = JSON.parse(localStorage.getItem("marketProducts")) || [];

marketProducts = marketProducts.filter(p => p.name !== productName);

localStorage.setItem("marketProducts", JSON.stringify(marketProducts));

li.remove();

updateMarketplaceChart();
}
let marketChart;

function updateMarketplaceChart(){

const marketProducts = JSON.parse(localStorage.getItem("marketProducts")) || [];

const labels = marketProducts.map(p => p.name);
const prices = marketProducts.map(p => p.price);

const ctx = document.getElementById("marketChart");

if(!ctx) return;

if(marketChart){
marketChart.destroy();
}

marketChart = new Chart(ctx,{
type:"bar",
data:{
labels:labels,
datasets:[{
label:"Marketplace Earnings (₹)",
data:prices,
backgroundColor:"#FF9800"
}]
},
options:{
responsive:true,
scales:{
y:{
beginAtZero:true
}
}
}
});

}
function updateGrowth(){

const value = document.getElementById("cropStage").value;

document.getElementById("growthProgress").style.width = value + "%";

let status = "";

if(value==20) status="🌱 Seeds planted Successfully!";
if(value==50) status="🌿 Crop growing well!";
if(value==80) status="🌼 Flowering stage is blooming successfully!";
if(value==100) status="🌾 Ready for harvest!";

document.getElementById("growthStatus").innerText=status;

}
function updateOrders(){

  let orders = document.getElementById("ordersInput").value;

  if(orders === ""){
    alert("Enter number of orders");
    return;
  }

  orders = parseInt(orders);

  // Get latest farmer data
  let farmer = JSON.parse(localStorage.getItem("farmerData")) || {};

  // Update orders
  farmer.orders = orders;

  // Save to storage
  localStorage.setItem("farmerData", JSON.stringify(farmer));

  // Update UI
  document.getElementById("orders").textContent = orders;

  // Clear input
  document.getElementById("ordersInput").value = "";

}
window.addEventListener("load", function(){

let products = JSON.parse(localStorage.getItem("products")) || [];

products.forEach(p => {

const li = document.createElement("li");

li.innerHTML =
`${p.name} | Yield: ${p.yield}kg | Price: ₹${p.price}/kg | Revenue: ₹${p.revenue}
<button onclick="removeProduct(this,'${p.name}')">Remove</button>`;

document.getElementById("productList").appendChild(li);

});

recalculateTotals();
updateSalesChart();

});
window.addEventListener("load", function(){

updateSalesChart();
updateMarketplaceChart();

});
window.onload = function(){
updateSalesChart();
};