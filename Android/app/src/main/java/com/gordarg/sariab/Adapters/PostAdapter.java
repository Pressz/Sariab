package com.gordarg.sariab.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gordarg.sariab.Data.Access;
import com.gordarg.sariab.DetailsActivity;
import com.gordarg.sariab.Helpers.IntentCheck;
import com.gordarg.sariab.Models.Post;
import com.gordarg.sariab.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import at.markushi.ui.CircleButton;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MainActivityListViewHolder> {

    private Context context;
    private List<Post> postList;


    public PostAdapter(Context context, ArrayList<Post> postList) {
        this.context = context;
        this.postList = postList;
    }

    @Override
    public MainActivityListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.listitem_activity_main, null);
        MainActivityListViewHolder holder = new MainActivityListViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MainActivityListViewHolder holder, int position) {

        final Post post = postList.get(position);

        holder.textViewTitle.setText(post.getTitle());
        holder.textViewDescrpition.setText(String.valueOf(post.getDescription()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(context, DetailsActivity.class);
                    boolean isIntentAvailable = new IntentCheck(context, intent).isIntentAvailable();
                    if (isIntentAvailable) {
                        intent.putExtra("ID", post.getId());
                        context.startActivity(intent);
                    }

            }
        });
        holder.button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Add
            }
        });
        holder.button_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Remove
            }
        });

        new Thread( new Runnable() {
            @Override
            public void run() {
                try {
                    String parms = "?Id=postImage.sql&Params=[PostId:" + post.getId() + "]";
                    Access acc = new Access("BridgeController.php", parms);
                    JSONArray resp = acc.Get();
                    JSONObject jo = (JSONObject) resp.get(0);
                    String b = jo.getString("BinImage");
                    byte[] decodedString = Base64.decode(b, Base64.DEFAULT);
                    final Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    if (b.equals("null") || decodedByte.equals(""))
                        return;
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public void run() {
                        holder.imageView.setImageBitmap(decodedByte);
                        }
                    });
                }
                catch (Exception exp)
                {
                    exp.printStackTrace();
                }
            }
        }).start();

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class MainActivityListViewHolder extends RecyclerView.ViewHolder {

        CircleButton button_add, button_remove;
        ImageView imageView;
        TextView textViewTitle, textViewDescrpition;

        public MainActivityListViewHolder(final View itemView) {
            super(itemView);

            button_add = itemView.findViewById(R.id.button_add);
            button_remove = itemView.findViewById(R.id.button_remove);
            textViewTitle = itemView.findViewById(R.id.txt_title);
            textViewDescrpition = itemView.findViewById(R.id.txt_desc);
            imageView = itemView.findViewById(R.id.image_food);
        }
    }
}
