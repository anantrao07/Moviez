package app.com.moviez.anant.moviez.data;

import net.simonvt.schematic.annotation.AutoIncrement;
import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.NotNull;
import net.simonvt.schematic.annotation.PrimaryKey;

/**
 * Created by anant on 2015-11-01.
 */
public interface MoviesColumns {




  //  @DataType(DataType.Type.INTEGER)

  //  public static final String COLUMN_MOVIE_ROW_ID =
    //        "movie_row_id";

    @DataType(DataType.Type.INTEGER) @PrimaryKey
    @AutoIncrement
    public static final String _ID =
            "_id";

    @DataType(DataType.Type.INTEGER) @NotNull
    public static final String COLUMN_MOVIE_ID =
            "movie_id";


    @DataType(DataType.Type.TEXT) @NotNull
    public static final String COLUMN_ORIGINAL_TITLE = "original_title";

    @DataType(DataType.Type.TEXT) @NotNull
    public static final String COLUMN_RELEASE_DATE =
            "release_date";

    @DataType(DataType.Type.TEXT) @NotNull
    public static final String COLUMN_BACKDROP_PATH =
            "backdrop_path";

    @DataType(DataType.Type.TEXT) @NotNull
    public static final String COLUMN_OVERVIEW =
            "overview";

    @DataType(DataType.Type.REAL) @NotNull
    public static final String COLUMN_VOTE_AVERAGE =
            "vote_average";

    @DataType(DataType.Type.REAL) @NotNull
    public static final String COLUMN_POPULARITY = "popularity";

    @DataType(DataType.Type.TEXT) @NotNull
    public static final String COLUMN_FAVORITE = "is_favorite";


}
