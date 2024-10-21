package br.com.pepper.account.config;

import io.opentelemetry.api.trace.Span;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class TraceIdResponseBodyAdvice implements ResponseBodyAdvice<Object> {

  @Override
  public boolean supports(
      MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
    return true; // Aplica-se a todas as respostas
  }

  @Override
  public Object beforeBodyWrite(
      Object body,
      MethodParameter returnType,
      MediaType selectedContentType,
      Class<? extends HttpMessageConverter<?>> selectedConverterType,
      ServerHttpRequest request,
      ServerHttpResponse response) {

    if (response instanceof ServletServerHttpResponse) {
      ServletServerHttpResponse servletResponse = (ServletServerHttpResponse) response;
      HttpServletResponse httpServletResponse = servletResponse.getServletResponse();

      Span span = Span.current();
      if (span != null && span.getSpanContext().isValid()) {
        String traceId = span.getSpanContext().getTraceId();
        httpServletResponse.addHeader("X-Trace-Id", traceId); // Use um nome padronizado
      }
    }

    return body;
  }
}
