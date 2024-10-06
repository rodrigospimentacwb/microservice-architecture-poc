package br.com.pepper.account.config.log;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.instrument.util.IOUtils;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RequestResponseLoggingFilter implements Filter {

  private static final Logger LOGGER = LoggerFactory.getLogger(RequestResponseLoggingFilter.class);
  private static final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public void doFilter(
      final ServletRequest request, final ServletResponse response, final FilterChain chain)
      throws IOException, ServletException {
    final CachedHttpServletRequest cachedRequest =
        new CachedHttpServletRequest((HttpServletRequest) request);

    // Log method
    final String method = cachedRequest.getMethod();

    // Log path
    final String path = cachedRequest.getRequestURI();

    // Log headers
    final StringBuilder headers = new StringBuilder();
    final Enumeration<String> headerNames = cachedRequest.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      final String headerName = headerNames.nextElement();
      headers
          .append(headerName)
          .append("=")
          .append(cachedRequest.getHeader(headerName))
          .append(", ");
    }

    // Log request body
    String requestBody = IOUtils.toString(cachedRequest.getInputStream(), StandardCharsets.UTF_8);
    requestBody = minifyJson(requestBody);

    // Format the log message in a single line
    final String logMessage =
        String.format(
            "REQUEST: method=%s, path=%s, headers=[%s], body=%s",
            method, path, headers.toString(), requestBody);
    LOGGER.info(logMessage);

    // Continue the filter chain
    chain.doFilter(cachedRequest, response);
  }

  private String minifyJson(final String json) {
    try {
      final JsonNode jsonNode = objectMapper.readTree(json);
      return objectMapper.writeValueAsString(jsonNode);
    } catch (final IOException e) {
      return json;
    }
  }
}
