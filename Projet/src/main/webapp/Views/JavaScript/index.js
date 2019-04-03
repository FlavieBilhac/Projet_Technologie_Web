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


// Fonction qui traite les erreurs de la requête
function showError(xhr, status, message) {
    alert(JSON.parse(xhr.responseText).message);
}