package com.saipc.ashish.instareplica;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ashish.instareplica.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class CustomListAdapter extends BaseAdapter {

    ArrayList<ImageObject> imgObjects;
    Context context;
    LayoutInflater layoutInflater;
    int size;

    public CustomListAdapter(ArrayList<ImageObject> _imgObjs, Context _context, int _size) {
        imgObjects = _imgObjs;
        context = _context;
        layoutInflater = LayoutInflater.from(context);
        size = _size;
    }

    @Override
    public int getCount() {
        return imgObjects.size();
    }

    @Override
    public Object getItem(int i) {
        return imgObjects.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 1;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        holder hold = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.row_item, null);
            hold = new holder();
            hold.txtName = (TextView) convertView.findViewById(R.id.tv_UserName);
            hold.txtCaption = (TextView) convertView.findViewById(R.id.tv_Caption);
            hold.btnshare = (ImageButton) convertView.findViewById(R.id.imageButton);
            hold.btncomment = (ImageButton) convertView.findViewById(R.id.btn_comment);
            hold.txtHashTags = (TextView) convertView.findViewById(R.id.tv_HashTag);
            hold.imgDisp = (ImageView) convertView.findViewById(R.id.imgDisp);
            hold.imgLike = (ImageButton) convertView.findViewById(R.id.btn_like);
            hold.cmnt_list = (ListView) convertView.findViewById(R.id.comments_list);

            convertView.setTag(hold);

        } else {
            hold = (holder) convertView.getTag();
        }
        hold.txtName.setText(imgObjects.get(position).getName());
        hold.txtCaption.setText(imgObjects.get(position).getCaption());
        hold.txtHashTags.setText(imgObjects.get(position).getHashtags());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.comment_list_item, imgObjects.get(position).getComment_list());
        hold.cmnt_list.setAdapter(adapter);
        

        System.out.println("getComment_list().size() : " + imgObjects.get(position).getComment_list().size());


        if (imgObjects.get(position).isLiked()) {
            hold.imgLike.setImageResource(R.drawable.liked_48x48);
        } else {
            hold.imgLike.setImageResource(R.drawable.like48x48);
        }


        Picasso.with(context)
                .load(imgObjects.get(position).getUrl()).resize(size, size)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.ic_launcher)
                .into(hold.imgDisp);

        hold.imgLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("IMG FAV : " + position);
                System.out.println("imgObjects.get(position).isLiked() : " + imgObjects.get(position).isLiked());
                if (imgObjects.get(position).isLiked()) {
                    ImageButton ib = (ImageButton) v.findViewById(R.id.btn_like);
                    ib.setImageResource(R.drawable.like48x48);
                } else {
                    ImageButton ib = (ImageButton) v.findViewById(R.id.btn_like);
                    ib.setImageResource(R.drawable.liked_48x48);
                }
            }
        });
        hold.btncomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("IMG COMMENT : " + position);

                LayoutInflater inflater = LayoutInflater.from(context);
                View dialog_layout = inflater.inflate(R.layout.comment_dialog, null);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(true);
                builder.setView(dialog_layout);
                builder.setPositiveButton("Comment", new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                builder.show();

                Toast.makeText(context, "Clicked", Toast.LENGTH_LONG).show();

            }
        });
        hold.btnshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("IMG SHARE : " + position);
                Intent nav_share_Intent = new Intent(android.content.Intent.ACTION_SEND);
                nav_share_Intent.setType("text/plain");
                nav_share_Intent.putExtra(android.content.Intent.EXTRA_TEXT, "SECU-SPACE SHARE");
                context.startActivity(Intent.createChooser(nav_share_Intent, "Share via :"));
            }
        });

        return convertView;
    }

    static class holder {
        TextView txtName;
        TextView txtCaption;
        TextView txtHashTags;
        ImageView imgDisp;
        ImageButton btnshare;
        ImageButton btncomment;
        ImageButton imgLike;
        ListView cmnt_list;

    }
}
