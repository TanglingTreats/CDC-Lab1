package bufferImpl;

import java.util.ArrayList;

public class BoundedBufferImpl implements BoundedBuffer {
	
	private Object[] buffer;
	private int pointer = -1;
	public BoundedBufferImpl() 
	{
		buffer = new Object[5];
	}
	
	public BoundedBufferImpl(int size)
	{
		System.out.println("Buffer size is: " + size);
		buffer = new Object[size];
	}

	public void insert(Object o) 
	{
		pointer++;
		if (pointer < buffer.length)
		{
			System.out.println("Adding object");
			buffer[pointer] = o;
		}
		else 
		{
			//Do nothing
			System.out.println("Buffer full!");
		}
	}
	
	public Object remove()
	{
		System.out.println("Remove object");
		Object result;
		if(pointer > -1)
		{
			result = this.buffer[0];
			int size = this.pointer;
			for(int i = 0; i < size; i++)
			{
				if (i != size-1)
				{
					buffer[i] = buffer[i+1];
				} 
				else 
				{
					
					buffer[i] = null;
					break;
				}

			}
			--pointer;
			System.out.println("Current pointer: " + this.pointer);
			return result;
		}
		else 
		{
			return null;
		}
	}
}
