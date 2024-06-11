//CLIENTS
function deleteClient(id) {
    if (confirm('Etes vous sur de supprimer ce client?')) {
        fetch(`/api/clients/${id}`, {
            method: 'DELETE'
        })
            .then(response => {
                if (response.ok) {
                    window.location.reload();
                } else {
                    alert('Echec de suppression du client');
                }
            });
    }
}

function showEditClientModal(button) {
    const id = button.getAttribute('data-id');
    const nom = button.getAttribute('data-nom');
    const adresse = button.getAttribute('data-adresse');
    const contact = button.getAttribute('data-contact');

    document.getElementById('editClientId').value = id;
    document.getElementById('editClientNom').value = nom;
    document.getElementById('editClientAdresse').value = adresse;
    document.getElementById('editClientContact').value = contact;
    $('#editClientModal').modal('show');
}

document.getElementById('addClientForm').addEventListener('submit', function(event) {
    event.preventDefault();
    const formData = new FormData(this);
    fetch('/api/clients', {
        method: 'POST',
        body: JSON.stringify(Object.fromEntries(formData)),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(response => {
        if (response.ok) {
            window.location.reload();
        } else {
            alert('Echec de l\'ajout du client');
        }
    });
});

//PRODUCTS

// Products
function deleteProduct(id) {
    if (confirm('Etes vous sur de supprimer ce produit?')) {
        fetch(`/api/produits/${id}`, {
            method: 'DELETE'
        })
            .then(response => {
                if (response.ok) {
                    window.location.reload();
                } else {
                    alert('Echec de suppression du produit');
                }
            });
    }
}

function showEditProductModal(button) {
    const id = button.getAttribute('data-id');
    const nom = button.getAttribute('data-nom');
    const quantite  = button.getAttribute('data-quantite');
    const numeroLot = button.getAttribute('data-numeroLot');
    const prix = button.getAttribute('data-prix');

    document.getElementById('editProductId').value = id;
    document.getElementById('editProductNom').value = nom;
    document.getElementById('editProductQuantite').value = quantite;
    document.getElementById('editProductNumeroLot').value = numeroLot;
    document.getElementById('editProductPrix').value = prix;
    $('#editProductModal').modal('show');
}

document.getElementById('addProductForm').addEventListener('submit', function(event) {
    event.preventDefault();
    const formData = new FormData(this);
    fetch('/api/produits', {
        method: 'POST',
        body: JSON.stringify(Object.fromEntries(formData)),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(response => {
        if (response.ok) {
            window.location.reload();
        } else {
            alert('Echec de l\'ajout du produit');
        }
    });
});


//SUPPLIERS


function deleteSuppliers(id) {
    if (confirm('Etes vous sur de supprimer ce fournisseur ?')) {
        fetch(`/api/fournisseurs/${id}`, {
            method: 'DELETE'
        })
            .then(response => {
                if (response.ok) {
                    window.location.reload();
                } else {
                    alert('Echec de suppression du fournisseur !');
                }
            });
    }
}

function showEditSupplierModal(button) {
    const id = button.getAttribute('data-id');
    const nom = button.getAttribute('data-nom');
    const adresse = button.getAttribute('data-adresse');
    const contact = button.getAttribute('data-contact');

    document.getElementById('editSupplierId').value = id;
    document.getElementById('editSupplierNom').value = nom;
    document.getElementById('editSupplierAdresse').value = adresse;
    document.getElementById('editSupplierContact').value = contact;
    $('#editSupplierModal').modal('show');
}

document.getElementById('addSupplierForm').addEventListener('submit', function(event) {
    event.preventDefault();
    const formData = new FormData(this);
    fetch('/api/fournisseurs', {
        method: 'POST',
        body: JSON.stringify(Object.fromEntries(formData)),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(response => {
        if (response.ok) {
            window.location.reload();
        } else {
            alert('Echec de l\'ajout du fournisseur');
        }
    });
});

document.getElementById('editSupplierForm').addEventListener('submit', function(event) {
    event.preventDefault();
    const formData = new FormData(this);
    fetch('/api/fournisseurs/' + document.getElementById('editSupplierId').value, {
        method: 'PUT',
        body: JSON.stringify(Object.fromEntries(formData)),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(response => {
        if (response.ok) {
            window.location.reload();
        } else {
            alert('Echec de la modification du fournisseur');
        }
    });
});