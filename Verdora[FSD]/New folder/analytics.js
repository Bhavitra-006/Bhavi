const ctx = document.getElementById('salesChart');

if(ctx){

new Chart(ctx, {
  type: 'line',
  data: {
    labels: ["Mon","Tue","Wed","Thu","Fri","Sat","Sun"],
    datasets: [{
      label: "Weekly Earnings (₹)",
      data: [500,900,700,1200,950,1500,1800],
      borderWidth: 3
    }]
  },
  options: {
    responsive: true
  }
});

}