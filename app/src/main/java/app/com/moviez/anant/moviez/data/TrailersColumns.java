package app.com.moviez.anant.moviez.data;

import net.simonvt.schematic.annotation.AutoIncrement;
import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.NotNull;
import net.simonvt.schematic.annotation.PrimaryKey;
import net.simonvt.schematic.annotation.References;

/**
 * Created by anant on 2015-11-01.
 */
public interface TrailersColumns {

    @DataType(DataType.Type.INTEGER) @References(table = MovieDb.movies, column = MoviesColumns._ID)
    String COLUMN_TMOVIE_ROW_ID =   "tmovie_row_id";


    @DataType(DataType.Type.INTEGER) @PrimaryKey
    @AutoIncrement
    public static final String _ID =
            "_id";
    @DataType(DataType.Type.INTEGER)@NotNull
    public static final String COLUMN_TMOVIE_ID = "tmovie_id";


    @DataType(DataType.Type.TEXT) @NotNull
    public static final String COLUMN_MOVIE_TRAILER_NAME = "trailer_name";


    @DataType(DataType.Type.TEXT) @NotNull
    public static final String COLUMN_MOVIE_TRAILER_KEY = "trailer_key";



}
