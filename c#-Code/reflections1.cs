using System; 
using System.Reflection; 
namespace Reflection_Demo { 
class Program { 
	static void Main(string[] args) 
	{ 
		Type t = typeof(string); 
		Console.WriteLine("Name : {0}", t.Name); 
		Console.WriteLine("Full Name : {0}", t.FullName); 
		Console.WriteLine("Namespace : {0}", t.Namespace); 
		Console.WriteLine("Base Type : {0}", t.BaseType); 
	} 
} 
} 
