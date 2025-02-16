package app.controller;

import app.dto.OrderDtoRequest;
import app.entity.Order;
import app.entity.Product;
import app.repository.ProductRepository;
import app.service.OrderService;
import app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService service;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/orders")
    public String fetchAllUsers(Model model) {
        List<Order> list = service.fetchAll();
        model.addAttribute("title", "Orders");
        if (list.isEmpty()) {
            model.addAttribute("ordersInfo", "No Orders yet :(");
        } else {
            model.addAttribute("orders", list);
        }
        model.addAttribute("fragmentName", "order-list");
        return "layout";
    }

    @RequestMapping("/create-order")
    public String createUser(Model model) {
        model.addAttribute("title", "Add Order");
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("fragmentName", "order-add");
        return "layout";
    }

    @RequestMapping(value = "/add-order", method = RequestMethod.POST)
    public RedirectView addOrder(@RequestParam("creationDate") String creationDate,
                                 @RequestParam("totalCost") double totalCost,
                                 @RequestParam("products") List<Long> productIds) {
        LocalDate date = LocalDate.parse(creationDate);

        List<Product> products = productService.findProductsByIds(productIds);
        OrderDtoRequest request = new OrderDtoRequest(null, date, totalCost, products);
        RedirectView redirectView = new RedirectView("./orders");

        if (service.create(request)) {
            return redirectView;
        }

        return redirectView;
    }



    @RequestMapping("/update-order/{id}")
    public String updateUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("title", "Update Order");
        Order order = service.fetchById(id);
        model.addAttribute("order", order);
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("fragmentName", "order-update");
        return "layout";
    }

    @RequestMapping(value = "/change-order", method = RequestMethod.POST)
    public RedirectView changeUser(@RequestParam("id") Long id,
                                   @RequestParam("creationDate") String creationDate,
                                   @RequestParam("totalCost") double totalCost,
                                   @RequestParam("products") List<Long> productIds) {
        LocalDate date = LocalDate.parse(creationDate);
        List<Product> products = productService.findProductsByIds(productIds);
        OrderDtoRequest request = new OrderDtoRequest(id, date, totalCost, products);

        RedirectView redirectView = new RedirectView("./orders");
        if (service.update(request.id(), request))
            return redirectView;
        else return redirectView;
    }

    @RequestMapping("/delete-order/{id}")
    public RedirectView deleteUser(@PathVariable("id") Long id) {
        RedirectView redirectView = new RedirectView("../orders");
        if (service.delete(id)) return redirectView;
        else return redirectView;
    }
}
