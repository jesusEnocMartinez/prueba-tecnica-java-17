package prueba.marvel.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import prueba.marvel.service.MarvelApiService;
import prueba.marvel.utils.CustomError;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.http.HttpStatus;


@Service
public class MarvelApiServiceImpl implements MarvelApiService {
    @Value("${marvel.api.public-key}")
    private String publicKey;

    @Value("${marvel.api.private-key}")
    private String privateKey;

    @Value("${marvel.api.base-url}")
    private String baseUrl;

    /**
     * Retrieves information about Marvel characters from the API.
     *
     * @return ResponseEntity containing the response body as a String.
     * @throws CustomError if there is an error generating the MD5 hash.
     */
    @Override
    public ResponseEntity<String> getCharacters() {
        try {
            long timestamp = System.currentTimeMillis() / 1000;
            String hash = generateMD5Hash(timestamp);
            String url = baseUrl + "?apikey=" + publicKey + "&hash=" + hash + "&ts=" + timestamp;
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.getForEntity(url, String.class);
        } catch (CustomError e) {
            throw new CustomError("Error generating MD5 hash", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Generates an MD5 hash from the provided timestamp, public key, and private key.
     *
     * @param timestamp The current timestamp in seconds.
     * @return The MD5 hash as a hexadecimal string.
     * @throws CustomError if there is an error generating the MD5 hash.
     */
    public String generateMD5Hash(long timestamp) {
        try {
            String input = timestamp + privateKey + publicKey;
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder(2 * messageDigest.length);
            for (byte b : messageDigest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new CustomError("Error generating MD5 hash", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    }

