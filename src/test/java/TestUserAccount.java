import com.epam.tamentoring.bo.Product;
import com.epam.tamentoring.bo.ShoppingCart;
import com.epam.tamentoring.bo.UserAccount;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestUserAccount {
    private UserAccount testUser;
    private ShoppingCart testUserShoppingCart;
    @BeforeClass
    public void CreateUser(){
        testUser = new UserAccount();
        testUser.setName("Ant");
        testUser.setSurname("On");
        testUser.setDateOfBirth("10/10/10");
        testUser.setShoppingCart(testUserShoppingCart);
    }
    @Test
    public void CheckUserName(){
        Assert.assertEquals(testUser.getName(),"Ant");
    }
    @Test
    public void CheckUserSurname(){
        Assert.assertEquals(testUser.getSurname(),"On");
    }
    @Test
    public void CheckUserDate(){
        Assert.assertEquals(testUser.getDateOfBirth(),"10/10/10");
    }
    @Test
    public void CheckUserToString(){
        Assert.assertEquals(testUser.toString(),"UserAccount(name=Ant, surname=On, dateOfBirth=10/10/10, shoppingCart=null)");
    }
}
