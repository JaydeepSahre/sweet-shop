// src/main/resources/static/app.js
const apiUrl = '/sweets';

document.addEventListener('DOMContentLoaded', fetchSweets);

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

async function fetchSweets() {
  const response = await fetch(apiUrl);
  const sweets = await response.json();
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
