package app.controller;

import app.dto.OrderDtoRequest;
import app.entity.Order;
import app.repository.ProductRepository;
import app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService service;
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
    public RedirectView addOrder(@ModelAttribute  OrderDtoRequest request) {
        RedirectView redirectView = new RedirectView("./orders");

        if (service.create(request)) {
            return redirectView;
        } else {
            return redirectView;
        }
    }

    @RequestMapping("/update-order/{id}")
    public String updateUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("title", "Update Order");
        Order order = service.fetchById(id);
        model.addAttribute("order", order);
        model.addAttribute("fragmentName", "order-update");
        return "layout";
    }

    @RequestMapping(value = "/change-order", method = RequestMethod.POST)
    public RedirectView changeUser(@ModelAttribute OrderDtoRequest request) {
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
