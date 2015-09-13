package app.com.moviez.anant.moviez;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by anant on 2015-08-19.
 */

//Below class is created to create a custom array list to store the different values parsed from MOVIEDB json
public class MoviesDetail implements Parcelable ,Comparable<MoviesDetail> {

    private String jsf_original_title;
    private String jsf_poster;
    private String jsf_plot_synopsis;
    private Float jsf_user_rating;
    private String jsf_release_date;
    private String jsf_results;
    private int jsf_id;

    public MoviesDetail() {

    }

    private MoviesDetail(Parcel p) {
        jsf_original_title = p.readString();
        jsf_poster = p.readString();
        jsf_plot_synopsis = p.readString();
        jsf_user_rating = p.readFloat();
        jsf_release_date = p.readString();
        jsf_results = p.readString();
        jsf_id = p.readInt();

    }


    public String getJsf_original_title() {
        return jsf_original_title;
    }

    public String getJsf_poster() {
        return jsf_poster;
    }

    public String getJsf_plot_synopsis() {
        return jsf_plot_synopsis;
    }

    public float getJsf_user_rating() {
        return jsf_user_rating;
    }

    public String getJsf_release_date() {
        return jsf_release_date;
    }

    public String getJsf_results() {
        return jsf_results;
    }

    public int getJsf_id() {
        return jsf_id;
    }

    public void setJsf_original_title(String jsf_original_title) {
        this.jsf_original_title = jsf_original_title;
    }

    public void setJsf_poster(String jsf_poster) {
        this.jsf_poster = jsf_poster;
    }

    public void setJsf_plot_synopsis(String jsf_plot_synopsis) {
        this.jsf_plot_synopsis = jsf_plot_synopsis;
    }

    public void setJsf_user_rating(float jsf_user_rating) {
        this.jsf_user_rating = jsf_user_rating;
    }

    public void setJsf_release_date(String jsf_release_date) {
        this.jsf_release_date = jsf_release_date;
    }

    public void setJsf_results(String jsf_results) {
        this.jsf_results = jsf_results;
    }

    public void setJsf_id(int jsf_id) {
        this.jsf_id = jsf_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(jsf_original_title);
        dest.writeString(jsf_poster);
        dest.writeString(jsf_plot_synopsis);
        dest.writeFloat(jsf_user_rating);
        dest.writeString(jsf_release_date);
        dest.writeString(jsf_results);
        dest.writeInt(jsf_id);

    }

    public final Parcelable.Creator<MoviesDetail> CREATOR = new Parcelable.Creator<MoviesDetail>() {

        /**
         * Create a new instance of the Parcelable class, instantiating it
         * from the given Parcel whose data had previously been written by
         * {@link Parcelable#writeToParcel Parcelable.writeToParcel()}.
         *
         * @param source The Parcel to read the object's data from.
         * @return Returns a new instance of the Parcelable class.
         */
        @Override
        public MoviesDetail createFromParcel(Parcel source) {
            return new MoviesDetail(source);

        }

        /**
         * Create a new array of the Parcelable class.
         *
         * @param size Size of the array.
         * @return Returns an array of the Parcelable class, with every entry
         * initialized to null.
         */
        @Override
        public MoviesDetail[] newArray(int size) {
            return new MoviesDetail[size];
        }
    };

    /**
     * Compares this object to the specified object to determine their relative
     * order.
     *
     * @param //another the object to compare to this instance.
     * @return a negative integer if this instance is less than {@code another};
     * a positive integer if this instance is greater than
     * {@code another}; 0 if this instance has the same order as
     * {@code another}.
     * @throws ClassCastException if {@code another} cannot be converted into something
     *                            comparable to {@code this} instance.
     */
    @Override
    public int compareTo(MoviesDetail aMovie) {

        if(aMovie.getJsf_user_rating()<this.getJsf_user_rating()){
            return -1;
        }

        else if(aMovie.getJsf_user_rating()==this.getJsf_user_rating()){
            return 0;
        }

            return 1;

    }
}
