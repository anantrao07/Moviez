package app.com.moviez.anant.moviez.data;

import net.simonvt.schematic.annotation.AutoIncrement;
import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.NotNull;
import net.simonvt.schematic.annotation.PrimaryKey;
import net.simonvt.schematic.annotation.References;

/**
 * Created by anant on 2015-11-01.
 */
public interface ReviewsColumns {

    @DataType(DataType.Type.INTEGER) @References(table = MovieDb.movies, column = MoviesColumns._ID) String COLUMN_RMOVIE_ROW_ID =
            "movieRowId";


    @DataType(DataType.Type.INTEGER) @PrimaryKey
    @AutoIncrement
    public static final String _ID =
            "_id";

    @DataType(DataType.Type.INTEGER) @NotNull
    public static final String COLUMN_RMOVIE_ID = "rmovie_id";

    @DataType(DataType.Type.TEXT)
    public static final String COLUMN_AUTHOR = "author";


    @DataType(DataType.Type.TEXT)
    public static final String COLUMN_CONTENT = "content";


}
