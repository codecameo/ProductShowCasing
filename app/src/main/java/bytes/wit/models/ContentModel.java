package bytes.wit.models;

import java.io.Serializable;

/**
 * Created by Md. Sifat-Ul Haque on 12/26/2016.
 */
public class ContentModel implements Serializable {

    private String content_id, content_url, extension, type, thumb_url, thumb_width, thumb_height;

    public String getContent_id() {
        return content_id;
    }

    public void setContent_id(String content_id) {
        this.content_id = content_id;
    }

    public String getContent_url() {
        return content_url;
    }

    public void setContent_url(String content_url) {
        this.content_url = content_url;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getThumb_url() {
        return thumb_url;
    }

    public void setThumb_url(String thumb_url) {
        this.thumb_url = thumb_url;
    }

    public String getThumb_width() {
        return thumb_width;
    }

    public void setThumb_width(String thumb_width) {
        this.thumb_width = thumb_width;
    }

    public String getThumb_height() {
        return thumb_height;
    }

    public void setThumb_height(String thumb_height) {
        this.thumb_height = thumb_height;
    }
}
