package sun.misc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.ByteBuffer;

public abstract class CharacterEncoder {

	protected PrintStream pStream;

	public CharacterEncoder() {
		// compiled code
		throw new RuntimeException("Compiled Code");
	}

	protected abstract int bytesPerAtom();

	protected abstract int bytesPerLine();

	protected void encodeBufferPrefix(OutputStream out) throws IOException {
		// compiled code
		throw new RuntimeException("Compiled Code");
	}

	protected void encodeBufferSuffix(OutputStream out) throws IOException {
		// compiled code
		throw new RuntimeException("Compiled Code");
	}

	protected void encodeLinePrefix(OutputStream out, int i) throws IOException {
		// compiled code
		throw new RuntimeException("Compiled Code");
	}

	protected void encodeLineSuffix(OutputStream out) throws IOException {
		// compiled code
		throw new RuntimeException("Compiled Code");
	}

	protected abstract void encodeAtom(OutputStream out, byte[] bytes, int i,
			int i1) throws IOException;

	protected int readFully(InputStream in, byte[] bytes) throws IOException {
		// compiled code
		throw new RuntimeException("Compiled Code");
	}

	public void encode(InputStream in, OutputStream out) throws IOException {
		// compiled code
		throw new RuntimeException("Compiled Code");
	}

	public void encode(byte[] bytes, OutputStream out) throws IOException {
		// compiled code
		throw new RuntimeException("Compiled Code");
	}

	public String encode(byte[] bytes) {
		// compiled code
		throw new RuntimeException("Compiled Code");
	}

	private byte[] getBytes(ByteBuffer bb) {
		// compiled code
		throw new RuntimeException("Compiled Code");
	}

	public void encode(ByteBuffer bb, OutputStream out) throws IOException {
		// compiled code
		throw new RuntimeException("Compiled Code");
	}

	public String encode(ByteBuffer bb) {
		// compiled code
		throw new RuntimeException("Compiled Code");
	}

	public void encodeBuffer(InputStream in, OutputStream out)
			throws IOException {
		// compiled code
		throw new RuntimeException("Compiled Code");
	}

	public void encodeBuffer(byte[] bytes, OutputStream out) throws IOException {
		// compiled code
		throw new RuntimeException("Compiled Code");
	}

	public String encodeBuffer(byte[] bytes) {
		// compiled code
		throw new RuntimeException("Compiled Code");
	}

	public void encodeBuffer(ByteBuffer bb, OutputStream out)
			throws IOException {
		// compiled code
		throw new RuntimeException("Compiled Code");
	}

	public String encodeBuffer(ByteBuffer bb) {
		// compiled code
		throw new RuntimeException("Compiled Code");
	}
}
