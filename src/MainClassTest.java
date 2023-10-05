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

    @Test
    public void testGetClassNumber(){
        if (testClass.getClassNumber()>45){
            System.out.println("\nMethod getClassNumber returning more then 45");
        }
        else {
            System.out.println("\nMethod getClassNumber return less then 45");
            System.out.println("getClassNumber return: " + testClass.getClassNumber());
        }
    }
}
