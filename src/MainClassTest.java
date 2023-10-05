import org.junit.Test;


public class MainClassTest {
    MainClass testClass = new MainClass();

    @Test
    public void testGetLocalNumber(){
        if (testClass.getLocalNumber()==14){
            System.out.println("Method getLocalNumber returning 14");
        }
        else {
            System.out.println("Method getLocalNumber not returned 14");
        }
    }
}
