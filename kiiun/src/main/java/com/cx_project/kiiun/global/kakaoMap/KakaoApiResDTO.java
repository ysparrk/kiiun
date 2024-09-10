package com.cx_project.kiiun.global.kakaoMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIgnoreProperties(ignoreUnknown = true) // 불필요한 필드를 무시하도록 설정
public class KakaoApiResDTO {

    @JsonProperty("documents")
    private List<Document> documents;

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    // 내부 Document 클래스
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Document {

        @JsonProperty("x")
        private String x;

        @JsonProperty("y")
        private String y;

        // Getters and Setters
        public String getX() {
            return x;
        }

        public void setX(String x) {
            this.x = x;
        }

        public String getY() {
            return y;
        }

        public void setY(String y) {
            this.y = y;
        }
    }
}
