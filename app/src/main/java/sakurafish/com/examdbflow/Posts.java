package sakurafish.com.examdbflow;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.annotation.Unique;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;

@Table(databaseName = DBSample.NAME)
public class Posts extends BaseModel {

    @Column
    @Unique
    @PrimaryKey()
    String postId;

    @Column(name = "modify_date")
    Date date;

    @Column
//    @NotNull
    String title;

    @Column
    String description;

//    @Column
//    com.raizlabs.android.dbflow.data.Blob testblob;
}