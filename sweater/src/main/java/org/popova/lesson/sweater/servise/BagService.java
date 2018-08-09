package org.popova.lesson.sweater.servise;

import org.popova.lesson.sweater.domain.Bag;
import org.popova.lesson.sweater.domain.Product;
import org.popova.lesson.sweater.domain.User;
import org.popova.lesson.sweater.repos.BagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BagService {

    @Autowired
    private BagRepo bagRepo;

    public void addToBag(User user, Product product, int numbers) {
        List<Bag> custumBag = bagRepo.findByAuthor(user);
        if (custumBag.size() == 0) {
            Bag bag = new Bag(user);
            bagRepo.save(bag);
            custumBag = bagRepo.findByAuthor(user);
        }
        Bag bagNow = custumBag.get(0);
        while (numbers > 0) {
            bagNow.getProductsForBuy().add(product);
            numbers--;
        }
        bagRepo.save(bagNow);
    }

    public void deleteInBag(User user, Product product, int numbers) {
        Bag bag = user.getBags().iterator().next();
        List<Product> products = bag.getProductsForBuy();

        while (numbers > 0 && products.contains(product)) {
            products.remove(product);
            numbers--;
        }
        bagRepo.save(bag);
    }

    public void deleteBag(User user) {
        Bag bag = user.getBags().iterator().next();
        List<Product> products = bag.getProductsForBuy();
        products.clear();
        bagRepo.save(bag);
    }

}
