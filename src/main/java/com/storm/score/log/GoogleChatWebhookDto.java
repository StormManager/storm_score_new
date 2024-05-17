package com.storm.score.log;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GoogleChatWebhookDto {
    private String name;
    private List<Object> annotations;
    private String argumentText;
    private List<Card> cards;
    private String createTime;
    private String fallbackText;
    private String previewText;
    private Sender sender;
    private Space space;
    private String text;
    private Thread thread;

    public GoogleChatWebhookDto(String text) {
        this.text = text;
    }


    public static class Card {
        private List<Object> cardActions;
        private Header header;
        private String name;
        private List<Object> sections;

        // getter, setter, constructor 생략
    }

    public static class Header {
        private String imageAltText;
        private String imageStyle;
        private String imageUrl;
        private String subtitle;
        private String title;

        // getter, setter, constructor 생략
    }

    public static class Sender {
        private String avatarUrl;
        private String displayName;
        private String email;
        private String name;
        private String type;

        // getter, setter, constructor 생략
    }

    public static class Space {
        private String displayName;
        private String name;
        private String type;

        // getter, setter, constructor 생략
    }

    public static class Thread {
        private String name;

        // getter, setter, constructor 생략
    }
}