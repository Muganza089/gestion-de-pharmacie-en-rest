package org.example.gestionpharmacie.ViewControllers;

import org.example.gestionpharmacie.models.Article;
import org.example.gestionpharmacie.models.Commande;
import org.example.gestionpharmacie.models.Produit;
import org.example.gestionpharmacie.models.Stock;
import org.example.gestionpharmacie.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    private final ProduitService produitService;
    private final ClientService clientService;
    private final StockService stockService;
    private final CommandeService commandeService;
    private final FournisseurService fournisseurService;
    private final VenteService venteService;
    private final ArticleService articleService;
    private final List<Article> listeArticles = new ArrayList<>();

    public MainController(ProduitService produitService, ClientService clientService, StockService stockService, CommandeService commandeService, FournisseurService fournisseurService, VenteService venteService, ArticleService articleService) {
        this.produitService = produitService;
        this.clientService = clientService;
        this.stockService = stockService;
        this.commandeService = commandeService;
        this.fournisseurService = fournisseurService;
        this.venteService = venteService;
        this.articleService = articleService;

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
        model.addAttribute("produits",produitService.getAllProduits());
        model.addAttribute("path", "/stocks");
        return "index";
    }

    @GetMapping("commands")
    public String showOrdersPage(Model model) {
        model.addAttribute("commands", commandeService.getAllCommandes());
        model.addAttribute("suppliers", fournisseurService.getAllFournisseurs());
        model.addAttribute("articles", listeArticles);
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

    @PostMapping("/submitStock")
    public String submitStock(@Validated Stock stock, BindingResult result, Model model){


        stockService.saveStock(stock);
        System.out.println(stock.getProduits());

        if(result.hasErrors()) {
            return "index";
        }
        return "index";
    }


    @PostMapping("/submitCommand")
    public String submitCommand(@Validated Commande commande, BindingResult result, Model model){



        System.out.println("Date : "+commande.getDateCommande());
        System.out.println("Fournisseur : "+commande.getFournisseur());
        commande.setArticles(listeArticles);
        System.out.println(commande.getArticles());
        commandeService.saveCommande(commande);
        //System.out.println(commande);
        //System.out.println(commandeService.getAllCommandes());
        if(result.hasErrors()) {
            return "index";
        }
        return "index";
    }
    @PostMapping("/addArticle")
    public String addArticle(@Validated Article article, BindingResult result, Model model){
        listeArticles.add(article);
         if(result.hasErrors()) {
            return "index";
        }
        //model.addAttribute("articles", listeArticles);
        return "redirect:/commands";
    }
    @PostMapping("/addProduitToStock")
    public String addToStock(@Validated Stock stock, BindingResult result, Model model){

        if(result.hasErrors()) {
            return "index";
        }

        for (Produit produit : stock.getProduits()) {
            produit.setStock(stock);
        }


        Stock savedStock = stockService.saveStock(stock);

        List<Stock> allStocks = stockService.getAllStocks();
        model.addAttribute("stocks", allStocks);



        System.out.println(" tous les stock "+stockService.getAllStocks());
//        System.out.println("ID STOCK : "+stock1.getId());
//        System.out.println("PRODUITS EN STOCK : "+stock1.getProduits());

       // model.addAttribute("stocks", stockService.getAllStocks());
        //model.addAttribute("articles", listeArticles);
        return "redirect:/stocks";
    }



}
