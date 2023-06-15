import com.epam.tamentoring.bo.Product;
import com.epam.tamentoring.bo.ShoppingCart;
import com.epam.tamentoring.bo.UserAccount;
import com.epam.tamentoring.exceptions.ProductNotFoundException;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class TestShoppingCart {
    private UserAccount testUser;
    private ShoppingCart testUserShoppingCart;
    private Product testProductKiwi;
    private Product testProductBall;
    private Product controlProductKiwi;

    private Product controlProductBall;
    private List<Product> testProducts = new ArrayList<>();

    private List<Product> expectedProducts = new ArrayList<>();

    private int numberOfProductAdditions =9;

    double expectedPrice = 0;
    @BeforeMethod
    public void CreateUser(){
        testUserShoppingCart = new ShoppingCart(testProducts);
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
    @BeforeMethod
    public void CreateTestProductBall(){
        testProductBall = new Product();
        testProductBall.setName("Ball");
        testProductBall.setId(1);
        testProductBall.setPrice(51);
        testProductBall.setQuantity(4);
    }
    @BeforeTest
    public void CreateControlProductKiwi(){
        controlProductKiwi = new Product();
        controlProductKiwi.setName("Kiwi");
        controlProductKiwi.setId(0);
        controlProductKiwi.setPrice(100.0);
        controlProductKiwi.setQuantity(2);
    }
    @BeforeTest
    public void CreateControlProductBall(){
        controlProductBall = new Product();
        controlProductBall.setName("Ball");
        controlProductBall.setId(1);
        controlProductBall.setPrice(51);
        controlProductBall.setQuantity(4);
    }
    @AfterMethod
    public void ClearObject(){
        testUser = null;
        testProductKiwi = null;
        testProductBall = null;
        testProducts.clear();
        expectedProducts.clear();
    }
    @Test
    public void CheckAddNewProductToCart_andPrice(){
        expectedProducts.add(testProductKiwi);
        testUserShoppingCart.addProductToCart(testProductKiwi);
        Assert.assertEquals(testUserShoppingCart.getProducts(),expectedProducts); /**Check AddProduct*/
        int testProductId = testProductKiwi.getId();
        double expectedPrice = testProductKiwi.getPrice()*testUserShoppingCart.getProductById(testProductId).getQuantity();
        Assert.assertEquals(testUserShoppingCart.getCartTotalPrice(),expectedPrice); /**Check get total price*/
    }
    @Test
    public void CheckAddProductsToCartOneAtTime_andPrice(){
        testUserShoppingCart.addProductToCart(testProductKiwi);
        testUserShoppingCart.addProductToCart(testProductBall);
        expectedProducts.add(testProductKiwi);
        expectedProducts.add(testProductBall);
        Assert.assertEquals(testUserShoppingCart.getProducts(),expectedProducts);
        int testProductIdKiwi = testProductKiwi.getId();
        int testProductIdBall = testProductBall.getId();
        double expectedPrice = testProductKiwi.getPrice()*testUserShoppingCart.getProductById(testProductIdKiwi).getQuantity()+testProductBall.getPrice()*testUserShoppingCart.getProductById(testProductIdBall).getQuantity();
        System.out.println(testProductBall.getQuantity());
        System.out.println(testProductKiwi.getQuantity());
        System.out.println(testUserShoppingCart.getProducts());
        Assert.assertEquals(testUserShoppingCart.getCartTotalPrice(),expectedPrice);
    }
    @Test
    public void CheckAddProductsToCartArray_andPrice(){
        expectedProducts.add(testProductKiwi);
        expectedProducts.add(testProductBall);
        testUserShoppingCart.setProducts(expectedProducts);
        Assert.assertEquals(testUserShoppingCart.getProducts(),expectedProducts);
        int testProductIdKiwi = testProductKiwi.getId();
        int testProductIdBall = testProductBall.getId();
        double expectedPrice = testProductKiwi.getPrice()*testUserShoppingCart.getProductById(testProductIdKiwi).getQuantity()+testProductBall.getPrice()*testUserShoppingCart.getProductById(testProductIdBall).getQuantity();
        Assert.assertEquals(testUserShoppingCart.getCartTotalPrice(),expectedPrice);
    }
    @Test
    public void CheckRemoveProductToCart(){
        testUserShoppingCart.addProductToCart(testProductKiwi);
        testUserShoppingCart.removeProductFromCart(testProductKiwi);
        Assert.assertEquals(testUserShoppingCart.getProducts(),expectedProducts);

    }
    @Test
    public void CheckRemoveAllProductsToCart(){
        testUserShoppingCart.addProductToCart(testProductKiwi);
        testUserShoppingCart.addProductToCart(testProductBall);
        testUserShoppingCart.removeProductFromCart(testProductKiwi);
        testUserShoppingCart.removeProductFromCart(testProductBall);
        Assert.assertEquals(testUserShoppingCart.getProducts(),expectedProducts);
    }
    @Test
    public void CheckRemoveSingleProductsToCart(){
        testUserShoppingCart.addProductToCart(testProductKiwi);
        testUserShoppingCart.addProductToCart(testProductBall);
        testUserShoppingCart.removeProductFromCart(testProductKiwi);
        expectedProducts.add(testProductBall);
        Assert.assertEquals(testUserShoppingCart.getProducts(),expectedProducts);
    }
    @Test
    public void CheckTotalPriceWithExistProductInCart(){
        double quantity = controlProductKiwi.getQuantity();
        testUserShoppingCart.addProductToCart(testProductKiwi);
        for(int i = 0;i<numberOfProductAdditions;i++) {
            testUserShoppingCart.addProductToCart(testProductKiwi);
            quantity += quantity;
            System.out.println(testProductKiwi.getQuantity());
            System.out.println(quantity);
        }
        expectedPrice = controlProductKiwi.getPrice()*quantity;
        Assert.assertEquals(testUserShoppingCart.getCartTotalPrice(),expectedPrice);
    }

}
