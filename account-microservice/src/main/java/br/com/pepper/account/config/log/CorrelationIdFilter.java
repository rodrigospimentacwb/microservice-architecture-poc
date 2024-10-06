package br.com.pepper.account.config.log;

import jakarta.servlet.*;
import java.io.IOException;
import java.util.UUID;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Component
public class CorrelationIdFilter implements Filter {

  @Override
  public void doFilter(
      final ServletRequest request, final ServletResponse response, final FilterChain chain)
      throws IOException, ServletException {
    final String correlationId = UUID.randomUUID().toString();
    MDC.put("correlationId", correlationId);
    try {
      chain.doFilter(request, response);
    } finally {
      MDC.remove("correlationId");
    }
  }

  @Override
  public void init(final FilterConfig filterConfig) throws ServletException {
    // Optional initialization method, can be left empty if not needed
  }

  @Override
  public void destroy() {
    // Optional destroy method, can be left empty if not needed
  }
}
