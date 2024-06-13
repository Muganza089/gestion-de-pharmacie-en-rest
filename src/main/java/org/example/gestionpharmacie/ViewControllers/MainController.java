package org.example.gestionpharmacie.ViewControllers;

import org.example.gestionpharmacie.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    private final ProduitService produitService;
    private final ClientService clientService;
    private final StockService stockService;
    private final CommandeService commandeService;
    private final FournisseurService fournisseurService;
    private final VenteService venteService;

    public MainController(ProduitService produitService, ClientService clientService, StockService stockService, CommandeService commandeService, FournisseurService fournisseurService, VenteService venteService) {
        this.produitService = produitService;
        this.clientService = clientService;
        this.stockService = stockService;
        this.commandeService = commandeService;
        this.fournisseurService = fournisseurService;
        this.venteService = venteService;
    }

    @GetMapping("products")
    public String showProductsPage(Model model) {
        model.addAttribute("products", produitService.getAllProduits());
        model.addAttribute("path", "/products");
        return "index";
    }

    @GetMapping("clients")
    public String showClientsPage(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("path", "/clients");
        return "index";
    }

    @GetMapping("stocks")
    public String showStocksPage(Model model) {
        model.addAttribute("stocks", stockService.getAllStocks());
        model.addAttribute("path", "/stocks");
        return "index";
    }

    @GetMapping("commands")
    public String showOrdersPage(Model model) {
        model.addAttribute("commands", commandeService.getAllCommandes());
        model.addAttribute("suppliers", fournisseurService.getAllFournisseurs());
        model.addAttribute("products", produitService.getAllProduits());
        model.addAttribute("path", "/commands");
        return "index";
    }

    @GetMapping("suppliers")
    public String showSuppliersPage(Model model) {
        model.addAttribute("suppliers", fournisseurService.getAllFournisseurs());
        model.addAttribute("path", "/suppliers");
        return "index";
    }

    @GetMapping("sales")
    public String showSalesPage(Model model) {
        model.addAttribute("sales", venteService.getAllVentes());
        model.addAttribute("path", "/sales");
        return "index";
    }

    @GetMapping
    public String showHomePage(Model model) {
        model.addAttribute("path", "/");
        return "index";
    }
}
