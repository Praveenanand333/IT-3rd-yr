using System;
using System.Collections;

public class ArrayListExample
{
    public static void Main(string[] args)
    {
        
        ArrayList myList = new ArrayList();

        
        myList.Add("Apple");
        myList.Add(10);
        myList.Add(true);

        
        Console.WriteLine("Elements in the ArrayList:");
        foreach (object item in myList)
        {
            Console.WriteLine(item);
        }

        
        string firstFruit = (string)myList[0];
        Console.WriteLine("\nFirst element: {0}", firstFruit);

        
        if (myList.Contains(true))
        {
            Console.WriteLine("\nArrayList contains 'true'");
        }

        
        myList.Remove(10);
        Console.WriteLine("\nAfter removing 10:");
        foreach (object item in myList)
        {
            Console.WriteLine(item);
        }

        
        myList.Insert(1, "Banana");
        Console.WriteLine("\nAfter inserting 'Banana':");
        foreach (object item in myList)
        {
            Console.WriteLine(item);
        }

        
        int count = myList.Count;
        Console.WriteLine("\nTotal elements: {0}", count);
    }
}