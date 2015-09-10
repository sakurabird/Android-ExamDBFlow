package sakurafish.com.examdbflow;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;

@Table(databaseName = DBSample.NAME)
public class Posts extends BaseModel {

    @Column
    @PrimaryKey()
    String postId;

    @Column
    Date date;

    @Column
    String title;

    @Column
    String description;
}