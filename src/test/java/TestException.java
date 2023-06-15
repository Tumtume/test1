import com.epam.tamentoring.bo.Product;
import com.epam.tamentoring.bo.ShoppingCart;
import com.epam.tamentoring.bo.UserAccount;
import com.epam.tamentoring.exceptions.ProductNotFoundException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestException {
    private UserAccount testUser;
    private Product testProductKiwi;
    private List<Product> testProducts = new ArrayList<>();
    private ShoppingCart testUserShoppingCart = new ShoppingCart(testProducts);

    @BeforeMethod
    public void CreateUser(){
        testUser = new UserAccount();
        testUser.setName("Ant");
        testUser.setSurname("On");
        testUser.setDateOfBirth("10/10/10");
        testUser.setShoppingCart(testUserShoppingCart);
    }
    @BeforeMethod
    public void CreateTestProductKiwi(){
        testProductKiwi = new Product();
        testProductKiwi.setName("Kiwi");
        testProductKiwi.setId(0);
        testProductKiwi.setPrice(100.0);
        testProductKiwi.setQuantity(2);
    }
 @Test(expectedExceptions = {ProductNotFoundException.class})
    public void CheckExceptionMessage() throws Exception {
        String message = "Product is not found in the cart: " + testProductKiwi.toString();
        throw new ProductNotFoundException(message);
 }
 @Test(expectedExceptions = {ProductNotFoundException.class})
    public void CheckExceptionShoppingCartTest() {
     testUserShoppingCart.removeProductFromCart(testProductKiwi);
 }
}
