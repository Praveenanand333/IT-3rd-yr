using System; 
using System.Collections.Generic; 

class Geeks { 
	public static void Main(String[] args) 
	{  
		List<int> mylist = new List<int>(); 
		for (int j = 5; j < 10; j++) { 
			mylist.Add(j * 3); 
		} 
		foreach(int items in mylist) 
		{ 
			Console.WriteLine(items); 
		} 
	} 
} 
