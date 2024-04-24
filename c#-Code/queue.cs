using System; 
using System.Collections; 

class GFG { 

	public static void Main() 
	{ 
		Queue myQueue = new Queue(); 
		myQueue.Enqueue("C#"); 
		myQueue.Enqueue("PHP"); 
		myQueue.Enqueue("Perl"); 
		myQueue.Enqueue("Java"); 
		myQueue.Enqueue("C");  
		Console.Write("Total number of elements present in the Queue are: "); 
		Console.WriteLine(myQueue.Count); 
		Console.WriteLine("Beginning Item is: " + myQueue.Peek()); 
	} 
} 
