using System;
class rectangle {

public delegate void rectDelegate(double height,double width);

	public void area(double height, double width)
	{
		Console.WriteLine("Area is: {0}", (width * height));
	}
	public void perimeter(double height, double width)
	{
		Console.WriteLine("Perimeter is: {0} ", 2 * (width + height));
	}

public static void Main(String []args)
{
	rectangle rect = new rectangle();
	rectDelegate rectdele = new rectDelegate(rect.area);
	rectdele += rect.perimeter; //multicast
	rectdele.Invoke(6.3, 4.2);//invoke both methods
	Console.WriteLine();
	rectdele.Invoke(16.3, 10.3);
}
}
