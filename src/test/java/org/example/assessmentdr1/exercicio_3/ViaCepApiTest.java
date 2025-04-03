package org.example.assessmentdr1.exercicio_3;

import org.junit.jupiter.api.*;
import java.net.http.*;
import java.net.URI;
import static org.junit.jupiter.api.Assertions.*;

public class ViaCepApiTest {

    private static final HttpClient client = HttpClient.newHttpClient();

    @Nested
    class EndpointCepTest {

        @Test
        void testCepValido() throws Exception {
            HttpResponse<String> response = get("https://viacep.com.br/ws/51020100/json/");
            assertEquals(200, response.statusCode());
            assertTrue(response.body().contains("logradouro"));
        }

        @Test
        void testCepInexistente() throws Exception {
            HttpResponse<String> response = get("https://viacep.com.br/ws/00000000/json/");
            assertEquals(200, response.statusCode());
            assertTrue(response.body().contains("\"erro\": true"));
        }

        @Test
        void testCepComLetras() throws Exception {
            HttpResponse<String> response = get("https://viacep.com.br/ws/cepcepce/json/");
            assertTrue(response.statusCode() >= 400);
        }

        @Test
        void testCepComHifen() throws Exception {
            HttpResponse<String> response = get("https://viacep.com.br/ws/51020-100/json/");
            assertEquals(200, response.statusCode());
            assertTrue(response.body().contains("logradouro"));
        }

        @Test
        void testCepComPoucosDigitos() throws Exception {
            HttpResponse<String> response = get("https://viacep.com.br/ws/000/json/");
            assertTrue(response.statusCode() >= 400);
        }

        @Test
        void testCepVazio() throws Exception {
            HttpResponse<String> response = get("https://viacep.com.br/ws//json/");
            assertEquals(404, response.statusCode());
        }
    }

    @Nested
    class EndpointEnderecoTest {

        @Test
        void testEnderecoComAcento() throws Exception {
            HttpResponse<String> response = get("https://viacep.com.br/ws/SP/S%C3%A3o%20Paulo/Avenida%20Paulista/json/");
            assertEquals(200, response.statusCode());
            assertTrue(response.body().contains("Paulista"));
        }

        @Test
        void testEnderecoSemAcento() throws Exception {
            HttpResponse<String> response = get("https://viacep.com.br/ws/SP/Sao%20Paulo/Avenida%20Paulista/json/");
            assertEquals(200, response.statusCode());
            assertTrue(response.body().contains("Paulista"));
        }

        @Test
        void testMunicipioInexistente() throws Exception {
            HttpResponse<String> response = get("https://viacep.com.br/ws/SP/Recife/Avenida%20Paulista/json/");
            assertEquals(200, response.statusCode());
            assertTrue(response.body().equals("[]"));
        }

        @Test
        void testLogradouroInexistente() throws Exception {
            HttpResponse<String> response = get("https://viacep.com.br/ws/SP/Sao%20Paulo/Avenida%20Boa%20Viagem/json/");
            assertEquals(200, response.statusCode());
            assertTrue(response.body().equals("[]"));
        }

        @Test
        void testUFInvalidoParaCidade() throws Exception {
            HttpResponse<String> response = get("https://viacep.com.br/ws/PE/Sao%20Paulo/Avenida%20Paulista/json/");
            assertEquals(200, response.statusCode());
            assertTrue(response.body().equals("[]"));
        }

        @Test
        void testUFVazio() throws Exception {
            HttpResponse<String> response = get("https://viacep.com.br/ws//Sao%20Paulo/Avenida%20Paulista/json/");
            assertEquals(404, response.statusCode());
        }

        @Test
        void testCidadeVazia() throws Exception {
            HttpResponse<String> response = get("https://viacep.com.br/ws/SP//Avenida%20Paulista/json/");
            assertEquals(404, response.statusCode());
        }

        @Test
        void testLogradouroVazio() throws Exception {
            HttpResponse<String> response = get("https://viacep.com.br/ws/SP/Sao%20Paulo//json/");
            assertEquals(404, response.statusCode());
        }
    }

    private HttpResponse<String> get(String url) throws Exception {
        HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).GET().build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}

