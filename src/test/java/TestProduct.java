import com.epam.tamentoring.bo.Product;
import com.epam.tamentoring.bo.ShoppingCart;
import com.epam.tamentoring.bo.UserAccount;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class TestProduct {
    private Product testProductKiwi;

    @BeforeClass
    public void CreateTestProductKiwi(){
        testProductKiwi = new Product();
        testProductKiwi.setName("Kiwi");
        testProductKiwi.setId(0);
        testProductKiwi.setPrice(100.0);
        testProductKiwi.setQuantity(2);
    }

    @Test
    public void CheckNameProduct(){
        Assert.assertEquals(testProductKiwi.getName(),"Kiwi");
    }
    @Test
    public void CheckIdProduct(){
        Assert.assertEquals(testProductKiwi.getId(),0);
    }
    @Test
    public void CheckPriceProduct(){
        Assert.assertEquals(testProductKiwi.getPrice(),100);
    }
    @Test
    public void CheckQuantityProduct(){
        Assert.assertEquals(testProductKiwi.getQuantity(),2);
    }
    @Test
    public void CheckProductKiwiToString(){
        Assert.assertEquals(testProductKiwi.toString(),"Product(id=0, name=Kiwi, price=100.0, quantity=2.0)");
    }
}
