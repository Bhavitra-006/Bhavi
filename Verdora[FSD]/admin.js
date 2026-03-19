// LOGIN FUNCTION
function login(){
  const user = document.getElementById("username").value;
  const pass = document.getElementById("password").value;

  if(user === "admin" && pass === "1234"){
    localStorage.setItem("adminLoggedIn", true);
    window.location.href = "admin-dashboard.html";
  } else {
    document.getElementById("error").innerText = "Invalid Credentials";
  }
}

// LOGOUT
function logout(){
  localStorage.removeItem("adminLoggedIn");
  window.location.href = "admin-login.html";
}

// CHECK LOGIN
if(window.location.pathname.includes("admin-dashboard")){
  if(!localStorage.getItem("adminLoggedIn")){
    window.location.href = "admin-login.html";
  }
}

// CHARTS
window.onload = function(){

  // ORDERS CHART
  new Chart(document.getElementById("ordersChart"), {
    type: 'bar',
    data: {
      labels: ["Mon","Tue","Wed","Thu","Fri","Sat","Sun"],
      datasets: [{
        label: "Orders",
        data: [12,19,8,15,20,25,18],
        backgroundColor: "#2e7d32"
      }]
    }
  });

  // REVENUE CHART
  new Chart(document.getElementById("revenueChart"), {
    type: 'line',
    data: {
      labels: ["Jan","Feb","Mar","Apr","May","Jun"],
      datasets: [{
        label: "Revenue",
        data: [5000,8000,12000,15000,20000,25000],
        borderColor: "#fbc02d",
        fill:false
      }]
    }
  });

  // USERS PIE CHART
  new Chart(document.getElementById("userChart"), {
    type: 'pie',
    data: {
      labels: ["Users","Farmers"],
      datasets: [{
        data: [85,20],
        backgroundColor: ["#2e7d32","#fbc02d"]
      }]
    }
  });

}