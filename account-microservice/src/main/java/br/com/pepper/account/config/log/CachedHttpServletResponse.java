package br.com.pepper.account.config.log;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class CachedHttpServletResponse extends HttpServletResponseWrapper {

  private final ByteArrayOutputStream cachedBody = new ByteArrayOutputStream();
  private final ServletOutputStream outputStream = new CachedServletOutputStream(cachedBody);
  private final PrintWriter writer = new PrintWriter(cachedBody, true);

  public CachedHttpServletResponse(final HttpServletResponse response) {
    super(response);
  }

  @Override
  public ServletOutputStream getOutputStream() {
    return outputStream;
  }

  @Override
  public PrintWriter getWriter() {
    return writer;
  }

  public byte[] getCachedBody() {
    return cachedBody.toByteArray();
  }

  private static class CachedServletOutputStream extends ServletOutputStream {
    private final ByteArrayOutputStream cachedBody;

    public CachedServletOutputStream(final ByteArrayOutputStream cachedBody) {
      this.cachedBody = cachedBody;
    }

    @Override
    public void write(final int b) throws IOException {
      cachedBody.write(b);
    }

    @Override
    public boolean isReady() {
      return true;
    }

    @Override
    public void setWriteListener(final WriteListener writeListener) {
      // Not used
    }
  }
}
