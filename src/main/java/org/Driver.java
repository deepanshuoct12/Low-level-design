package org;


import org.ecommerce.constants.UserRole;
import org.ecommerce.controller.ProductController;
import org.ecommerce.controller.ShoppingCartController;
import org.ecommerce.controller.UserController;
import org.ecommerce.dao.ProductDao;
import org.ecommerce.model.Product;
import org.ecommerce.model.User;
import org.elevator.controller.ElevatorController;
import org.hashMapImplementation.MyHashMap;
import org.lrucache.LRUCache;
import org.snakeAndLadder.SnakeAndLadderGame;
import org.ticTacToe.TicTacToeGame;
import org.upiSystem.demo.UPIDriver;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class Driver {
    private TicTacToeGame ticTacToeGame = new TicTacToeGame();
    private SnakeAndLadderGame snakeAndLadderGame = new SnakeAndLadderGame();
    AtomicLong atomicLong;
    private UPIDriver upiDriver = new UPIDriver();

    public void ticTacToeStart() {
        ticTacToeGame.initializeGame();
        String result = ticTacToeGame.startGame();
        System.out.println("Result is  : " +  result);
        atomicLong.incrementAndGet();
    }

    public void snakeAndLadderStart() {
        snakeAndLadderGame.initGame(10,4,3);
        snakeAndLadderGame.startGame();
    }

    public void hashMap() {
        System.out.println("---------------------------------------------------------------- MY PERSONAL HASHMAP ----------------------------------------------------------------");
        MyHashMap<Integer, String> myHashMap = new MyHashMap<>();
        myHashMap.put(1, "abc");
        myHashMap.put(2, "def");
        myHashMap.put(3, "ghi");
        myHashMap.put(2, "jkl");
        myHashMap.put(4, "mno");
        myHashMap.put(5, "pqr");

        System.out.println(myHashMap.get(2));
    }

    public void elevatorSystemDemo() {
        System.out.println("---------------------------------------------------------------- MY ELEVATOR SYSTEM ----------------------------------------------------------------");
        ElevatorController elevatorController = new ElevatorController(3, 5);
        elevatorController.requestElevator(5, 10);
        elevatorController.requestElevator(3, 7);
        elevatorController.requestElevator(8, 2);
        elevatorController.requestElevator(1, 9);
    }

    public void lruCache() {
        System.out.println("---------------------------------------------------------------- MY LRU CACHE ----------------------------------------------------------------");

        LRUCache<Integer, String> cache = new LRUCache<>(3);

        cache.put(1, "Value 1");
        cache.put(2, "Value 2");
        cache.put(3, "Value 3");

        System.out.println(cache.get(1)); // Output: Value 1
        System.out.println(cache.get(2)); // Output: Value 2

        cache.put(4, "Value 4");

        System.out.println(cache.get(3)); // Output: null
        System.out.println(cache.get(4)); // Output: Value 4

        cache.put(2, "Updated Value 2");

        System.out.println(cache.get(1)); // Output: Value 1
        System.out.println(cache.get(2)); // Output: Updated Value 2
    }

    public void eCommerce() {
        System.out.println("---------------------------------------------------------------- MY ECOMMERCE SITE ----------------------------------------------------------------");
        ProductController productController = new ProductController();
        ShoppingCartController shoppingCartController = new ShoppingCartController();
        UserController userController = new UserController();


        Product product1 = new Product("Garnier", "Face wash", 100);
        Product product2 = new Product("Fair", "Face Cream", 200);
        Product product3 = new Product("Beardo", "Oil", 150);
        Product product4 = new Product("Locket", "Casual wear", 300);

        List <Product> productList = List.of(product1, product2, product3, product4);
        productController.addProduct(productList);
        List<String> productIds = productController.get().stream().map(Product::getId).collect(Collectors.toList());

        User user = userController.createUser("Abhi", "abhidude2543@gmail.com", UserRole.BUYER.name());
        shoppingCartController.addToCart(user.getId(), productIds);
        shoppingCartController.purchase(user.getId(), 100);
    }

    public void upiSystem() {
        upiDriver.startExecution();
    }
}
