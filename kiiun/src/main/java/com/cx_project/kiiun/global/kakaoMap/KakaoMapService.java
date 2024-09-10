package com.cx_project.kiiun.global.kakaoMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KakaoMapService {

    private static final String API_KEY = "1fec60929c95a6c6e0897b2631857c48";
    private static final String API_URL = "https://dapi.kakao.com/v2/local/search/address.json";
    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 주소를 검색하고 카카오 API 응답을 문자열로 반환하는 메서드
     * @param query 검색할 주소
     * @return API 응답 본문
     * @throws IOException
     */
    public KakaoApiResDTO.Document getCoordinatesFromAddress(String query) throws IOException {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(API_URL).newBuilder();
        urlBuilder.addQueryParameter("query", query);

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .addHeader("Authorization", "KakaoAK " + API_KEY)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {

                String responseBody = response.body().string();

                KakaoApiResDTO apiResponse = objectMapper.readValue(responseBody, KakaoApiResDTO.class);
                return apiResponse.getDocuments().get(0);
            } else {
                System.out.println("Request failed: " + response.code());
                return null;
            }
        }
    }
}
