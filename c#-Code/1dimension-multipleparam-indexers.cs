using System;

public class MyCollection
{
	private int[] data = new int[10];

	// Indexer with multiple parameters
	public int this[int index, bool square]
	{
		get
		{
			if (square)
				return data[index] * data[index];
			else
				return data[index];
		}
		set
		{
			if (square)
				data[index] = (int)Math.Sqrt(value);
			else
				data[index] = value;
		}
	}

	// Overloaded indexer with string parameter
	public int this[string name]
	{
		get
		{
			switch (name.ToLower())
			{
				case "first":
					return data[0];
				case "last":
					return data[data.Length - 1];
				default:
					throw new ArgumentException("Invalid index parameter.");
			}
		}
	}

	// Read-only indexer
	public int this[int index]
	{
		get { return data[index]; }
	}
}

public class Program
{
	public static void Main()
	{
		MyCollection collection = new MyCollection();

		// Setting values using multiple parameter indexer
		collection[0, false] = 5;
		collection[1, false] = 10;
		collection[2, false] = 15;
		collection[3, false] = 20;

		// Getting values using multiple parameter indexer
		Console.WriteLine(collection[0, false]); 
		Console.WriteLine(collection[1, true]); 

		// Getting values using string parameter indexer
		Console.WriteLine(collection["first"]); 
		Console.WriteLine(collection["last"]); 

		// Getting values using read-only indexer
		Console.WriteLine(collection[2]);		 



		Console.ReadLine();
	}
}
