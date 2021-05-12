package bufferImpl;

import java.util.ArrayList;

import com.sun.tools.sjavac.Log;

public class BoundedBufferImpl implements BoundedBuffer {

	static private Object[] buffer;
	private int pointer = -1;
	private boolean isFull = false;
	private boolean isEmpty = true;

	public BoundedBufferImpl() {
		buffer = new Object[5];
	}

	public BoundedBufferImpl(int size) {
		System.out.println("Buffer size is: " + size);
		buffer = new Object[size];
	}

	public void insert(Object o) {
		synchronized(buffer) {
			System.out.println(Thread.currentThread().getName());
			while (isFull) {
				try {
					buffer.wait();
					
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					Log.error("Thread interrupted" + e);
				}
			}


			System.out.println("Adding object");
			++pointer;
			buffer[pointer] = o;
			if (pointer == buffer.length) {
				this.isFull = true;
				System.out.println("Buffer full!");
				buffer.notifyAll();
			}
			buffer.notifyAll();
		}
	}

	public Object remove() {
		synchronized(buffer) {
			System.out.println(Thread.currentThread().getName());
			
			while (isEmpty) {
				try {
					buffer.wait();
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					Log.error("Thread interrupted" + e);
				}
			}

			System.out.println("Remove object");

			Object result;

			result = this.buffer[0];
			int size = this.pointer;
			for (int i = 0; i <= size; i++) {
				if (i != size) {
					buffer[i] = buffer[i + 1];
				} else {

					buffer[i] = null;
					break;
				}

			}

			--this.pointer;

			System.out.println("Current pointer: " + this.pointer);
			if(this.pointer == -1)
				isEmpty = true;
			isFull = false;

			buffer.notifyAll();

			return result;
		}
	}
}
