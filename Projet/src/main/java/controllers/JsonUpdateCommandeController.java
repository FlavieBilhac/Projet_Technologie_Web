package controllers;

// Bibliothèque Google GSon
import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.DAO;
import models.DAOException;
import models.DataSourceFactory;

@WebServlet(name = "updateCommande", urlPatterns = {"/updateCommande"}) // L'URL de la Servlet
public class JsonUpdateCommandeController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws models.DAOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, DAOException {

        // Créér le DAO avec sa source de données
        DAO dao = new DAO(DataSourceFactory.getDataSource());

        // On récupère les paramètres saisis par le client
        int newQte = Integer.parseInt(request.getParameter("newQte"));
        int order_num = Integer.parseInt(request.getParameter("order_num"));
        String freight_company = request.getParameter("fc");
        int product_id = Integer.parseInt(request.getParameter("product_id"));
        int quantity = Integer.parseInt(request.getParameter("qte"));
        
        // On définit les autres variables par rapport aux éléments saisis
        float shipping_cost = 400 + 2 * newQte;
        String message;

        // On exécute les requêtes du DAO
        dao.updateCommande(newQte, freight_company, shipping_cost, order_num);
        dao.updateQuantity(newQte - quantity, product_id);
        message = String.format("Commande %s modifiée", order_num);

        Properties resultat = new Properties();
        resultat.put("message", message);

        try (PrintWriter out = response.getWriter()) {
            // On spécifie que la servlet va générer du JSON
            response.setContentType("application/json;charset=UTF-8");
            // Générer du JSON
            Gson gson = new Gson();
            out.println(gson.toJson(resultat));
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (DAOException ex) {
            Logger.getLogger(JsonUpdateCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (DAOException ex) {
            Logger.getLogger(JsonUpdateCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    } // </editor-fold>
}