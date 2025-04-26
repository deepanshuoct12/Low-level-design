import org.Driver;
import org.ecommerce.constants.UserRole;
import org.ecommerce.controller.ProductController;
import org.ecommerce.controller.ShoppingCartController;
import org.ecommerce.controller.UserController;
import org.ecommerce.model.Product;
import org.ecommerce.model.User;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class TestLld {
    private static Driver driver = new Driver();

    @Test
    public void elevatorSystemTest() {
        System.out.println("---------------------------------------------------------------- MY ELEVATOR SYSTEM ----------------------------------------------------------------");
        driver.elevatorSystemDemo();
    }

    @Test
    public void lruCacheTest() {
        System.out.println("---------------------------------------------------------------- MY LRU CACHE ----------------------------------------------------------------");
        driver.lruCache();
    }


    @Test
    public void eCommerce() {
        System.out.println("---------------------------------------------------------------- MY ECOMMERCE SITE ----------------------------------------------------------------");
        ProductController productController = new ProductController();
        ShoppingCartController shoppingCartController = new ShoppingCartController();
        UserController userController = new UserController();


        Product product1 = new Product("Garnier", "Face wash", 100);
        Product product2 = new Product("Fair", "Face Cream", 200);
        Product product3 = new Product("Beardo", "Oil", 150);
        Product product4 = new Product("Locket", "Casual wear", 300);

        List<Product> productList = List.of(product1, product2, product3, product4);
        productController.addProduct(productList);
        List<String> productIds = productController.get().stream().map(Product::getId).collect(Collectors.toList());

        User user = userController.createUser("Abhi", "abhidude2543@gmail.com", UserRole.BUYER.name());
        shoppingCartController.addToCart(user.getId(), productIds);
        shoppingCartController.purchase(user.getId(), 1000);
    }

    public void upiSystem() {
        System.out.println("---------------------------------------------------------------- MY UPI SYSTEM ----------------------------------------------------------------");
        driver.upiSystem();
    }
}
