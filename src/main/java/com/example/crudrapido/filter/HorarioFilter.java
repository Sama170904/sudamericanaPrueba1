package com.example.crudrapido.filter;


import com.example.crudrapido.service.ParametroService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalTime;

@Component 
public class HorarioFilter implements Filter {

    @Autowired
    private ParametroService parametroService; 

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        LocalTime horaActual = LocalTime.now();
        LocalTime horaLimite = parametroService.getHoraLimite();

        
        if (horaActual.isAfter(horaLimite)) {
            
            httpResponse.setStatus(HttpStatus.FORBIDDEN.value());
            httpResponse.getWriter().write("Acceso denegado: Fuera de horario. El límite es las " + horaLimite);
            return; 
        }

        chain.doFilter(request, response);
    }
}