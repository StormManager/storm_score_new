package com.storm.score.log;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * packageName    : com.storm.score.log
 * fileName       : SlackReqDto
 * author         : ojy
 * date           : 2024/05/19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/05/19        ojy       최초 생성
 */

/*
{
    "content": "",
    "embeds": [
        {
            "id": "",
            "title": "exception name",
            "description": "exception content",
            "url": "",
            "color": "15865880",
            "author": {
                "name": "exception name",
                "url": "",
                "icon_url": ""
            },
            "footer": {
                "text": "",
                "icon_url": ""
            },
            "image": {
                "url": ""
            },
            "thumbnail": {
                "url": ""
            },
            "fields": [
                {
                    "title": "",
                    "value": "",
                    "shortValue": ""
                }
            ]
        }
    ]
}
*/
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SlackReqDto {

    @JsonProperty("content")
    private String content;

    @JsonProperty("embeds")
    private List<Embeds> embeds;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Embeds {
        @JsonProperty("id")
        private String id;

        @JsonProperty("title")
        private String title;

        @JsonProperty("description")
        private String description;

        @JsonProperty("url")
        private String url;

        @JsonProperty("color")
        private String color;

        @JsonProperty("author")
        private Author author;

        @JsonProperty("footer")
        private Footer footer;

        @JsonProperty("image")
        private Image image;

        @JsonProperty("thumbnail")
        private Thumbnail thumbnail;

        @JsonProperty("fields")
        private List<Fields> fields;

        @Getter
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Author {
            @JsonProperty("name")
            private String name;

            @JsonProperty("url")
            private String url;

            @JsonProperty("icon_url")
            private String iconUrl;
        }

        @Getter
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Footer {
            @JsonProperty("text")
            private String text;

            @JsonProperty("icon_url")
            private String iconUrl;
        }

        @Getter
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Image {
            @JsonProperty("url")
            private String url;
        }

        @Getter
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Thumbnail {
            @JsonProperty("url")
            private String url;
        }

        @Getter
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Fields {
            @JsonProperty("title")
            private String title;

            @JsonProperty("value")
            private String value;

            @JsonProperty("shortValue")
            private String shortValue;
        }

    }
}
