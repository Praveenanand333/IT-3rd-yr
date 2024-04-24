using System;
using System.Collections.Generic;
using System.Linq;


class Student {
	public int rollNo
	{
		get;
		set;
	}
	
	public string name
	{
		get;
		set;
	}
}

class GFG {
	static void Main(string[] args)
	{
		List<Student> details = new List<Student>() {
			new Student{ rollNo = 1, name = "Liza" },
				new Student{ rollNo = 2, name = "Stewart" },
				new Student{ rollNo = 3, name = "Tina" },
				new Student{ rollNo = 4, name = "Stefani" },
				new Student { rollNo = 5, name = "Trish" }
		};
		var newDetails = details.OrderBy(x => x.name);

		foreach(var value in newDetails)
		{
			Console.WriteLine(value.rollNo + " " + value.name);
		}
	}
}
