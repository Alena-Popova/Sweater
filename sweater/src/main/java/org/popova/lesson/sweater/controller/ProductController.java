package org.popova.lesson.sweater.controller;

import org.popova.lesson.sweater.domain.Bag;
import org.popova.lesson.sweater.domain.Message;
import org.popova.lesson.sweater.domain.Product;
import org.popova.lesson.sweater.domain.User;
import org.popova.lesson.sweater.repos.BagRepo;
import org.popova.lesson.sweater.repos.ProductRepo;
import org.popova.lesson.sweater.repos.UserRepo;
import org.popova.lesson.sweater.servise.BagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private BagService bagService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BagRepo bagRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Product> products = productRepo.findAll();

        if (filter != null && !filter.isEmpty()) {
            products = productRepo.findByTag(filter);
        } else {
            products = productRepo.findAll();
        }

        model.addAttribute("products", products);
        model.addAttribute("filter", filter);
        return "products";
    }


    @PostMapping("/sell")
    public String sell(
            @AuthenticationPrincipal User user,
            @Valid Product product,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        product.setAuthor(user);

        if (bindingResult.hasErrors()) {
            model.mergeAttributes(ControllerUtils.getErrors(bindingResult));
            model.addAttribute("products", product);
        } else {
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File upliadDir = new File(uploadPath);
                if (!upliadDir.exists()) {
                    upliadDir.mkdir();
                }
                String uuDir = UUID.randomUUID().toString();
                String resultFilename = uuDir + "." + file.getOriginalFilename();
                file.transferTo(new File(uploadPath + "/" + resultFilename));
                product.setFilename(resultFilename);
            }
            model.addAttribute("products", null);
            productRepo.save(product);
        }
        Iterable<Product> products = productRepo.findAll();
        model.addAttribute("products", products);
        return "redirect:/products";
    }

    /**
     * купить продукт
     *
     * @param currentUser
     * @param model
     * @param idProduct
     * @param quantity
     * @return
     */
    @GetMapping("/{idProduct}")
    public String activate(
            @AuthenticationPrincipal User currentUser,
            Model model,
            @PathVariable String idProduct,
            @RequestParam(value = "quantity", required = false) String quantity) {
        if (!quantity.isEmpty()) {
            Product pies = productRepo.findById(Long.valueOf(idProduct)).get();
            int coutn = Integer.parseInt(quantity);
            bagService.addToBag(currentUser, pies, coutn);
            Iterable<Product> products = productRepo.findAll();
            model.addAttribute("products", products);
            System.out.println(pies.getTitle());
            System.out.printf(quantity);
        }
        return "redirect:/products";
    }


    /**
     * то что пользователь продает
     *
     * @param currentUser
     * @param user
     * @param model
     * @return
     */
    @GetMapping("/mysale/{user}")
    public String userSallesBag(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            Model model
    ) {
        List<Product> products = productRepo.findByAuthor(currentUser);
        model.addAttribute("products", products);
        return "userProductsSelling";
    }

    /**
     * то что покупает
     *
     * @param currentUser
     * @param user
     * @param model
     * @return
     */
    @GetMapping("/mybuy/{user}")
    public String userBuyBag(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            Model model
    ) {
        Bag custumBag = bagRepo.findByAuthor(currentUser);
        if (custumBag == null) {
            Bag bag = new Bag(currentUser);
            bagRepo.save(bag);
            custumBag = bagRepo.findByAuthor(currentUser);
        }
        Set<Product> products = custumBag.getQuantity().keySet();
        Collection<Integer> quantities = custumBag.getQuantity().values();

        int price = 0;
        int index = 0;
        Iterator<Product> iterator = products.iterator();
        for (int i : quantities) {
            if (iterator.hasNext()) {
                price +=  i * Integer.parseInt(iterator.next().getPrice());
            }
        }

        model.addAttribute("products", custumBag.getQuantity());
        model.addAttribute("price", price);
        return "userProductsBuying";
    }
}
