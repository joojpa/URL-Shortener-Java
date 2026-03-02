package dev.EncutadorDeUrl.services;

import dev.EncutadorDeUrl.entities.Url;
import dev.EncutadorDeUrl.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class UrlServices {

    @Autowired
    private UrlRepository urlRepository;

    public String shortenUrl(String originalUrl){

        int count = 0;
        String shortUrl = generateShortUrl();
        Url url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setShortUrl(shortUrl);
        url.setExpirationDate(LocalDateTime.now().plusDays(30));
        url.setClicks(count++);
        urlRepository.save(url);


        return shortUrl;
    }
    private String generateShortUrl(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder shortUrl = new StringBuilder();
        SecureRandom random = new SecureRandom();
        int length = 5 + random.nextInt(6);
        for (int i = 0; i < length;i++){
            shortUrl.append(characters.charAt(random.nextInt(characters.length())));
        }
        return shortUrl.toString();
    }
}
