package us.pdavidson.reactivereddit;

import java.util.List;

/**
 * Created by pete on 9/14/14.
 */
public class ListingBean {
    private String kind;
    private ListingData data;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public ListingData getData() {
        return data;
    }

    public void setData(ListingData data) {
        this.data = data;
    }

    public static class ListingData {
        private String modHash;
        private List<ListingChildBean> children;
        private String after;
        private String before;

        public String getModHash() {
            return modHash;
        }

        public void setModHash(String modHash) {
            this.modHash = modHash;
        }

        public List<ListingChildBean> getChildren() {
            return children;
        }

        public void setChildren(List<ListingChildBean> children) {
            this.children = children;
        }

        public String getAfter() {
            return after;
        }

        public void setAfter(String after) {
            this.after = after;
        }

        public String getBefore() {
            return before;
        }

        public void setBefore(String before) {
            this.before = before;
        }
    }

    private static class ListingChildBean {
        private String kind;
        private ListingChildDataBean data;

        public String getKind() {
            return kind;
        }

        public void setKind(String kind) {
            this.kind = kind;
        }

        public ListingChildDataBean getData() {
            return data;
        }

        public void setData(ListingChildDataBean data) {
            this.data = data;
        }
    }

    private static class ListingChildDataBean {
        private String title;
        private String url;
        private String permalink;
        private String name;
        private Long score;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPermalink() {
            return permalink;
        }

        public void setPermalink(String permalink) {
            this.permalink = permalink;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getScore() {
            return score;
        }

        public void setScore(Long score) {
            this.score = score;
        }
    }
}
