package sakurafish.com.examdbflow;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final String TAG = MainActivity.class.getSimpleName();
    private List<Posts> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        deleteData();
        addData();
        selectData();
        initLayout();
    }

    private void deleteData() {
        //全件削除
        Log.d(TAG, "count 削除前:" + new Delete().from(Posts.class).count());
        new Delete().from(Posts.class).query();
        Log.d(TAG, "count 削除後:" + new Delete().from(Posts.class).count());
    }

    private void addData() {
        Posts posts = new Posts();
        posts.date = new Date();
        posts.postId = "test01";
        posts.title = "title1";
        posts.description = "description1";
        posts.save();

        posts = new Posts();
        posts.date = new Date();
        posts.postId = "test02";
        posts.title = "title2";
        posts.description = "description2";
        posts.save();

        posts = new Posts();
        posts.date = new Date();
        posts.postId = "test03";
        posts.title = "title3";
        posts.description = "description3";
        posts.save();

        posts = new Posts();
        posts.date = new Date();
        posts.postId = "test04";
        posts.title = "title4";
        posts.description = "description4";
        posts.save();

        posts = new Posts();
        posts.date = new Date();
        posts.postId = "test05";
        posts.title = "title5";
        posts.description = "description5";
        posts.save();
    }

    private void selectData() {
        //全件取得
        mDatas = new Select().from(Posts.class).queryList();
    }

    private void initLayout() {
        ListView listView = (ListView) findViewById(R.id.listview);
        final MyAdapter adapter = new MyAdapter(this, mDatas);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "deleted! " + mDatas.get(position).postId, Toast.LENGTH_LONG).show();
                //１件削除
                mDatas.get(position).delete();
                mDatas.remove(position);
                adapter.swapItems(mDatas);
            }
        });
    }

    public class MyAdapter extends BaseAdapter {
        private Context mContext;
        private List<Posts> mDatas;

        public MyAdapter(@NonNull Context context, @Nullable List<Posts> datas) {
            super();
            mContext = context;
            mDatas = datas;
        }

        public void swapItems(@NonNull final List<Posts> datas) {
            mDatas = datas;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            if (mDatas == null) {
                return 0;
            }
            return mDatas.size();
        }

        @Override
        public Object getItem(int position) {
            return mDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        class ViewHolder {
            TextView date;
            TextView postId;
            TextView title;
            TextView description;
        }

        SimpleDateFormat incomingFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        SimpleDateFormat outgoingFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm",
                java.util.Locale.getDefault());

        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
                convertView = inflater.inflate(R.layout.list_row, null);
                holder = new ViewHolder();

                holder.date = (TextView) convertView.findViewById(R.id.textView_date);
                holder.postId = (TextView) convertView.findViewById(R.id.textView_post_id);
                holder.title = (TextView) convertView.findViewById(R.id.textView_title);
                holder.description = (TextView) convertView.findViewById(R.id.textView_description);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            Posts data = (Posts) getItem(position);

            String date = outgoingFormat.format(data.date);
            holder.date.setText(date);
            holder.postId.setText(data.postId);
            holder.title.setText(data.title);
            holder.description.setText(data.description);

            return convertView;
        }
    }
}
