import org.junit.Before;
import org.junit.Test;
import sample.password;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class Testing {

    password pass;
    ArrayList<password> testList;

    @Before

    public void setup(){
        pass = new password();
        testList = new ArrayList<>();
    }

    @Test
    public void testAddPassword(){
        //check nothing is already in list
        assertEquals(testList.size(), 0);
        assertFalse(testList.contains(new password()));
        //insert a new password with no parameters
        testList.add(new password());
        //check if password object is in list
        assertEquals(testList.size(), 1);
    }

    @Test
    public void testSetPasswordTitle(){
        //check that what default passwordTitle is
        assertEquals(pass.getPasswordTitle(), "");
        //gives/sets a new passwordTitle
        pass.setPasswordTitle("HELLO");
        //check if password title has changed
        assertEquals(pass.getPasswordTitle(), "HELLO");

    }

    @Test
    public void testSetPassword(){
        //check that password is "default"
        assertEquals(pass.getPassword(), "default");
        //gives/sets a new password
        pass.setPassWord("TEST");
        //check if password title has changed
        assertEquals(pass.getPassword(), "TEST");
    }

}
