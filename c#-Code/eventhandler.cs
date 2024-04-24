using System;
public class MyEventArgs : EventArgs
{
    public string Message { get; }
    public MyEventArgs(string message)
    {
        Message = message;
    }
}

public delegate void MyEventHandler(object sender, MyEventArgs e);
public class Publisher
{
    public event MyEventHandler MyEvent;
    public void RaiseEvent(string message)
    {
        OnMyEvent(new MyEventArgs(message));
    }
    protected virtual void OnMyEvent(MyEventArgs e)
    {
        MyEventHandler handler = MyEvent;
        handler?.Invoke(this, e);
    }
}
public class Subscriber
{
    public void HandleEvent(object sender, MyEventArgs e)
    {
        Console.WriteLine($"Received message: {e.Message}");
    }
}

class Program
{
    static void Main(string[] args)
    {
        Publisher publisher = new Publisher();
        Subscriber subscriber = new Subscriber();
        publisher.MyEvent += subscriber.HandleEvent;
        publisher.RaiseEvent("Hello, world!");
    }
}
