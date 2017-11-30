package mockito.mockito2;
//http://www.vogella.com/tutorials/Mockito/article.html

import org.junit.Test;
import org.mockito.Mockito;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;
import static org.testng.FileAssert.fail;

public class MockitoTestMock {


    @Test
    public void test1() {
        //  create mock
        MyClass test = mock(MyClass.class);

        // define return value for method getUniqueId()
        when(test.getUniqueId()).thenReturn(44);

        // use mock in test....
        assertEquals(test.getUniqueId(), 44);
    }


    // demonstrates the return of multiple values
    @Test
    public void testMoreThanOneReturnValue() {
        Iterator<String> i = mock(Iterator.class);
        when(i.next()).thenReturn("Mockito").thenReturn("rocks");
        String result = i.next() + " " + i.next();
        //assert
        assertEquals("Mockito rocks", result);
    }

    // this test demonstrates how to return values based on the input
    @Test
    public void testReturnValueDependentOnMethodParameter()  {
        Comparable<String> c= mock(Comparable.class);
        when(c.compareTo("Mockito")).thenReturn(1);
        when(c.compareTo("Eclipse")).thenReturn(2);
        //assert
        assertEquals(1, c.compareTo("Mockito"));
    }

    // this test demonstrates how to return values independent of the input value
    @Test
    public void testReturnValueInDependentOnMethodParameter()  {
        Comparable<Integer> c= mock(Comparable.class);
        when(c.compareTo(anyInt())).thenReturn(-1);
        //assert
        assertEquals(-1, c.compareTo(9));
    }

    @Test
    public void testReturnValueInDependentOnMethodParameter2()  {
        Comparable<Todo> c= mock(Comparable.class);
        when(c.compareTo(isA(Todo.class))).thenReturn(0);
        //assert
        assertEquals(0, c.compareTo(new Todo(1)));
    }

    @Test
    public void testReturnValueInDependentOnMethodParameter3()  {
        Properties properties = mock(Properties.class);

        when(properties.get("Anddroid")).thenThrow(new IllegalArgumentException());

        try {
            properties.get("Anddroid");
            fail("Anddroid is misspelled");
        } catch (IllegalArgumentException ex) {
            // good!
        }
    }

    @Test
    public void testReturnValueInDependentOnMethodParameter4()  {
        Properties properties = new Properties();

        Properties spyProperties = spy(properties);

        doReturn("42").when(spyProperties).get("shoeSize");

        String value = (String) spyProperties.get("shoeSize");

        assertEquals("42", value);
    }


// 4.3. Wrapping Java objects with Spy

    @Test
    public void testLinkedListSpyWrong() {
        // Lets mock a LinkedList
        List<String> list = new LinkedList<>();
        List<String> spy = spy(list);

        // this does not work
        // real method is called so spy.get(0)
        // throws IndexOutOfBoundsException (list is still empty)
        when(spy.get(0)).thenReturn("foo");

        assertEquals("foo", spy.get(0));
    }

    @Test
    public void testLinkedListSpyCorrect() {
        // Lets mock a LinkedList
        List<String> list = new LinkedList<>();
        list.add("one");
        list.add("two");
        List<String> spy = spy(list);

        // You have to use doReturn() for stubbing
        doReturn("foo").when(spy).get(0);

        assertEquals("foo", spy.get(0));

        assertEquals("two", spy.get(1));
    }

// E:\A\_00012\amazonmigrationstudio\pom.xml

 // 4.4. Verify the calls on the mock objects
 @Test
 public void testVerify()  {
     // create and configure mock
     MyClass test = Mockito.mock(MyClass.class);
     when(test.getUniqueId()).thenReturn(43);


     // call method testing on the mock with parameter 12
     test.testing(12);
     test.getUniqueId();
     test.getUniqueId();


     // now check if method testing was called with the parameter 12
//     verify(test).testing(ArgumentMatchers.eq(12));

     // was the method called twice?
     verify(test, times(2)).getUniqueId();

     // other alternatives for verifiying the number of method calls for a method
//     verify(test, never()).someMethod("never called");
//     verify(test, atLeastOnce()).someMethod("called at least once");
//     verify(test, atLeast(2)).someMethod("called at least twice");
//     verify(test, times(5)).someMethod("called five times");
//     verify(test, atMost(3)).someMethod("called at most 3 times");
     // This let's you check that no other methods where called on this object.
     // You call it after you have verified the expected method calls.
     verifyNoMoreInteractions(test);
 }
}


class MyClass {

    int getUniqueId() {
        return 43;
    }


    public void testing(int i) {
    }
}


 class Todo {
    public Todo(int i) {

    }
}

