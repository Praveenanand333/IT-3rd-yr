using System; 
using System.Text; 
//s.Append(),s.AppendLine(),s.Insert(),s.Remove(),s.Replace()
class GFG { 
  
    // Main Method 
    public static void Main() 
    { 
  
        // "20" is capacity 
        StringBuilder s = new StringBuilder("HELLO ", 20); 
          
        s.Append("GFG"); 
  
        // after printing "GEEKS" 
        // a new line append 
        s.AppendLine("GEEKS"); 
          
        s.Append("GeeksForGeeks"); 
        Console.WriteLine(s); 



         // "20" is capacity 
        StringBuilder s = new StringBuilder("HELLO ", 20); 
          
        // "GEEKS" insert after 6th index 
        s.Insert(6, "GEEKS"); 


          StringBuilder s = new StringBuilder("GeeksForGeeks", 20); 
  
        // remove starts from index 5 
        // and remove happes 3 index  
        // after index 5 
        s.Remove(5, 3); 


          StringBuilder s = new StringBuilder("GFG Geeks ", 20); 
          
        // Replace "GFG" with "Geeks For" 
        s.Replace("GFG", "Geeks For"); 
  

    } 
} 