package org.example.GlobalSolutionFinal.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

@Controller
public class LocaleController {

    private final LocaleResolver localeResolver;

    public LocaleController(LocaleResolver localeResolver) {
        this.localeResolver = localeResolver;
    }

    @GetMapping("/changeLang")
    public String changeLanguage(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam("lang") String lang) {
        Locale locale = "en".equalsIgnoreCase(lang) ? new Locale("en", "US") : new Locale("pt", "BR");
        localeResolver.setLocale(request, response, locale);

        String referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "/");
    }
}
