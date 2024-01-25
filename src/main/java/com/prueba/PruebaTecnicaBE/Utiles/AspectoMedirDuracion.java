package com.prueba.PruebaTecnicaBE.Utiles;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectoMedirDuracion {

    private static final Logger LOG = LoggerFactory.getLogger(AspectoMedirDuracion.class);

    @Around("@annotation(com.prueba.PruebaTecnicaBE.Utiles.MedirDuracion)")
    public Object medirTiempo(ProceedingJoinPoint joinPoint) throws Throwable {

        long comienzo = System.currentTimeMillis();
        Object resultado = joinPoint.proceed();
        long fin = System.currentTimeMillis();
        long duracion = fin - comienzo;

        LOG.info("El método '{}' tardó {} milisegundos en ejecutarse.", joinPoint.getSignature().toShortString(),duracion);
        return resultado;
    }
}
