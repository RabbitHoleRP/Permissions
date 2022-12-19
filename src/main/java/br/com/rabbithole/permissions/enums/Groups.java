package br.com.rabbithole.permissions.enums;

public enum Groups {
    ADMIN(1000, "<DARK_RED><BOLD>ADMIN<GREY>"),
    COORDINATOR(999, "<GOLD><BOLD>COORD<GREY> "),
    MODERATOR(998, "<DARK_GREEN><BOLD>MOD<GREY> "),
    HELPER(997, "<YELLOW><BOLD>AJD<GREY> "),
    YOUTUBER(500, "<RED><BOLD>YT<GREY> "),
    STREAMER(499, "<PURPLE><BOLD>STREAMER<GREY> "),
    DUQUE(498, "<GREN><BOLD>DUQUE<GREY>"),
    CONDE(497, "<AQUA><BOLD>CONDE<GREY>"),
    BARAO(496, "<RED><BOLD>BARÃO<GREY>"),
    MEMBER(0, "<GREY>"),
    NOT_FOUND(-1, "");

    public final int hierarchy;
    public final String tag;

    Groups(int hierarchy, String tag) {
        this.hierarchy = hierarchy;
        this.tag = tag;
    }

    public int getHierarchy() {
        return hierarchy;
    }

    public String getTag() {
        return tag;
    }
}
