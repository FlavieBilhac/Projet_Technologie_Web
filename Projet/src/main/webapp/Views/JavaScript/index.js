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

// Fonction qui traite les erreurs de la requête
function showError(xhr, status, message) {
    alert(JSON.parse(xhr.responseText).message);
}