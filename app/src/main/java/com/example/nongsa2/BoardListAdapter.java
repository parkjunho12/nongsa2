package com.example.nongsa2;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Response;

import java.util.List;

public class BoardListAdapter extends BaseAdapter {
    private Context context;
    private List<Board> boardList;
    private Fragment parent;

    public BoardListAdapter(Context context, List<Board> boardList,Fragment parent)
    {
        this.context = context;
        this.boardList =boardList;
        this.parent=parent;
    }

    @Override
    public int getCount() {
        return boardList.size();
    }

    @Override
    public Object getItem(int i) {
        return boardList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context,R.layout.board,null);

        TextView title = (TextView) v.findViewById(R.id.title);
        TextView date = (TextView) v.findViewById(R.id.date);
        TextView content = (TextView) v.findViewById(R.id.content);

        title.setText(boardList.get(i).getTitle());
        date.setText(boardList.get(i).getDate());
        content.setText(boardList.get(i).getContent());


        Button addbutton =(Button)v.findViewById(R.id.addbtn);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
        return v;
    }
}
