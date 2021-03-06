<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html> 
    <head>
        <title>Statistiques</title>
        <!-- Image d'onglet -->
        <link rel="shortcut icon" href="Views/images/analysis.png">
        <!-- On charge JQuery -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <!-- On charge l'API Google -->
        <script type="text/javascript" src="https://www.google.com/jsapi"></script>
        <!-- On charge le fichier CSS associé -->
        <link rel="stylesheet" href="<c:url value="css/statistiques_admin.css"/>">
        <!-- On charge les polices Google -->
        <link rel="stylesheet" href="<c:url value="https://fonts.googleapis.com/css?family=Oswald:400,500|Roboto:300,400,500,700"/>">
        <script type="text/javascript">
            google.load("visualization", "1", {packages: ["corechart"]});

            // Après le chargement de la page, on fait l'appel AJAX
            google.setOnLoadCallback(doAjax);

            // La fonction permettant de dessiner le diagramme en secteurs
            function drawChart(dataArray) {
                var data = google.visualization.arrayToDataTable(dataArray);
                var options = {
                    is3D: true
                };
                var chart = new google.visualization.PieChart(document.getElementById('piechart'));
                chart.draw(data, options);
            }

           

            // Afficher les ventes par client
            function doAjax() {
                $.ajax({
                    url: "../salesByZone",
                    data: {"datePickerDebut": $("#datePickerDebut").val(), "datePickerFin": $("#datePickerFin").val()},
                    dataType: "json",
                    success: // La fonction qui traite les résultats
                            function (result) {
                                // On reformate le résultat comme un tableau
                                var chartData = [];
                                // On met le descriptif des données
                                chartData.push(["Zone", "Ventes"]);
                                for (var zone in result.records) {
                                    chartData.push([zone, result.records[zone]]);
                                }
                                // On dessine les graphiques
                                drawChart(chartData);
                                
                            },
                    error: showError
                });
            }

            // Fonction qui traite les erreurs de la requête
            function showError(xhr, status, message) {
                alert(JSON.parse(xhr.responseText).message);
            }

        </script>
    </head>
    <body>
        <!-- Le bouton Retour -->
        <input type="button" value="<< RETOUR" onclick="location.href = document.referrer; return false;">
        <!-- Le bouton Déconnexion -->
        <form action="../" method="POST"> <input type='submit' name='action' value='DECONNEXION'></form>
        <h1>Chiffre d'affaires par zone géographique</h1><br/>

         <!-- La période sur laquelle porte la statistique -->
        <p>Date de début : <input type="date" id="datePickerDebut" onchange="doAjax()" value="2011-01-01">
            Date de fin : <input type="date" id="datePickerFin" onchange="doAjax()" value="2019-04-20"></p>

        <!-- Le diagramme en secteurs apparaît ici -->
        <div id="piechart" style="width: 725px; height: 500px;"></div>
       
    </body>
</html>