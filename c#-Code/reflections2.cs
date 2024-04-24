using System; 
using System.Reflection; 
namespace Reflection_Metadata {  
class Student { 
	public int RollNo 
	{ 
		get; 
		set; 
	} 
	
	public string Name 
	{ 
		get; 
		set; 
	} 

	
	public Student() 
	{ 
		RollNo = 0; 
		Name = string.Empty; 
	} 
	public Student(int rno, string n) 
	{ 
		RollNo = rno; 
		Name = n; 
	} 

	
	public void displayData() 
	{ 
		Console.WriteLine("Roll Number : {0}", RollNo); 
		Console.WriteLine("Name : {0}", Name); 
	} 
} 

class GFG { 

	static void Main(string[] args) 
	{ 

		Assembly executing = Assembly.GetExecutingAssembly(); 
		Type[] types = executing.GetTypes(); 
		foreach(var item in types) 
		{ 
			Console.WriteLine("Class : {0}", item.Name); 
			MethodInfo[] methods = item.GetMethods(); 
			foreach(var method in methods) 
			{ 
				Console.WriteLine("--> Method : {0}", method.Name); 
				ParameterInfo[] parameters = method.GetParameters(); 
				foreach(var arg in parameters) 
				{ 

					Console.WriteLine("----> Parameter : {0} Type : {1}",arg.Name, arg.ParameterType); 
				} 
			} 
		} 
	} 
} 
} 
