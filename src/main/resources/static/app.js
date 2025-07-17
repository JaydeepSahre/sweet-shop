const api = "http://localhost:8080/sweets";

function fetchSweets() {
    fetch(api)
        .then(res => res.json())
        .then(data => {
            const tbody = document.querySelector("#sweetsTable tbody");
            tbody.innerHTML = "";
            data.forEach(sweet => {
                const row = `<tr>
                    <td>${sweet.id}</td>
                    <td>${sweet.name}</td>
                    <td>${sweet.category}</td>
                    <td>${sweet.quantity}</td>
                    <td>${sweet.price}</td>
                    <td>
                        <button onclick="deleteSweet(${sweet.id})">Delete</button>
                        <button onclick="purchaseSweet(${sweet.id})">Purchase</button>
                    </td>
                </tr>`;
                tbody.innerHTML += row;
            });
        });
}

document.getElementById("addSweetForm").addEventListener("submit", function(e) {
    e.preventDefault();
    const sweet = {
        id: document.getElementById("id").value,
        name: document.getElementById("name").value,
        category: document.getElementById("category").value,
        quantity: document.getElementById("quantity").value,
        price: document.getElementById("price").value
    };

    fetch(api, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(sweet)
    }).then(() => {
        fetchSweets();
        this.reset();
    });
});

function deleteSweet(id) {
    fetch(`${api}/${id}`, { method: "DELETE" }).then(fetchSweets);
}

function purchaseSweet(id) {
    const qty = prompt("Enter quantity to purchase:");
    fetch(`${api}/${id}/purchase?quantity=${qty}`, {
        method: "PUT"
    }).then(fetchSweets);
}
