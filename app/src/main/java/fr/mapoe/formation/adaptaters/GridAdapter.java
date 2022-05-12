package fr.mapoe.formation.adaptaters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import fr.mapoe.formation.R;
import fr.mapoe.formation.models.Categorie;

public class GridAdapter extends BaseAdapter {

    public GridAdapter(Context context, ArrayList<Categorie> categories) {
        this.context = context;
        this.categories = categories;
    }

    Context context;
    ArrayList<Categorie> categories;
    LayoutInflater layoutInflater;

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int index, View contentView, ViewGroup parent) {
        if(layoutInflater == null){
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (contentView == null){

            contentView = layoutInflater.inflate(R.layout.grid_item,null);

        }
        TextView textView = contentView.findViewById(R.id.cat_title);
        String title = categories.get(index).title;
        title = title.substring(0, 1).toUpperCase() + title.substring(1);
        textView.setText(title);
        return contentView;
    }
}


