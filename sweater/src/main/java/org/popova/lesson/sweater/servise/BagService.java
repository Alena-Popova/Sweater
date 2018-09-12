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
        if (!checkBag(user)) {
            addUserBag(user);
            System.out.println("add userbag");
        }
        Bag bagNow = bagRepo.findByAuthor(user);

        if (bagNow.getQuantity().keySet().contains(product)) {
            numbers  += bagNow.getQuantity().get(product);
        }

        bagNow.getQuantity().put(product, numbers);
        bagRepo.save(bagNow);
    }

    public void deleteInBag(User user, Product product, int numbers) {
        if (checkBag(user)) {
            Bag bag = bagRepo.findByAuthor(user);
            int preNum = bag.getQuantity().get(product);

            Map<Product, Integer> products = bag.getQuantity();
            if (products.containsKey(product)) {
                if (preNum - numbers < 0) {
                    bag.getQuantity().remove(product);
                } else {
                    bag.getQuantity().put(product, preNum - numbers);
                }
            }
            bagRepo.save(bag);
        }
    }

    public void deleteBag(User user) {
        if (checkBag(user)) {
            Bag bag = user.getBag();
            bag.getQuantity().clear();
            bagRepo.save(bag);
        }
    }

    private boolean checkBag(User user) {
        return bagRepo.findByAuthor(user) != null;
    }

    private void addUserBag(User user) {
        Bag bag = new Bag(user);
        bagRepo.save(bag);
    }

}
