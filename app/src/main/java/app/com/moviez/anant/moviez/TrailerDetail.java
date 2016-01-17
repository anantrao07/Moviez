package app.com.moviez.anant.moviez;

/**
 * Created by anant on 2015-09-26.
 */
public class TrailerDetail {

    private  String key;
    private String name;
    private  String site;
    private  String trailer_id;

    public void setTrailer_id(String trailer_id) {
        this.trailer_id = trailer_id;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getSite() {
        return site;
    }

    public String getTrailer_id() {
        return trailer_id;
    }
}
