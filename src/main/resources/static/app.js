// src/main/resources/static/app.js
const apiUrl = '/sweets';
let allSweets = []; // To store all sweets from backend

document.addEventListener('DOMContentLoaded', () => {
  fetchSweets();

  document.getElementById('sweetForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const sweet = {
      id: parseInt(document.getElementById('id').value),
      name: document.getElementById('name').value,
      category: document.getElementById('category').value,
      quantity: parseInt(document.getElementById('quantity').value),
      price: parseFloat(document.getElementById('price').value)
    };

    await fetch(apiUrl, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(sweet)
    });

    e.target.reset();
    fetchSweets();
  });

  // Live search listener
  function applySearchFilters() {
    const query = document.getElementById('searchInput').value.toLowerCase();
    const minPrice = parseFloat(document.getElementById('minPrice').value);
    const maxPrice = parseFloat(document.getElementById('maxPrice').value);

    const filtered = allSweets.filter(sweet => {
      const matchesNameOrCategory =
        sweet.name.toLowerCase().includes(query) ||
        sweet.category.toLowerCase().includes(query);

      const matchesMin = isNaN(minPrice) || sweet.price >= minPrice;
      const matchesMax = isNaN(maxPrice) || sweet.price <= maxPrice;

      return matchesNameOrCategory && matchesMin && matchesMax;
    });

    renderTable(filtered);
  }


async function fetchSweets() {
  const response = await fetch(apiUrl);
  allSweets = await response.json();
  renderTable(allSweets);
}

function renderTable(sweets) {
  const tbody = document.getElementById('sweetTableBody');
  tbody.innerHTML = '';

  sweets.forEach(s => {
    const row = document.createElement('tr');
    row.innerHTML = `
      <td>${s.id}</td><td>${s.name}</td><td>${s.category}</td>
      <td>${s.price}</td><td>${s.quantity}</td>
      <td>
        <button class="btn btn-danger btn-sm" onclick="deleteSweet(${s.id})">Delete</button>
        <button class="btn btn-warning btn-sm" onclick="purchaseSweet(${s.id})">Purchase</button>
      </td>
    `;
    tbody.appendChild(row);
  });
}

async function deleteSweet(id) {
  await fetch(`${apiUrl}/${id}`, { method: 'DELETE' });
  fetchSweets();
}

async function purchaseSweet(id) {
  const quantity = prompt("Enter quantity to purchase:");
  if (quantity && !isNaN(quantity)) {
    await fetch(`${apiUrl}/${id}/purchase?quantity=${quantity}`, {
      method: 'PUT'
    });
    fetchSweets();
  }
}
