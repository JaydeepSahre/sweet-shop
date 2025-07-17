// src/main/resources/static/app.js
const apiUrl = '/sweets';
let allSweets = []; // Global storage for all sweets

// Run after DOM loads
document.addEventListener('DOMContentLoaded', () => {
  fetchSweets();

  // Add Sweet Form Submission
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

 // Listen for input in the search bar
document.getElementById('searchInput').addEventListener('input', (e) => {
  const query = e.target.value.toLowerCase();
  const filtered = allSweets.filter(sweet =>
    sweet.name.toLowerCase().includes(query) ||
    sweet.category.toLowerCase().includes(query) // 🔍 Include category in search
  );
  renderTable(filtered);
});


  // Sorting Dropdown
  document.getElementById('sortBy').addEventListener('change', applySorting);
});

// Fetch all sweets
async function fetchSweets() {
  const response = await fetch(apiUrl);
  allSweets = await response.json();
  renderTable(allSweets);
}

// Render sweets in the table
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
        <button class="btn btn-success btn-sm" onclick="restockSweet(${s.id})">Restock</button>
      </td>
    `;
    tbody.appendChild(row);
  });
}

// Sort sweets
function applySorting() {
  const sortBy = document.getElementById('sortBy').value;
  const sorted = [...allSweets];

  switch (sortBy) {
    case 'price-asc':
      sorted.sort((a, b) => a.price - b.price);
      break;
    case 'price-desc':
      sorted.sort((a, b) => b.price - a.price);
      break;
    case 'quantity-asc':
      sorted.sort((a, b) => a.quantity - b.quantity);
      break;
    case 'quantity-desc':
      sorted.sort((a, b) => b.quantity - a.quantity);
      break;
  }

  renderTable(sorted);
}

// Delete a sweet
async function deleteSweet(id) {
  await fetch(`${apiUrl}/${id}`, { method: 'DELETE' });
  fetchSweets();
}

// Purchase a sweet
async function purchaseSweet(id) {
  const quantity = prompt("Enter quantity to purchase:");
  if (quantity && !isNaN(quantity)) {
    await fetch(`${apiUrl}/${id}/purchase?quantity=${quantity}`, {
      method: 'PUT'
    });
    fetchSweets();
  }
}

// Restock a sweet
async function restockSweet(id) {
  const quantity = prompt("Enter quantity to restock:");
  if (quantity && !isNaN(quantity)) {
    await fetch(`${apiUrl}/${id}/restock?quantity=${quantity}`, {
      method: 'PUT'
    });
    fetchSweets(); // Refresh table
  }
}

