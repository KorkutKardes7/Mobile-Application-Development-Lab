package msku.ceng.madlab.week3;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

public class PostAdapter extends BaseAdapter {
    List<Post> postList;
    LayoutInflater inflater;

    public PostAdapter(List<Post> postList, Activity activity) {
        this.postList = postList;
        inflater = activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return postList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View rowView;
        rowView = inflater.inflate(R.layout.row, null);
        EditText txtMessage = rowView.findViewById(R.id.textmessage);
        TextView txtLocaiton = rowView.findViewById(R.id.textLocation);
        ImageView imgView = rowView.findViewById(R.id.imgView);

        Post post = postList.get(position);
        txtMessage.setText(post.getMessage());
        imgView.setImageBitmap(post.getImage());
        if (post.getLocation() != null){
            txtLocaiton.setText(post.getLocation().getLatitude() +
                    " " + post.getLocation().getLongitude());
        }

        return rowView;
    }


}
