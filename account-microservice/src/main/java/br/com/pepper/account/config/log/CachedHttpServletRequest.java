package br.com.pepper.account.config.log;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CachedHttpServletRequest extends HttpServletRequestWrapper {

  private final byte[] cachedBody;

  public CachedHttpServletRequest(final HttpServletRequest request) throws IOException {
    super(request);
    final InputStream requestInputStream = request.getInputStream();
    this.cachedBody = requestInputStream.readAllBytes(); // Reads and caches the body
  }

  @Override
  public ServletInputStream getInputStream() {
    final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.cachedBody);
    return new ServletInputStream() {
      @Override
      public int read() throws IOException {
        return byteArrayInputStream.read();
      }

      @Override
      public boolean isFinished() {
        return byteArrayInputStream.available() == 0;
      }

      @Override
      public boolean isReady() {
        return true;
      }

      @Override
      public void setReadListener(final ReadListener readListener) {
        // Not used
      }
    };
  }

  public byte[] getCachedBody() {
    return this.cachedBody;
  }
}
