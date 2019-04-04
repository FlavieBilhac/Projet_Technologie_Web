$(document).ready(// Exécuté à la fin du chargement de la page
        function () {
            // On montre la liste des produits et des commandes
            showProducts();
            //showOrders();
        }
);

// Montrer la liste des produits disponibles à l'achat
function showProducts() {
    // On fait un appel AJAX pour chercher les produits
    $.ajax({
        url: "listeProduits",
        dataType: "json",
        error: showError,
        success: // La fonction qui traite les résultats
                function (result) {
                    // Le code source du template est dans la page
                    var template = $('#productsTemplate').html();
                    // On combine le template avec le résultat de la requête
                    var processedTemplate = Mustache.to_html(template, result);
                    // On affiche la liste des produits
                    $('#products').html(processedTemplate);
                }
    });
}


function showOrders() {
    // On fait un appel AJAX pour chercher les commandes
    $.ajax({
        url: "listeCommandes",
        dataType: "json",
        error: showError,
        success: // La fonction qui traite les résultats
                function (result) {
                    // Le code source du template est dans la page
                    var template = $('#ordersTemplate').html();
                    // On combine le template avec le résultat de la requête
                    var processedTemplate = Mustache.to_html(template, result);
                    // On affiche la liste des commandes
                    $('#orders').html(processedTemplate);
                    // On vérifie les commandes
                   // checkOrders();
                }
    });
}
// Ajouter une commande
function addCommande(product_id) {
    $.ajax({
        url: "addCommande",
        data: {"product_id": product_id, "qte": $("#qte" + product_id).val(), "fc": $("#fc" + product_id).val()},
        dataType: "json",
        success: // La fonction qui traite les résultats
                function (result) {
                    // MaJ AJAX des produits et commandes
                    showOrders();
                    showProducts();
                },
        error: showError
    });
    return false;
}

function supprCommande(order_num) {
    $.ajax({
        url: "supprCommande",
        data: {"order_num": order_num, "product_id": $("#product_id" + order_num).html(), "qte": $("#qte" + order_num).html()},
        dataType: "json",
        success:
                function (result) {
                    // MaJ AJAX des produits et commandes
                    showOrders();
                    showProducts();
                },
        error: showError
    });
    return false;
}

// Modifier une commande
function updateCommande(order_num, newQte, fc) {
    $.ajax({
        url: "updateCommande",
        data: {"order_num": order_num, "newQte": newQte, "fc": fc, "product_id": $("#product_id" + order_num).html(), "qte": $("#newQte" + order_num).attr('name')},
        dataType: "json",
        success:
                function (result) {
                    // MaJ AJAX des produits et commandes
                    showOrders();
                    showProducts();
                },
        error: showError
    });
    return false;
}

function paramUpdateCommande(order_num) {
    // Si le client décide de modifier une de ses commandes
    if (document.getElementById("btnUpdate" + order_num).innerHTML === '<center><img src="Views/images/refresh.png" style="width: 45%; height: 45%"></center>') {
        // Le bouton devient un bouton de validation pour valider ses changements
        document.getElementById("btnUpdate" + order_num).innerHTML = '<center><img src="Views/images/validation.png" style="width: 45%; height: 45%"></center>';
        let qte = document.getElementById("qte" + order_num).innerHTML;
        let product_id = document.getElementById("product_id" + order_num).innerHTML;
        let qteMax = document.getElementById("qteMax" + product_id).innerHTML;
        // Créer un input pour que le client modifie la quantité du produit commandé
        document.getElementById("qte" + order_num).innerHTML = '<input id="newQte' + order_num + '" class="qte" name="' + qte + '" value="' + qte + '" type="number" step="1" min="1" max="' + qteMax + '">';
        let fc = document.getElementById("fc" + order_num).innerHTML;
        // Permet de modifier la compagnie de transport
        document.getElementById("fc" + order_num).innerHTML =
                '<select class="fc" id="newFc' + order_num + '">\n\
                    <option value="Coastal Freight">Coastal Freight</option>\n\
                    <option value="FR Express">FR Express</option>\n\
                    <option value="Poney Express">Poney Express</option>\n\
                    <option value="Slow Snail">Slow Snail</option>\n\
                    <option value="Southern Delivery Service">Southern Delivery Service</option>\n\
                    <option value="We deliver">We deliver</option>\n\
                    <option value="Western Fast">Western Fast</option>\n\
                </select>';
        document.getElementById("newFc" + order_num).value = fc;
    } 
    // Le client valide son édition
    else {
        // Le bouton de validation redevient un bouton de modification
        document.getElementById("btnUpdate" + order_num).innerHTML = '<center><img src="Views/images/refresh.png" style="width: 45%; height: 45%"></center>';
        // Nouvelle quantité
        let newQte = document.getElementById("newQte" + order_num).value;
        // Nouvelle compagnie de transport
        let newFc = document.getElementById("newFc" + order_num).value;
        // Exécute la fonction permettant la MaJ de la commande
        updateCommande(order_num, newQte, newFc);
    }
}


// Fonction qui traite les erreurs de la requête
function showError(xhr, status, message) {
    alert(JSON.parse(xhr.responseText).message);
}